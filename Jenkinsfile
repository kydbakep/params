node {
    stage('Test of parameters') {
        git 'https://github.com/kydbakep/params.git'
        echo env.AWIS_URL
        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
            mvn surefire: test
        }
    }
}