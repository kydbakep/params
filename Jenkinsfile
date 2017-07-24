node {
    stage('Test of parameters') {
        git 'https://svarych.a@stash.np.ua/scm/rpz/test_parameters.git'
        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
            sh 'ls -a src/'

            def commandParams = ''
            if (env.AWIS_URL) {
                commandParams += " -Durl=${env.AWIS_URL}"
            }
            if (env.AWIS_LASTNAME) {
                commandParams += " -DlastName=${env.AWIS_LASTNAME}"
            }
            if (env.AWIS_LOGIN) {
                commandParams += " -Dname=${env.AWIS_LOGIN}"
            }

            sh "mvn test " + commandParams

//            post {
//                always {
//                    junit 'build/reports/**/*.xml'
//                }
//            }

            junit '/var/jenkins_home/workspace/np_test_suite/target/surefire-reports/*.xml'

//            junit 'archiveArtifacts \'**/surefire-reports/TEST-*.xml\''
//            archiveArtifacts '/var/jenkins_home/workspace/np_test_suite/target/surefire-reports/*.xml'
        }
    }
}