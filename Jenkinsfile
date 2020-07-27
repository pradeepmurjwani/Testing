node {
   try {
      stage('initialize') {
         def dockerHome = tool 'maven-3.6.3'
         def mavenHome  = tool 'docker'
         env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
      }

      stage('git checkout') {
         git "https://github.com/pradeepmurjwani/Testing.git"
      }

      stage ('code clean, test and build') {
         sh "mvn clean package"
      }

      stage ('docker build') {
         sh "sudo docker build -t pradeepmurjwani/testing:0.1 ."
      }

      stage ('docker image push into dockerhub') {
          withCredentials([string(credentialsId: 'dockerHubPassword', variable: 'dockerHubPassword')]) {
               sh "sudo docker login -u pradeepmurjwani -p ${dockerHubpassword}"
         }
         sh "sudo docker push pradeepmurjwani/testing:0.1"
      }

    // working, tried and tested
   // stage ('push into pcf via cf command') {
  //       withCredentials([string(credentialsId: 'pcfPassword', variable: 'pcfPassword')]) {
   //          sh "cf login -a https://api.run.pivotal.io -u pradeep.m.murjwani@gmail.com -p ${pcfPassword} -o pradeep.m.murjwani.org"
   //      }
  //      sh "cf push springboot-cf-cmd --docker-image pradeepmurjwani/pipeline_springboot:0.1"
   // }

    // working, tried and tested
    // stage('push into pcf via CloudFoundry plugin') {
    //      pushToCloudFoundry manifestChoice: [manifestFile: 'cicd-pipeline-manifest.yml'], cloudSpace: 'development', credentialsId: 'pcfUserAndPswd', organization: 'pradeep.m.murjwani.org', target: 'api.run.pivotal.io'
    // }
   } catch(e) {
      notifyFailed()
   }
} 

def notifyFailed() {
   def recipients = emailextrecipients([ [$class: 'DevelopersRecipientProvider'] ])
   def recipients1 = emailextrecipients([ [$class: 'RequesterRecipientProvider'] ])
   sh "echo 1=${recipients}"
   sh "echo 2=${recipients1}"
   def subject = "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
   def body = """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""
   emailext (subject: subject, body: body, recipientProviders: [[$class: 'DevelopersRecipientProvider']])
}
