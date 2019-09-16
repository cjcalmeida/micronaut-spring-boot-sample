# Micronaut with Spring Boot Sample

PoC para validar execução de aplicações Spring Boot com Micronaut.

### Referencia do Framework Micronaut

**[Micronaut](https://micronaut.io/)**

### Executando via Docker
*Dentro da pasta raiz do projeto*

##### 1. Build
```
mvnw clean install
```

##### 1. Execução
```
docker run --rm -ti -p 8080:8080 cristiano/estudos-micronaut-spring-boot-data-services
```

## Performance Spring Boot

Durante os testes, fiz algumas configurações que permitem que o Spring execute com 150Mb.

Para executar com o docker, retire o comentario da classe de start: Application.

Em seguida, comente o pluginManagement do pom.xml, e compile conforme a seção anterior,
com os parametros abaixo de execução:

```
 docker run -ti --rm -p 8080:8080 -p 9091:9091 -e JAVA_OPTS="-Xmx40m -Xms20m -XX:MaxNewSize=13m -XX:MaxPermSize=30m -XX:PermSize=15m" -m 150m   cristiano/estudos-micronaut-spring-boot-sample:0.0.3-SNAPSHOT 
``` 

O mesmo pode ser feito para o Micronaut.