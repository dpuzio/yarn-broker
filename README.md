# yarn-broker

Cloud foundry broker for YARN.

# How to use it?
To use zookeeper-broker, you need to build it from sources configure, deploy, create instance and bind it to your app. Follow steps described below.

## Build
Run command for compile and package.:
```
mvn clean package
```

## Deploy
Push broker binary code to cloud foundry (use cf client).:
```
cf push yarn-broker -p target/yarn-broker-*.jar -m 512M -i 1 --no-start
```

## Configure

For strict separation of config from code (twelve-factor principle), configuration must be placed in environment variables.

Broker configuration params list (environment properties):
* obligatory :
  * ZKCLUSTER_URL - comma separated ip addresses of zookeeper nodes
* obligatory only when zookeeper requires kerberos authentication:
  * KRB_KDC_HOST - kerberos kdc host address
  * KRB_REALM - kerberos realm name
* optional :
  * HADOOP_PROVIDED_PARAMS - list of hadoop configuration parameters exposed by service (json format, default: {})
  * CF_CATALOG_SERVICENAME - service name in cloud foundry catalog (default: yarn)
  * CF_CATALOG_SERVICEID - service id in cloud foundry catalog (default: yarn)
  * YARNBRK_SPACE: - (default: /yarnbrk_space)

For instance.:
```
cf se yarn-broker ZKCLUSTER_URL 10.10.9.145:2181,10.10.9.146:2181
```

When zookeeper requires kerberos authentication set:
```
cf se yarn-broker KRB_KDC_HOST ip-10-10-9-198.us-west-2.compute.internal
cf se yarn-broker KRB_REALM US-WEST-2.COMPUTE.INTERNAL

## Injection of Yarn configuration
YARN configuration must be set via HADOOP_PROVIDED_PARAMS environment variable. Description of this process is this same as in HDFS broker case ( https://github.com/intel-data/hdfs-broker/ ).

## Start service broker application

Use cf client :
```
cf start yarn-broker
```
## Create new service instance

Use cf client :
```
cf create-service-broker yarn-broker user haselko_maselko https://yarn-broker.gotapaas.com
cf enable-service-access yarn
cf cs yarn shared yarn-instance
```

## Binding broker instance

Broker instance can be bind with cf client :
```
cf bs <app> yarn-instance
```
or by configuration in app's manifest.yml :
```yaml
  services:
    - yarn-instance
```

To check if broker instance is bound, use cf client :
```
cf env <app>
```
and look for :
```yaml
  "yarn": [
   {
    "credentials": {
     "kerberos": {
      "kdc": "ip-10-10-9-198.us-west-2.compute.internal",
      "krealm": "US-WEST-2.COMPUTE.INTERNAL"
     }
    },
    "label": "yarn",
    "name": "yarn-instance",
    "plan": "shared",
    "tags": []
   }
  ]
```
in VCAP_SERVICES.
