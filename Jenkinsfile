node {
    stage('Test of parameters') {
        git 'ssh://git@stash.np.ua:7999/rpz/test_parameters.git'
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

            sh "mvn clean test " + commandParams

            junit '/var/jenkins_home/workspace/np_test_suite/target/surefire-reports/*.xml'

        }
    }
}