1. Postman : Le Client (L'initiateur)Postman intervient au tout début et à la toute fin. C'est lui qui remplace l'écran de ton téléphone ou le site web que l'utilisateur final utiliserait.Son rôle : Il simule un utilisateur qui veut faire une action.Où il se connecte : Il tape à la porte de ton Controller.Action : Quand tu cliques sur "Send" dans Postman, il fabrique un paquet (une requête HTTP) contenant l'URL (ex: /api/employees) et parfois du JSON.Fin du voyage : Une fois que ton code a fini de travailler, Postman reçoit la réponse et te l'affiche proprement (le statut 200 OK et les données).2. H2 : La Base de Données (Le coffre-fort)H2 intervient tout au bout de la chaîne, derrière ton Repository. C'est là que les données sont réellement écrites sur le "disque" (ou en mémoire).Son rôle : Stocker les informations de manière persistante. Sans H2, dès que tu éteins ton ordinateur, tous tes employés disparaissent.Où il se connecte : Il communique avec ton Repository.Action : Quand ton Repository appelle .save(), il envoie une commande SQL à H2. H2 vérifie si la table existe, insère les données et renvoie une confirmation (avec l'ID généré).Le Schéma du Flux CompletVoici comment les deux collaborent avec ton code :Postman envoie un signal $\rightarrow$ Controller (Porte d'entrée).Controller $\rightarrow$ Service (Traitement).Service $\rightarrow$ Repository (Ordre de stockage).Repository envoie du SQL $\rightarrow$ Base H2 (Stockage réel).H2 répond au Repository.Le Service valide.Le Controller renvoie le résultat à Postman.Résumé technique rapide :Postman est le "Frontend" temporaire : il parle le langage des URLs et du JSON.H2 est le "Storage" : il parle le langage du SQL.Ton code Spring Boot est le "Middleware" : il fait la traduction entre les deux pour que tout le monde se comprenne.C'est pour ça que si tu n'allumes pas ton application dans IntelliJ, Postman affichera une erreur "Connection Refused" : la porte du Controller est fermée !Est-ce que tu as réussi à ouvrir la console H2 dans ton navigateur (souvent sur localhost:8080/h2-console) pour voir tes tables en direct.

Model (Employee) : C'est la représentation de vos données. Il définit à quoi ressemble un employé (id, nom, poste, salaire) dans le code et dans la table SQL.
Repository (EmployeeRepository) : C'est la couche d'accès aux données. Elle "parle" à la base de données H2. En héritant de JpaRepository, elle possède déjà les méthodes pour sauvegarder ou supprimer.
Service (EmployeeService) : C'est ici que se trouve la logique métier. Il fait les calculs ou les vérifications avant de passer les données au Repository.
Controller (EmployeeController) : C'est la porte d'entrée. Il reçoit les requêtes du monde extérieur et renvoie les réponses. Il ne manipule jamais la base de données directement ; il demande au Service de le faire

2. Le fonctionnement des API RESTUne API REST fonctionne comme un menu au restaurant : elle définit des ressources et des actions standardisées pour les manipuler via le protocole HTTP.Ressources : Tout est considéré comme une ressource identifiée par une URL unique, ici /api/employees.Verbes HTTP (Les Actions) :GET : Pour lire/récupérer des données.POST : Pour créer une nouvelle ressource.PUT : Pour modifier une ressource existante.DELETE : Pour supprimer une ressource.

notre application = "serveur" ici 

Le Client envoie du JSON au Controller, qui demande au Service de traiter l'info, lequel utilise le Repository pour la stocker dans le Model en base de données.

=====tres important !!!!!!! :

Ce que vous avez ajouté (explications pour le prof) :
L'Automatisme : Dans addEmployee, vous avez ajouté une sécurité qui met la date d'embauche à "aujourd'hui" si elle n'est pas précisée.

La Traçabilité (Audit) : La méthode promoteEmployee est la preuve que vous maîtrisez la logique métier. Vous expliquez : "Au lieu de simplement écraser le titre, mon service fait une sauvegarde de l'état précédent. C'est essentiel pour l'historique de carrière."

L'Abstraction : Le service cache la complexité au contrôleur. Le contrôleur demande juste "promeus cet employé", et le service s'occupe de gérer les 3 champs impactés (position, previousPosition, lastPromotionDate).


===========================================================================PROJET APPLICATIONS REPARTIES================================================================================


L'architecture d'un projet Spring Boot moderne, comme ce système de gestion de tournois, repose sur une séparation stricte des responsabilités en couches superposées. Cette approche permet de maintenir un code propre, testable et évolutif.  Voici le détail du fonctionnement de chaque composant dans cet écosystème :
1. La structure en couches (Layered Architecture)Le flux d'une requête suit un chemin précis à travers quatre niveaux principaux : 
 Controller Layer (API Gateway) : C'est le point d'entrée. Utilisant @RestController, cette couche expose les points d'accès (endpoints) comme /api/v1/tournaments. Son seul rôle est de réceptionner la requête HTTP, de valider sommairement les entrées et de passer la main à la couche de service. 
 Service Layer : C'est ici que réside toute l'intelligence. Les classes annotées @Service orchestrent la logique métier (ex: vérifier si un tournoi est complet avant d'ajouter une équipe). C'est aussi ici que s'effectue la transformation cruciale entre les Entities et les DTOs. 
 Repository Layer : Grâce à Spring Data JPA, cette couche utilise des interfaces qui étendent JpaRepository. Elle permet de communiquer avec la base de données sans écrire de requêtes SQL complexes, en utilisant des méthodes comme save() ou findById().  
 Database Layer : Dans ce projet, H2 est utilisé comme base de données "In-Memory". Elle est idéale pour le développement car elle est rapide et ne nécessite pas d'installation de serveur de base de données externe. 
2. Le "Shared Kernel" : Le cœur du DomaineCette zone regroupe les objets qui circulent entre les couches.  Les Entities vs DTOsUne règle fondamentale dans cette architecture est qu'une Entity ne doit jamais sortir de la couche Service vers le Controller.  ConceptDescriptionRôle TechniqueEntityReprésente une table en base de données H2.  Annotée avec @Entity, gérée par JPA.  DTOObjet léger de transfert de données.  Utilisé pour l'exposition JSON via l'API. 

 Sécurité et Intégrité
Enums : Les types comme TournamentStatus (PENDING, ONGOING, FINISHED) garantissent que seules des valeurs prédéfinies sont acceptées, évitant les erreurs de saisie en base. 
Exceptions Personnalisées : Au lieu de renvoyer une erreur système illisible, le projet utilise des classes comme TournamentFullException. Un composant global (@ControllerAdvice) intercepte ces erreurs pour renvoyer un code HTTP propre (ex: 400 Bad Request) au client.  
3. Exemple de flux de donnéesPour la création d'un match via POST /api/matches :  Client : Envoie un JSON avec les détails du match (Team A, Team B).
MatchController : Reçoit le MatchDTO.
  MatchService :Vérifie si les équipes existent via TeamRepository.  Applique la logique métier (ex: vérifier la disponibilité).Convertit le DTO en une Entity Match.  Demande au MatchRepository de sauvegarder.  
MatchRepository/JPA : Génère l'ID et insère la ligne dans la table match de la base H2. 
 4. Résumé des Endpoints principauxLe système est organisé autour de quatre ressources majeures :  Tournaments : Gestion du cycle de vie (création, modification, statut).  Teams : Inscription et affectation aux tournois.  Players : Gestion des effectifs par équipe.  Matches : Planification et mise à jour des scores en temps réel via des requêtes PATCH
