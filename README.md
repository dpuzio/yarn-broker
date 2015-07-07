# yarn-broker

Cloud foundry broker for YARN.

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

## Injection of Yarn configuration
YARN configuration must be set via HADOOP_PROVIDED_PARAMS environment variable. Description of this process is this same as in HDFS broker case ( https://github.com/intel-data/hdfs-broker/ ).