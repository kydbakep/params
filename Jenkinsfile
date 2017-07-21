node {
    stage('Test of parameters') {
        git 'https://github.com/kydbakep/params.git'
        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
            sh 'ls -a src/'
            sh "mvn test -Durl=${env.AWIS_URL} -Dname=${env.AWIS_LOGIN} -DlastName=${env.AWIS_LASTNAME}"
        }
    }
}