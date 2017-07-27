node {
    checkout scm

    stage('Test of parameters') {
        sh "ls"

        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
            sh 'ls -a src/'
//==============================================================================
            String commandParams = ''
// AWIS ------------------------------------------------------------------------
            if (env.AWIS_URL) {
                commandParams += " -Dawis.url=${env.AWIS_URL}"
            }
            if (env.AWIS_LOGIN) {
                commandParams += " -Dawis.login=${env.AWIS_gLOGIN}"
            }
            if (env.AWIS_PASSWORD) {
                commandParams += " -Dawis.password=${env.AWIS_PASSWORD}"
            }
//WEB   ------------------------------------------------------------------------
            if (env.WEB_URL) {
                commandParams += " -Dweb.url=${env.WEB_URL}"
            }
            if (env.WEB_LOGIN) {
                commandParams += " -Dweb.login=${env.WEB_LOGIN}"
            }
            if (env.WEB_PASSWORD) {
                commandParams += " -Dweb.password=${env.WEB_PASSWORD}"
            }
//GOOGLE------------------------------------------------------------------------
            if (env.GOOGLE_URL) {
                commandParams += " -Dgoogle.url=${env.GOOGLE_URL}"
            }
            if (env.GOOGLE_QUERY) {
                commandParams += " -Dgoogle.query=${env.GOOGLE_QUERY}"
            }
//==============================================================================
            sh "echo 'Starting tests'"

            sh "Xvfb :99 -ac -screen 0 1920x1080x24 &"
            sh "mvn clean test" + commandParams
//            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
//            sh "ls target/surefire-reports/"
//            junit allowEmptyResults: true, keepLongStdio: true, testResults: '/var/log*'
        }
    }
    stage('Results') {
        junit allowEmptyResults: true, keepLongStdio: true, testResults: '**/target/surefire-reports/*'
        ls '**/target/surefire-reports/'
    }
}