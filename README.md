# Auto-Logs


## Description

Auto-Logs est une application Spring Boot qui permet de [générer / centraliser / analyser] automatiquement les logs de [votre cas d'usage]. Le projet est conçu pour être facilement extensible et indépendant des frameworks externes.

## Technologies & Architecture 

### Technologies

Le projet est développé en utilisant l'écosystème moderne de **Kotlin** et **Spring Boot**, avec un outillage robuste pour les tests et la gestion de la base de données :

* **Language & Runtime :**
    * **Java 25**
    * **Kotlin 2.4.20**


* **Main Framework :**
    * **Spring Boot 4.1.1**


* **Database & Persistence :**
    * **Spring JDBC 4.1.1**
    * **PostgreSQL**
    * **Liquibase 4.1.1**


* **Tests (TDD / BDD) :**
    * **JUnit 6.0.3**
    * **MockK 1.14.11**


* **Documentation :**
    * **SpringDoc OpenAPI 3.1.1** (with **WebMVC UI**)

### Architecture
For the Architecture of this project, we used, hexagonal architecture for a better 
project modularity, so if a technology is deprecated or other things that might happen, 
we won't be blocked, and will gain productivity time.

Pour ce projet, nous avons mis en place une **Architecture Hexagonale** (Ports & Adapteurs). Ce choix garantit une excellente modularité : le cœur métier est totalement indépendant des frameworks, des bases de données ou des APIs externes. Si une technologie devient obsolète, elle peut être remplacée sans impacter la logique métier.

L'arborescence du projet suit ce découpage strict :
```text
    📂AutoLog
    ├── 📂 domain                                           # Cœur Métier
    │   └── 📂 src/main/kotlin/site/benly/autolog
    │      ├── 📂 models                                    # Objets métiers et entités
    │      ├── 📂 ports                                     # Interfaces de liaison
    │      │   ├── 📂 inbound                               # Cas d'utilisation (Use Cases)
    │      │   └── 📂 outbound                              # Contrats requis (Repositories, SPI)
    │      └── 📂 services                                  # Implémentations des Use Cases du domaine
    │
    └── 📂 infrastructure                                   # Module Technique (Dépendant du Framework)
        └── 📂 src/main/kotlin/site/benly/autolog
            ├── 📂 adapters                                 # Implémentations concrètes des ports
            │   ├── 📂 inbound                              
            │   │   └── 📂 rest                             # Contrôleurs REST
            │   │       └── 📂 DTO                          # Objets d'échange de l'API
            │   └── 📂 outbound                             # Persistance (JDBC), Clients HTTP, etc.
            │       └── 📂 repository                       # Implémentation des accès BDD
            └── 📂 config                                   # Configuration Spring Boot et Beans du domaine
```

## Installation and Run


### Prequisite
* **JDK 25** installé.
* **Maven** (ou le wrapper `./mvnw` inclus).
* Une instance **PostgreSQL** active.

### Configuration de la Base de Données
Avant de lancer l'application, assurez-vous de configurer vos accès à PostgreSQL. Vous pouvez définir les variables d'environnement suivantes ou adapter votre fichier `application.yml` :

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/autolog_db
SPRING_DATASOURCE_USERNAME=votre_utilisateur
SPRING_DATASOURCE_PASSWORD=votre_mot_de_passe
```

> **Note :** Liquibase exécutera automatiquement les scripts de migration à la racine du projet lors du premier démarrage pour créer les tables nécessaires.


### Run
1. Cloner the repo :
   ```bash
   git clone https://github.com/Setsulys/Auto-Logs
   cd Auto-Logs
   ```

2. Install dependencies :
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```


## Documentation API

### Springdoc
After The app
Une fois l'application démarrée, la documentation interactive de l'API (Swagger UI) générée par SpringDoc est accessible à l'adresse suivante :
- Local : 🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Prod  : 🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)