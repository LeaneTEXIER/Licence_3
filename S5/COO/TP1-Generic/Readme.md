# Léane Texier    
          
           
# Compiler les sources du projet     
Se placer dans le dossier src.   
Taper: javac generics/* -d ../classes (Cela va compiler les classes présentes dans le dossier generics ainsi que ces sous-classes (ainsi que la classe présente dans le dossier scanner)).   
              
             
# Compiler et executer les tests   
A faire obligatoirement après avoir compilé les classes!   
Se placer dans le dossier TP1-Generic.   
Taper: javac -classpath test-1.7.jar tests/generic/CollectorTest.java -d classes/   
Un nouveau fichier CollectorTest.class a été créé si tout s'est bien passé.   
Taper: java -jar test-1.7.jar generic.CollectorTest   
Les tests de CollectorTest sont alors effectués.   
               
               
# Exectuer les programmes Main   
Normalement toutes les classes (nécessaires) ont toutes été compilées avant. Si ce n'est pas le cas, faire "#Compiler les sources du projet".   
Se placer dans le dossier classes.   
# ListChoser   
Taper: java generics.ListChoser   
Le main se lance.   
# VegetableListChoser    
Taper: java generics.VegetableListChoser   
Le main se lance.   
# ClonableVegetableListChoser   
Taper: java generics.ClonableVegetableListChoser   
Le main se lance.   
# Collector   
Taper: java generics.Collector   
Le main se lance.   
             
             
# Générer le fichier .jar   
Se placer dans le dossier classes si ce n'est pas le cas.   
Taper: jar cvf ../generics.jar generics scanner   
              
            
# Executer le jar (+exemples)   
Se placer dans le dossier TP1-Generic.   
# ListChoser    
Taper: java -classpath generics.jar generics.ListChoser    
# VegetableListChoser    
Taper: java -classpath generics.jar generics.VegetableListChoser    
# ClonableVegetableListChoser   
Taper: java -classpath generics.jar generics.ClonableVegetableListChoser    
# Collector   
Taper: java -classpath generics.jar generics.Collector    