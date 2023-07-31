timeout(180) {
    node('maven') {
            wrap([$class: 'BuildUser']){
                summary = """|<b>Owner:</b> ${env.BUILD_USER}
                            |<b>Branch:</b> ${BRANCH}""".stripMargin()
                currentBuild.description = summary.replaceAll("\n", "<br>")
                owner_user_id = "${env.BUILD_USER_ID}"
                }
            yaml_object = readYaml $YAML_CONFIG
            for (key in params.keySet()) {
                System.setProperty(key, params[key])
            }
            stage('Checkout') {
                checkout scm
            }
            stage('Run tests') {
                tests_exit_code = sh(
                        returnStatus: true,
                        script:"mvn test -Dbrowser=$BROWSER_NAME -Dbrowser.version=$BROWSER_VERSION",
                )

                if(tests_exit_code != 0) {
                    currentBuild.status = 'UNSTABLE'
                }
            }
            stage('Publish artifacts') {
                allure([
                        includeProperties: true,
                        results: [{
                                      path: 'allure-results'
                                  }],
                        disabled: false,
                        reportBuildPolicy: ALWAYS
                ])
                build('ui-tests', [])
            }
            stage('Publish notification in telegram') {
                lines = readFile './allure-results/export/influxDbData.txt'
                def message = "============= UI REPORT ============"
                for (line in lines) {
                    def matcher = line =~ /.*(\w+=\d+).*/
                    message += line
                }

                def connection = null

                withCredentials([sring(name: 'telegram_token', envVar: 'TELEGRAM_TOKEN')]) {
                    def url = "https://api.telegram.org/bot$TELEGRAM_TOKEN/sendMessage?"
                    connection = new URL(url).openConnection() as HttpURLConnection
                        connection.setProperty("token", $TELEGRAM_TOKEN)
                        connection.setProperty("text", message)
                        connection.setProperty('chat_id': '-918019592')
                    }
                    connection.setRequestMethod('POST')
                    connection.setDoOutput(true)
                }

        }
    }