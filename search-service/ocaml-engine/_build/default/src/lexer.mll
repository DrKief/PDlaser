{
open Parser
}

let digit = ['0'-'9']
let letter = ['a'-'z' 'A'-'Z']
let word_char = letter | digit | '_'

rule token = parse
  | [' ' '\t' '\n' '\r']+  { token lexbuf }
  | "AND"                   { AND }
  | "OR"                    { OR }
  | "NOT"                   { NOT }
  | "("                     { LPAREN }
  | ")"                     { RPAREN }
  | ":"                     { COLON }
  | ","                     { COMMA }
  | "~"                     { TILDE }
  | "+"                     { PLUS }
  | "-"                     { MINUS }
  | '"'                     { read_string (Buffer.create 64) lexbuf }
  | digit+ as n             { INT (int_of_string n) }
  | word_char+ as w         { BARE_WORD w }
  | eof                     { EOF }
  | _ as c                  { failwith (Printf.sprintf "Unexpected character: %c" c) }

and read_string buf = parse
  | '"'                     { QUOTED_STRING (Buffer.contents buf) }
  | '\\' '"'                { Buffer.add_char buf '"'; read_string buf lexbuf }
  | '\\' '\\'               { Buffer.add_char buf '\\'; read_string buf lexbuf }
  | _ as c                  { Buffer.add_char buf c; read_string buf lexbuf }
  | eof                     { failwith "Unterminated string literal" }
