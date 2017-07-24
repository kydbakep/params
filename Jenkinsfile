node {
    checkout scm

    stage('Test of parameters') {
        sh "ls"
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

            sh "mvn clean"
            sh "mvn test" + commandParams

            sh ls "/var/jenkins_home/workspace/np_test_suite/target/surefire-reports/"

            junit '/var/jenkins_home/workspace/np_test_suite/target/surefire-reports/*.xml'

        }
    }
}