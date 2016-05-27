====================
    DESCRIPTION
====================

Packet forwarding rule compiler for switch pipelined architecture. It generates an optimized packet 
classification scheme from a set of openflow rules. It also supports rule updates.   

====================
	 EXECUTION 
====================
To get the code of the compiler :
		# git clone https://HamadiSE@bitbucket.org/HamadiSE/network_compiler.git
To run the compiler :

		# java -jar "compiler.jar" -[cmd] [rcf_rule_file] [rcf_newrule_file] [compile_thr]  [result_file]
									
		example of compilation : #java -jar "compiler.jar" -compile input/test.rcf input/test_up.rcf 2  serial
		example of update      : #java -jar "compiler.jar" -update input/test.rcf input/test_up.rcf 2  serial

Where parameters are :
	cmd 				"compile" to compile rules / "update" to update the packet classification scheme
	rcf_rule_file			input file (.rcf) that includes rules to-descriptor association, mandatory for 
					both rule compilation and rule update 
	rcf_newrule_file		input file (.rcf) that includes new rules to add to the packet classification, 
					mandatory for rule update
	compile_thr			threshold for concept selection process
	result_file			output file that presents the packet classification scheme (trie)
	
	
====================
	 ENVIRONMENT 
====================

* java version "1.7.0_95"
* OpenJDK Runtime Environment (IcedTea 2.6.4) (7u95-2.6.4-0ubuntu0.15.10.2)
* OpenJDK 64-Bit Server VM (build 24.95-b01, mixed mode)    


* coron-0.8 - Coron System: a symbolic data-mining platform
for more information visit http://coron.loria.fr/site/index.php

* GNU/Linux envrionment (tested over 4.2.0-35-generic)

========================
   FILES AND FOLDERS
========================

bin/				- Compiled files (.class) with javac from java source files.
src/				- Compiler source files.
coron-08/			- Tools and sripts for lattice generation. 
input/				- RCF files for rule to compile and to update.
lattice/			- Temporary files for lattice generation.
output/				- Generated JSON files after rule compilation/update.
tmp/				- Temporary files for trie serialization/deserialization.
compiler.jar			- Compiler runnable file


========================
	   SRC FILES
========================

handler/
	| Compiler.java						- The main class for executing the compilation and the update process. It 									follows the process:
								1- Read the parameters of the program.
								2- Generate the lattice using coron program. Coron uses the algorithm dtouch 									and the method snow for lattice computation.
								3- The compiler read the rules from ().RCF) file it will store them in a Map 									structure. The read process is implemented in RuleHandler.java
								4- Extract the relevant concepts from the lattice. The process of extraction 									is implemented in TrieHandler.java
								5- Generate the pipeline using the chosen concepts. The function is 									implemented in EntryHandler.java
								6-  [*] If the first argument is "-compile". The compiler will build the Trie 									using CreateTrie() Methods. And it will serialize the object Trie in order to 									use for update. The serialized object is stored under. /tmp/ 
								     [*]I f the first argument is "-update". The compiler will first 									deserialize the original trie. After that It will call the update function 									UpdateTrie() implemented in Trie.java class.
	| EntryHandler.java					- Implements functions responsible for handling entries and generates the set 									of sub tables. It implements 4  functions:
								1-generateSubsetTables() : it takes as parametres : Lattice, the list of 									indexlist, and the list of rules. The function associates the entries to 									their sub tables.
								2-generateIndexPipeline() : requires a list of indexes and generates a list 									of pipelines.
								3-appGenerator() : This function will generate all the information required 									to identify the application. Those information includes:
									[*] The id of the application.
									[*] A map of tables. 										[*] The entries in each sub table.
									[*] The chaining between tables, it's a matrix that presents the 										     possible go_to actions.				
	| LatticeHandler.java					- Implements functions to work on lattice. The important function is concept 									selection. The class implement a set of function to  select the significant 									concepts. 
								1- ReadLatticeXML() : takes as parameters the xml file that presents the 									lattice. The file is generated using coron in the compiler.java class. The 									function will create a lattice structure from this xml.
								2- getSignificantConcept : takes as parameters the lattice. It will evaluate 									the lattice and choose the concepts based on:
									[*] minimal overlap.
									[*] minimal keys. 										[*] total inclusiveness of rules.
	| RuleHandler.java					- Implements function to read rules from (.rcf) file. 						
	| TrieHandler.java					- Implements function to generate the indexes. The class implements the 								following functions:
								1-generateIndex() : Using the list of significant concepts, this function 									will calculate the indexes uses for each concepts.
								2-updateIndex() : to handle the update process.
	
