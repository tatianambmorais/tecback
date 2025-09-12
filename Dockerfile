# Build
FROM maven:3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Variáveis de ambiente
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/techback
ENV SPRING_DATASOURCE_USERNAME=tech_user
ENV SPRING_DATASOURCE_PASSWORD=123456
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Instalando cliente Postgres para teste de conexão
RUN apt-get update && apt-get install -y postgresql-client && rm -rf /var/lib/apt/lists/*

# Espera o Postgres estar pronto antes de iniciar
CMD /bin/bash -c "until pg_isready -h db -p 5432 -U $SPRING_DATASOURCE_USERNAME; do echo 'Esperando Postgres...'; sleep 2; done; java -jar app.jar"
