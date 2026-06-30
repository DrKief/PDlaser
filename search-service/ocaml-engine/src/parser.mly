%{
  open Ast
%}

%token AND OR NOT
%token LPAREN RPAREN
%token COLON COMMA
%token TILDE
%token PLUS MINUS
%token <string> QUOTED_STRING
%token <string> BARE_WORD
%token <int> INT
%token EOF


%start <Ast.expr> parse_query
%%

parse_query:
  | e = or_expr; EOF { e }

or_expr:
  | l = and_expr; OR; r = or_expr { Or(l, r) }
  | e = and_expr { e }

and_expr:
  | l = and_expr; AND; r = clause { And(l, r) }
  | l = and_expr; r = clause { And(l, r) }
  | e = clause { e }

clause:
  | NOT; e = clause { Not(e) }
  | MINUS; e = clause { Not(e) }
  | PLUS; e = clause { e }
  | e = simple { e }

simple:
  | w = BARE_WORD { Tag(w) }
  | q = QUOTED_STRING { Tag(q) }
  | TILDE; vs = value { Semantic(vs) }
  | f = BARE_WORD; COLON; vs = value { FieldOp(f, vs) }
  | LPAREN; e = or_expr; RPAREN { Group(e) }
  | n = INT { Tag(string_of_int n) }

value:
  | v = value; COMMA; w = BARE_WORD { v @ [w] }
  | v = value; COMMA; q = QUOTED_STRING { v @ [q] }
  | w = BARE_WORD { [w] }
  | q = QUOTED_STRING { [q] }
