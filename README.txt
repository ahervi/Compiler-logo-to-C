README du projet de compilateur LOGO -> C.

Auteur : Antoine Hervieu

Environnement de travail :
Java 8 jre1.8.0_112
Windows 8.1 
ANTLR 4.6



Avancée :
Tout fonctionne, je suis allé jusqu'à la fin du TP avec les types et les listes.
Remarques :
1) koch.logo, blue7.logo et blue12.logo avaient tous le même petit problème au niveau de la ligne 'make "count :count -1'
qui n'est pas reconnue par l'analyseur lexical et n'est pas reconnue non plus par le compilateur logo de référence en ligne.
Je suis donc partis du principe qu'il s'agissait d'une petite erreur d'inattention.
'make "count :count - 1' fonctionnait mais j'ai préféré rendre l'analyseur .g4 plus puisant afin que 'make "count :count-1' fonctionne.
J'y reviens un peu plus loin dans ce README.txt.

2) Je ne sais pas si c'est voulu mais blue5.logo est un if suivi de deux block ce qui ne correspond pas à
une syntaxe correcte selon le compilateur logo de référence et le nôtre, ce qui génère naturellement une
erreur de syntaxe. Je n'ai pas touché au fichier de test.


Stratégie de gestion des erreurs :
Les erreurs sont dans le package error :

AssignError gère les erreurs du à des variables en double.

CompilationError s'occupe d'erreurs dans gen() pouvant se produire après l'analyse sémantique.

FormalsDuplicateError gèere les problèmes de formels avec le même nom.

FuncCallLookupError gère les erreurs lors de l'enregistrement des fonctions utilisé par FuncCall lors de l'analyse sémantique.

NumbreOfArgumentsError gère les erreurs apparaissant lors de l'appel de fonctions avec un mauvais nombre d'arguments.

ProcedureAlreadyDefinedError gère les erreurs lors de l'analyse sémantique dues à une redéfinition de procédure.

TypeError gère les erreurs de type pour les opérateurs binaires et pour if et ifelse.

TypeModifiedError gère les erreurs pour les types des méthodes et variables.

UndefinedProcError gère les erreurs lors d'appels de procédures inconnues.

UndefinedvariableError gère les erreurs lors d'appels de variables inconnues.


Liberté et justification :

- Enrichissement de l'analyseur lexical .g4 :
Le fichier koch.logo est erroné car le compilateur logo de référence ne peut pas le compiler.
En effet la ligne 'make "count :count -1' est ambigüe car -1 est vu comme un moins unaire. L'erreur du compilateur logo de référence est 'Don't know what to do with -1'. Mettre 'make "count :count - 1' est correct et reconnu par le fichier .g4 donné mais me semblait moins joli que 'make "count :count-1' plus proche de la logique logo où les espaces sont important pour séparer les instructions. Malheureusement cette syntaxe est reconnu par le compilateur logo de référence mais pas par Logo.g4.

Pour faire cela en regardant ce qui fonctionnait sur le compilateur logo de référence. J'ai enrichi le fichier .g4 en changeant la définition de NUMBER :

NUMBER
    :   ' -'? INT '.' INT EXP?                                      // 1.35, 1.35E-9, 0.3, -4.5
    | '(' ('-' | ' -' | '- ')? INT '.' INT EXP? ( ')' | ' )' )      // (-1.35), ( -1.35E-9), (- 0.3), (- 4.5)
    |   ' -'? INT EXP                                               // 1e10 -3e4
    |   '(' ('-' | ' -' | '- ')? INT EXP ( ')' | ' )' )             // (1e10) ( -3e4), ( -3e4 ), (-3e4 )
    |   ' -'? INT                                                   // -3, 45
    |   '(' ('-' | ' -' | '- ')? INT ( ')' | ' )' )                 // (-3), ( -45), (- 45 ), (-45 ) 
    ;
En effet un moins unaire est toujours précédé d'un espace ou d'une parenthèse ouvrante.
Ce qui nous permet finalement de lever l'ambiguité et d'écrire 'make "count :count-1' dans koch.logo. La génération de l'analyseur lexical m'a fait passer d'ANTLR 4.7 à 4.6, je ne sais pas pourquoi.

- ArrayList à la place de Vector
Les méthodes check1, check2, check3 utilisait la classe Vector. J'ai utilisé la classe ArrayList à la place car
Vector est une vieille classe Java moins performante pour son côté 'liste' (ajout et retrait d'éléments) que la classe ArrayList plus récente.
Éventuellement Vector est synchronized contrairement à ArrayList mais cela n'a pas d'utilité ici.
Source : https://stackoverflow.com/questions/2986296/what-are-the-differences-between-arraylist-and-vector


- Le type VOID :
J'ai ajouté un type VOID à l'enum Type pour gérer le type de retour des procédures et le cas où une procédure n'avait pas d'arguments.


- Pour que 'show xcor' fonctionne j'ai ajouté une classe XCor dans ast qui étend Expr, j'ai aussi modifié la grammaire et ASTEmmitter en conséquence.
En effet par défaut 'show' NAME n'est pas reconnu par la grammaire. Idem pour ycor.

