# yarn-broker

Cloud foundry broker for YARN.

## Configure

For strict separation of config from code (twelve-factor principle), configuration must be placed in environment variables.

Broker configuration params list (environment properties):
* obligatory :
  * YARNBRK_SPACE: - name of zookeeper node where broker bindings/instances will be stored, must start with "/" (default: /yarnbrk_space)
  * ZKCLUSTER_URL - url to zookeeper cluster
* optional :
  * HADOOP_PROVIDED_PARAMS - list of hadoop configuration parameters exposed by service (json format, default: {})
  * CF_CATALOG_SERVICENAME - service name in cloud foundry catalog (default: yarn)
  * CF_CATALOG_SERVICEID - service id in cloud foundry catalog (default: yarn)

## Injection of Yarn configuration
YARN configuration must be set via HADOOP_PROVIDED_PARAMS environment variable. Description of this process is this same as in HDFS broker case ( https://github.com/intel-data/hdfs-broker/ ).

