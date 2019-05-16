/* Exemple 1 */
package exemple1;

%%

%unicode

/* MOT_USUEL=[:letter:]+ */
ENTIER_SIMPLE=[0-9]+
OPERATEUR=[-+*/]
IDENTIFICATEUR=[A-Za-z][A-Za-z0-9]*

%% 

{OPERATEUR}|{ENTIER_SIMPLE}|{IDENTIFICATEUR}
      {return new Yytoken(yytext());}


[^-+*/0-9A-Za-z]+
      {}  
