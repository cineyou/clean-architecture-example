# Exemple d'Architecture Hexagonale (Clean Architecture)

## Présentation du Projet
Ce projet est un exemple d'implémentation de l'architecture hexagonale (également connue sous le nom de Clean Architecture) en Java. Il s'agit d'une application de gestion de comptes bancaires qui permet de créer différents types de comptes, d'effectuer des opérations de crédit et de débit, et de consulter les soldes.

## Architecture du Projet
Le projet est structuré selon les principes de l'architecture hexagonale, qui vise à séparer les préoccupations et à rendre le code plus maintenable, testable et évolutif.

### Principes Clés
- **Indépendance des frameworks** : Le cœur de l'application ne dépend pas de l'existence d'une bibliothèque ou d'un framework.
- **Testabilité** : Les règles métier peuvent être testées sans interface utilisateur, base de données ou tout autre élément externe.
- **Indépendance de l'interface utilisateur** : L'interface utilisateur peut changer facilement sans modifier le reste du système.
- **Indépendance de la base de données** : Les règles métier ne sont pas liées à la base de données.
- **Indépendance de tout agent externe** : Les règles métier ne connaissent rien du monde extérieur.

### Structure du Projet
Le projet est organisé en modules Maven :

- **clean-architecture-domain** : Contient le cœur métier de l'application, les entités, les règles métier et les ports.
- **clean-architecture-infrastructure** : Contient les adaptateurs, les configurations et les implémentations techniques.

## Domaine Métier
Le domaine métier est centré autour de la gestion de comptes bancaires :

### Entités Principales
- **Compte** : Classe abstraite représentant un compte bancaire générique.
- **CompteCourant** : Compte avec possibilité de découvert.
- **CompteEpargne** : Compte avec taux d'intérêt.

### Cas d'Utilisation
- Création de compte
- Consultation du solde
- Opérations de débit et crédit
- Récupération de tous les comptes
- Récupération d'un compte par ID

## Stack Technique
- **Java 17**
- **Spring Boot** : Framework pour la création d'applications Java
- **Spring Data JPA** : Pour la persistance des données
- **Maven** : Outil de gestion de dépendances et de build
- **Docker** : Pour la conteneurisation
- **Flyway** : Pour la gestion des migrations de base de données
- **JUnit 5** : Pour les tests unitaires et d'intégration

## Installation et Configuration

