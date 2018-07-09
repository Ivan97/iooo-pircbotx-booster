package tech.iooo.coco.configuration;

/**
 * Created on 2018/7/9 下午5:34
 *
 * @author <a href="mailto:yangkizhang@gmail.com?subject=iooo-pircbotx-booster">Ivan97</a>
 */
public interface ApplicationConstants {

  // Spring profiles for development, test and production, see http://www.jhipster.tech/profiles/
  String SPRING_PROFILE_DEVELOPMENT = "dev";
  String SPRING_PROFILE_TEST = "test";
  String SPRING_PROFILE_PRODUCTION = "prod";
  // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
  String SPRING_PROFILE_CLOUD = "cloud";
  // Spring profile used when deploying to Heroku
  String SPRING_PROFILE_HEROKU = "heroku";
  // Spring profile used to disable swagger
  String SPRING_PROFILE_SWAGGER = "swagger";
  // Spring profile used when deploying to Kubernetes and OpenShift
  String SPRING_PROFILE_K8S = "k8s";
}