struct/
	| Application.java					- Define the structure of the application. An application is defined by:
									[*] The id of the application.
									[*] A map of tables.
									[*] The entries in each sub table.
									[*] The chaining between tables, it's a matrix that presents the 										possible go_to actions.
	| AttributeType.java				- Define the supported field type. The supported fields are:
									[*]IngressPort,
									[*]IngressVPort,
									[*]EtherSrc,
									[*]EtherDst,
									[*]EtherType,
									[*]VlanId,
									[*]IpSrc,
									[*]IpDst,
									[*]PortSrc,
									[*]PortDst;
	| ConceptType.java					- Define the position of a concept in the lattice. The position could be:
									[*]TOP,
									[*]BOTTOM,
									[*]INNER;
	| Entry.java						- Define the entry selection. An entry is defined by:
									[*]an id
									[*]a matchfield
									[*]the next table
									[*]The input metadata: received from previous entry
									[*]The output metadata: to be sent to the following entry.
	| Index.java						- Define the index structure. It's defined by :
									[*]an id
									[*]A list of keys
									[*]The list of rules associated with the index.
	| Lat_Attribute.java				- Define the matchfield structure.
									[*]an id
									[*]The field type: It should be one the types defined in Attribute.java
									[*]value : The value is presented as a string type.
	| Lat_Concept.java					- Define the concept structure. It includes all the information required to lattice analysis:
									[*]an id
									[*]support : the support of the concept is the number of rules in this concept.
									[*]level : this characteristic is related to the level of the concept in the Hasse diagram.
									[*]type : This indicates whether the concept is on top, bottom or inner.
									[*]extent : A list of rules in the concepts
									[*]intent : A list of matchfields in the concepts.
									[*]parents : A list of parent of this concepts using the partial order relation. The parents can be viewed in Hasse diagram.
									[*]children : A list of children of this concepts using the partial order relation. The children can be viewed in Hasse diagram.
									[*]generators : A concept may have a set of generator. In the evaluation process, the characteristics of generators are used to evaluate a concept.
	| Lat_Object.java					- Define the rule. it includes an id and the number of the rule. 												
	| Lattice.java 						- Define the Lattice structure. The important parameters are:
									[*]objects : A map of all the matchfields.
									[*]attributes : A map of all the rules. 
									[*]concepts: A map of all the concepts.
									[*]nbInnerConcept : The number of concepts with inner characteristics.
									[*]topConceptId : The id of the top concept. This concept includes all the rules.
									[*]bottomConceptId : The id of the bottom concept. This concept 	includes all the matchfields and 0 rule.
	| MatchType.java					- Define the search used for the matchfields. It has two possible value. The first is Exact search and the second is LPM.
	| Pipeline.java						- Define the pipeline structure. It has an id and a map of tables.
	| Rule.java						- Define the rule structure. A rule is a set of matchfields and an id to distinguish the rule.
	| SubsetTable.java					- Define the subset of rules structures. It contains an id and a list of rules.
	| Table.java						- Define the table structure. A table is defined by the field and the type of search supported.
	| Trie.java							- Define the Trie structure. It includes:
									[*]id : The id of the node.
									[*]leaf : A Boolean to indicate if the node is a leaf.
									[*]key : The key of the node. The key is a matchfield.
									[*]childs : The list of child nodes.
									[*]parent : The parent node.
									[*]level : an int of the level.
									[*]ruleSubset : the rule subset of the node. This is not null only if the node is leaf.
								- The class Trie.java implements a set of functions to manipulate the trie.
									1- CompressTrie() : It is used to compress the trie. The compression
									occurs only if a node has one child
									2- LookupIndex() : It finds the best match for a node.
									3- UpdateTrie() : To update the trie.
									4- FormatTrie() :  Used to format the trie in a readable format.