### Prérequis
- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- Docker et Docker Compose (pour l'environnement de développement)

### Étapes d'Installation
1. Cloner le dépôt :
   ```
   git clone https://github.com/votre-utilisateur/clean-architecture-example.git
   ```

2. Naviguer vers le répertoire du projet :
   ```
   cd clean-architecture-example
   ```

3. Compiler le projet :
   ```
   mvn clean install
   ```

4. Lancer l'environnement Docker :
   ```
   cd clean-architecture-infrastructure
   docker-compose up -d
   ```

5. Démarrer l'application :
   ```
   mvn spring-boot:run -pl clean-architecture-infrastructure
   ```

## API REST
L'application expose les endpoints REST suivants :

### Création d'un Compte
- **URL** : `/api/v1/comptes`
- **Méthode** : `POST`
- **Corps de la Requête** :
  ```json
  {
    "typeCompte": "COMPTE_COURANT",
    "montant": 1000
  }
  ```
- **Réponse** :
  ```json
  {
    "id": "uuid-généré",
    "solde": 1000.0
  }
  ```

### Récupération de Tous les Comptes
- **URL** : `/api/v1/comptes`
- **Méthode** : `GET`
- **Réponse** : Liste de tous les comptes

### Récupération d'un Compte par ID
- **URL** : `/api/v1/comptes/{id}`
- **Méthode** : `GET`
- **Réponse** : Détails du compte spécifié

## Tests
Le projet comprend plusieurs types de tests :

- **Tests Unitaires** : Testent les composants individuels.
- **Tests Fonctionnels** : Testent les cas d'utilisation métier.
- **Tests d'Intégration** : Testent l'interaction entre les différentes couches.

Pour exécuter les tests :
```
mvn test
```

## Configuration Docker
Le projet inclut un fichier `docker-compose.yml` qui configure l'environnement de développement avec :
- Une base de données PostgreSQL
- L'application Spring Boot
- Des volumes pour la persistance des données

### Construction et Exécution avec Docker
Pour construire et exécuter l'application avec Docker :

1. Compiler le projet pour générer le JAR :
   ```
   mvn clean package
   ```

2. Lancer l'application avec Docker Compose :
   ```
   cd clean-architecture-infrastructure
   docker-compose up -d
   ```

Cette commande va :
- Construire l'image Docker de l'application à partir du Dockerfile
- Démarrer un conteneur PostgreSQL
- Démarrer l'application en la connectant à la base de données

L'application sera accessible à l'adresse : http://localhost:8082/api/v1/comptes

## Configuration Kubernetes
Le projet inclut des fichiers de configuration Kubernetes pour déployer l'application sur Minikube.

### Prérequis
- Minikube installé et configuré
- kubectl installé
- Ingress controller activé dans Minikube

### Déploiement sur Minikube avec Makefile

Le projet inclut un Makefile qui automatise le processus de déploiement sur Minikube.

#### Utilisation du Makefile

1. Afficher l'aide du Makefile :
   ```
   make help
   ```

2. Démarrer Minikube et activer Ingress :
   ```
   make minikube-start
   ```

3. Construire et déployer l'application en une seule commande :
   ```
   make all
   ```
   Cette commande exécute les étapes suivantes :
   - Compilation du projet avec Maven
   - Construction de l'image Docker
   - Chargement de l'image dans Minikube
   - Déploiement de l'application avec Kubernetes

4. Configurer le fichier hosts pour l'Ingress :
   ```
   make setup-hosts
   ```
   Suivez ensuite les instructions affichées pour exécuter la commande PowerShell en tant qu'administrateur.

5. Afficher l'URL de l'application :
   ```
   make show-url
   ```

6. Nettoyer les ressources :
   ```
   make clean
   ```

#### Déploiement manuel (alternative)

Si vous préférez ne pas utiliser le Makefile, vous pouvez suivre ces étapes manuelles :

1. Démarrer Minikube :
   ```
   minikube start
   ```

2. Activer l'addon Ingress :
   ```
   minikube addons enable ingress
   ```

3. Construire l'image Docker de l'application :
   ```
   mvn clean package
   docker build -t clean-architecture-app:1.0 .
   ```

4. Charger l'image dans Minikube :
   ```
   minikube image load clean-architecture-app:1.0
   ```

5. Déployer l'application avec Kustomize :
   ```
   kubectl apply -k kubernetes/
   ```

6. Ajouter l'entrée dans le fichier hosts pour l'Ingress (Windows) :
   ```
   # Exécuter cette commande dans PowerShell en tant qu'administrateur
   Add-Content -Path 'C:\Windows\System32\drivers\etc\hosts' -Value "$(minikube ip) clean-architecture.local"
   ```

7. Accéder à l'application :
   ```
   http://clean-architecture.local/api/v1/comptes
   ```

### Vérification du déploiement

Pour vérifier que tous les composants sont correctement déployés :

```
kubectl get all
kubectl get ingress
```

### Suppression du déploiement

Pour supprimer le déploiement :

```
kubectl delete -k kubernetes/
```

## Dépannage

### Erreur "no main manifest attribute, in app.jar"
Si vous rencontrez cette erreur lors du déploiement sur Minikube, cela signifie que le fichier JAR n'a pas d'attribut Main-Class dans son manifeste. Pour résoudre ce problème :

1. Assurez-vous que le plugin Spring Boot Maven est correctement configuré dans le fichier pom.xml du module infrastructure :
   ```xml
   <plugin>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-maven-plugin</artifactId>
       <configuration>
           <mainClass>org.example.InfraBootApplication</mainClass>
           <layout>JAR</layout>
           <executable>true</executable>
       </configuration>
       <executions>
           <execution>
               <goals>
                   <goal>repackage</goal>
               </goals>
           </execution>
       </executions>
   </plugin>
   ```

2. Reconstruisez l'application avec Maven :
   ```
   mvn clean package
   ```

3. Reconstruisez l'image Docker et redéployez sur Minikube :
   ```
   make docker-build minikube-load deploy
   ```

## Contribution
Les contributions sont les bienvenues ! N'hésitez pas à soumettre des pull requests ou à ouvrir des issues pour améliorer ce projet.

## Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.
