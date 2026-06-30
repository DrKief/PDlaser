
(* The type of tokens. *)

type token = 
  | TILDE
  | RPAREN
  | QUOTED_STRING of (string)
  | PLUS
  | OR
  | NOT
  | MINUS
  | LPAREN
  | INT of (int)
  | EOF
  | COMMA
  | COLON
  | BARE_WORD of (string)
  | AND

(* This exception is raised by the monolithic API functions. *)

exception Error

(* The monolithic API. *)

val parse_query: (Lexing.lexbuf -> token) -> Lexing.lexbuf -> (Ast.expr)
