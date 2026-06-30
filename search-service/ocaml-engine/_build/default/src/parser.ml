
module MenhirBasics = struct
  
  exception Error
  
  let _eRR =
    fun _s ->
      raise Error
  
  type token = 
    | TILDE
    | RPAREN
    | QUOTED_STRING of 
# 10 "src/parser.mly"
       (string)
# 17 "src/parser.ml"
  
    | PLUS
    | OR
    | NOT
    | MINUS
    | LPAREN
    | INT of 
# 12 "src/parser.mly"
       (int)
# 27 "src/parser.ml"
  
    | EOF
    | COMMA
    | COLON
    | BARE_WORD of 
# 11 "src/parser.mly"
       (string)
# 35 "src/parser.ml"
  
    | AND
  
end

include MenhirBasics

# 1 "src/parser.mly"
  
  open Ast

# 47 "src/parser.ml"

type ('s, 'r) _menhir_state = 
  | MenhirState00 : ('s, _menhir_box_parse_query) _menhir_state
    (** State 00.
        Stack shape : <empty>.
        Start symbol: parse_query. *)

  | MenhirState01 : (('s, _menhir_box_parse_query) _menhir_cell1_TILDE, _menhir_box_parse_query) _menhir_state
    (** State 01.
        Stack shape : TILDE.
        Start symbol: parse_query. *)

  | MenhirState09 : (('s, _menhir_box_parse_query) _menhir_cell1_PLUS, _menhir_box_parse_query) _menhir_state
    (** State 09.
        Stack shape : PLUS.
        Start symbol: parse_query. *)

  | MenhirState10 : (('s, _menhir_box_parse_query) _menhir_cell1_NOT, _menhir_box_parse_query) _menhir_state
    (** State 10.
        Stack shape : NOT.
        Start symbol: parse_query. *)

  | MenhirState11 : (('s, _menhir_box_parse_query) _menhir_cell1_MINUS, _menhir_box_parse_query) _menhir_state
    (** State 11.
        Stack shape : MINUS.
        Start symbol: parse_query. *)

  | MenhirState12 : (('s, _menhir_box_parse_query) _menhir_cell1_LPAREN, _menhir_box_parse_query) _menhir_state
    (** State 12.
        Stack shape : LPAREN.
        Start symbol: parse_query. *)

  | MenhirState15 : (('s, _menhir_box_parse_query) _menhir_cell1_BARE_WORD, _menhir_box_parse_query) _menhir_state
    (** State 15.
        Stack shape : BARE_WORD.
        Start symbol: parse_query. *)

  | MenhirState21 : (('s, _menhir_box_parse_query) _menhir_cell1_and_expr, _menhir_box_parse_query) _menhir_state
    (** State 21.
        Stack shape : and_expr.
        Start symbol: parse_query. *)

  | MenhirState22 : ((('s, _menhir_box_parse_query) _menhir_cell1_and_expr, _menhir_box_parse_query) _menhir_cell1_OR, _menhir_box_parse_query) _menhir_state
    (** State 22.
        Stack shape : and_expr OR.
        Start symbol: parse_query. *)

  | MenhirState24 : ((('s, _menhir_box_parse_query) _menhir_cell1_and_expr, _menhir_box_parse_query) _menhir_cell1_AND, _menhir_box_parse_query) _menhir_state
    (** State 24.
        Stack shape : and_expr AND.
        Start symbol: parse_query. *)


and ('s, 'r) _menhir_cell1_and_expr = 
  | MenhirCell1_and_expr of 's * ('s, 'r) _menhir_state * (Ast.expr)

and ('s, 'r) _menhir_cell1_value = 
  | MenhirCell1_value of 's * ('s, 'r) _menhir_state * (string list)

and ('s, 'r) _menhir_cell1_AND = 
  | MenhirCell1_AND of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_BARE_WORD = 
  | MenhirCell1_BARE_WORD of 's * ('s, 'r) _menhir_state * 
# 11 "src/parser.mly"
       (string)
# 114 "src/parser.ml"


and ('s, 'r) _menhir_cell1_LPAREN = 
  | MenhirCell1_LPAREN of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_MINUS = 
  | MenhirCell1_MINUS of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_NOT = 
  | MenhirCell1_NOT of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_OR = 
  | MenhirCell1_OR of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_PLUS = 
  | MenhirCell1_PLUS of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_TILDE = 
  | MenhirCell1_TILDE of 's * ('s, 'r) _menhir_state

and _menhir_box_parse_query = 
  | MenhirBox_parse_query of (Ast.expr) [@@unboxed]

let _menhir_action_01 =
  fun l r ->
    (
# 27 "src/parser.mly"
                                  ( And(l, r) )
# 143 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_02 =
  fun l r ->
    (
# 28 "src/parser.mly"
                             ( And(l, r) )
# 151 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_03 =
  fun e ->
    (
# 29 "src/parser.mly"
               ( e )
# 159 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_04 =
  fun e ->
    (
# 32 "src/parser.mly"
                    ( Not(e) )
# 167 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_05 =
  fun e ->
    (
# 33 "src/parser.mly"
                      ( Not(e) )
# 175 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_06 =
  fun e ->
    (
# 34 "src/parser.mly"
                     ( e )
# 183 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_07 =
  fun e ->
    (
# 35 "src/parser.mly"
               ( e )
# 191 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_08 =
  fun l r ->
    (
# 23 "src/parser.mly"
                                  ( Or(l, r) )
# 199 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_09 =
  fun e ->
    (
# 24 "src/parser.mly"
                 ( e )
# 207 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_10 =
  fun e ->
    (
# 20 "src/parser.mly"
                     ( e )
# 215 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_11 =
  fun w ->
    (
# 38 "src/parser.mly"
                  ( Tag(w) )
# 223 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_12 =
  fun q ->
    (
# 39 "src/parser.mly"
                      ( Tag(q) )
# 231 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_13 =
  fun vs ->
    (
# 40 "src/parser.mly"
                      ( Semantic(vs) )
# 239 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_14 =
  fun f vs ->
    (
# 41 "src/parser.mly"
                                     ( FieldOp(f, vs) )
# 247 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_15 =
  fun e ->
    (
# 42 "src/parser.mly"
                                ( Group(e) )
# 255 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_16 =
  fun n ->
    (
# 43 "src/parser.mly"
            ( Tag(string_of_int n) )
# 263 "src/parser.ml"
     : (Ast.expr))

let _menhir_action_17 =
  fun v w ->
    (
# 46 "src/parser.mly"
                                    ( v @ [w] )
# 271 "src/parser.ml"
     : (string list))

let _menhir_action_18 =
  fun q v ->
    (
# 47 "src/parser.mly"
                                        ( v @ [q] )
# 279 "src/parser.ml"
     : (string list))

let _menhir_action_19 =
  fun w ->
    (
# 48 "src/parser.mly"
                  ( [w] )
# 287 "src/parser.ml"
     : (string list))

let _menhir_action_20 =
  fun q ->
    (
# 49 "src/parser.mly"
                      ( [q] )
# 295 "src/parser.ml"
     : (string list))

let _menhir_print_token : token -> string =
  fun _tok ->
    match _tok with
    | TILDE ->
        "TILDE"
    | RPAREN ->
        "RPAREN"
    | QUOTED_STRING _ ->
        "QUOTED_STRING"
    | PLUS ->
        "PLUS"
    | OR ->
        "OR"
    | NOT ->
        "NOT"
    | MINUS ->
        "MINUS"
    | LPAREN ->
        "LPAREN"
    | INT _ ->
        "INT"
    | EOF ->
        "EOF"
    | COMMA ->
        "COMMA"
    | COLON ->
        "COLON"
    | BARE_WORD _ ->
        "BARE_WORD"
    | AND ->
        "AND"

let _menhir_fail : unit -> 'a =
  fun () ->
    Printf.eprintf "Internal failure -- please contact the parser generator's developers.\n%!";
    assert false

include struct
  
  [@@@ocaml.warning "-4-37"]
  
  let _menhir_run_31 : type  ttv_stack. ttv_stack -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _v _tok ->
      match (_tok : MenhirBasics.token) with
      | EOF ->
          let e = _v in
          let _v = _menhir_action_10 e in
          MenhirBox_parse_query _v
      | _ ->
          _eRR ()
  
  let rec _menhir_run_01 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _menhir_stack = MenhirCell1_TILDE (_menhir_stack, _menhir_s) in
      let _menhir_s = MenhirState01 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | QUOTED_STRING _v ->
          _menhir_run_02 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_03 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
  and _menhir_run_02 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      let q = _v in
      let _v = _menhir_action_20 q in
      _menhir_goto_value _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_goto_value : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match _menhir_s with
      | MenhirState01 ->
          _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | MenhirState15 ->
          _menhir_run_16 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _menhir_fail ()
  
  and _menhir_run_04 : type  ttv_stack. ((ttv_stack, _menhir_box_parse_query) _menhir_cell1_TILDE as 'stack) -> _ -> _ -> _ -> ('stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match (_tok : MenhirBasics.token) with
      | COMMA ->
          let _menhir_stack = MenhirCell1_value (_menhir_stack, _menhir_s, _v) in
          _menhir_run_05 _menhir_stack _menhir_lexbuf _menhir_lexer
      | AND | BARE_WORD _ | EOF | INT _ | LPAREN | MINUS | NOT | OR | PLUS | QUOTED_STRING _ | RPAREN | TILDE ->
          let MenhirCell1_TILDE (_menhir_stack, _menhir_s) = _menhir_stack in
          let vs = _v in
          let _v = _menhir_action_13 vs in
          _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_05 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_value -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | QUOTED_STRING _v ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell1_value (_menhir_stack, _menhir_s, v) = _menhir_stack in
          let q = _v in
          let _v = _menhir_action_18 q v in
          _menhir_goto_value _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | BARE_WORD _v ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell1_value (_menhir_stack, _menhir_s, v) = _menhir_stack in
          let w = _v in
          let _v = _menhir_action_17 v w in
          _menhir_goto_value _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_goto_simple : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      let e = _v in
      let _v = _menhir_action_07 e in
      _menhir_goto_clause _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_goto_clause : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match _menhir_s with
      | MenhirState00 ->
          _menhir_run_20 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | MenhirState12 ->
          _menhir_run_20 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | MenhirState22 ->
          _menhir_run_20 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | MenhirState24 ->
          _menhir_run_25 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState21 ->
          _menhir_run_26 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState11 ->
          _menhir_run_27 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState10 ->
          _menhir_run_28 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState09 ->
          _menhir_run_29 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | _ ->
          _menhir_fail ()
  
  and _menhir_run_20 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      let e = _v in
      let _v = _menhir_action_03 e in
      _menhir_goto_and_expr _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_goto_and_expr : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState21
      | QUOTED_STRING _v_0 ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v_0 MenhirState21
      | PLUS ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState21
      | OR ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          let _menhir_stack = MenhirCell1_OR (_menhir_stack, MenhirState21) in
          let _menhir_s = MenhirState22 in
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | TILDE ->
              _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | QUOTED_STRING _v ->
              _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | PLUS ->
              _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | NOT ->
              _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | MINUS ->
              _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | LPAREN ->
              _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | INT _v ->
              _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | BARE_WORD _v ->
              _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | _ ->
              _eRR ())
      | NOT ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState21
      | MINUS ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState21
      | LPAREN ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState21
      | INT _v_4 ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v_4 MenhirState21
      | BARE_WORD _v_5 ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v_5 MenhirState21
      | AND ->
          let _menhir_stack = MenhirCell1_and_expr (_menhir_stack, _menhir_s, _v) in
          let _menhir_stack = MenhirCell1_AND (_menhir_stack, MenhirState21) in
          let _menhir_s = MenhirState24 in
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | TILDE ->
              _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | QUOTED_STRING _v ->
              _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | PLUS ->
              _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | NOT ->
              _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | MINUS ->
              _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | LPAREN ->
              _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
          | INT _v ->
              _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | BARE_WORD _v ->
              _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | _ ->
              _eRR ())
      | EOF | RPAREN ->
          let e = _v in
          let _v = _menhir_action_09 e in
          _menhir_goto_or_expr _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_08 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      let q = _v in
      let _v = _menhir_action_12 q in
      _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_09 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _menhir_stack = MenhirCell1_PLUS (_menhir_stack, _menhir_s) in
      let _menhir_s = MenhirState09 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | QUOTED_STRING _v ->
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | PLUS ->
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | NOT ->
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | MINUS ->
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | LPAREN ->
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | INT _v ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
  and _menhir_run_10 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _menhir_stack = MenhirCell1_NOT (_menhir_stack, _menhir_s) in
      let _menhir_s = MenhirState10 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | QUOTED_STRING _v ->
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | PLUS ->
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | NOT ->
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | MINUS ->
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | LPAREN ->
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | INT _v ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
  and _menhir_run_11 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _menhir_stack = MenhirCell1_MINUS (_menhir_stack, _menhir_s) in
      let _menhir_s = MenhirState11 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | QUOTED_STRING _v ->
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | PLUS ->
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | NOT ->
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | MINUS ->
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | LPAREN ->
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | INT _v ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
  and _menhir_run_12 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _menhir_stack = MenhirCell1_LPAREN (_menhir_stack, _menhir_s) in
      let _menhir_s = MenhirState12 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | QUOTED_STRING _v ->
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | PLUS ->
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | NOT ->
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | MINUS ->
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | LPAREN ->
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | INT _v ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
  and _menhir_run_13 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      let n = _v in
      let _v = _menhir_action_16 n in
      _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_14 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | COLON ->
          let _menhir_stack = MenhirCell1_BARE_WORD (_menhir_stack, _menhir_s, _v) in
          let _menhir_s = MenhirState15 in
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | QUOTED_STRING _v ->
              _menhir_run_02 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | BARE_WORD _v ->
              _menhir_run_03 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
          | _ ->
              _eRR ())
      | AND | BARE_WORD _ | EOF | INT _ | LPAREN | MINUS | NOT | OR | PLUS | QUOTED_STRING _ | RPAREN | TILDE ->
          let w = _v in
          let _v = _menhir_action_11 w in
          _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_03 : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      let w = _v in
      let _v = _menhir_action_19 w in
      _menhir_goto_value _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_goto_or_expr : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match _menhir_s with
      | MenhirState12 ->
          _menhir_run_18 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState22 ->
          _menhir_run_23 _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok
      | MenhirState00 ->
          _menhir_run_31 _menhir_stack _v _tok
      | _ ->
          _menhir_fail ()
  
  and _menhir_run_18 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_LPAREN -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      match (_tok : MenhirBasics.token) with
      | RPAREN ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell1_LPAREN (_menhir_stack, _menhir_s) = _menhir_stack in
          let e = _v in
          let _v = _menhir_action_15 e in
          _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_23 : type  ttv_stack. ((ttv_stack, _menhir_box_parse_query) _menhir_cell1_and_expr, _menhir_box_parse_query) _menhir_cell1_OR -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_OR (_menhir_stack, _) = _menhir_stack in
      let MenhirCell1_and_expr (_menhir_stack, _menhir_s, l) = _menhir_stack in
      let r = _v in
      let _v = _menhir_action_08 l r in
      _menhir_goto_or_expr _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_25 : type  ttv_stack. ((ttv_stack, _menhir_box_parse_query) _menhir_cell1_and_expr, _menhir_box_parse_query) _menhir_cell1_AND -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_AND (_menhir_stack, _) = _menhir_stack in
      let MenhirCell1_and_expr (_menhir_stack, _menhir_s, l) = _menhir_stack in
      let r = _v in
      let _v = _menhir_action_01 l r in
      _menhir_goto_and_expr _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_26 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_and_expr -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_and_expr (_menhir_stack, _menhir_s, l) = _menhir_stack in
      let r = _v in
      let _v = _menhir_action_02 l r in
      _menhir_goto_and_expr _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_27 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_MINUS -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_MINUS (_menhir_stack, _menhir_s) = _menhir_stack in
      let e = _v in
      let _v = _menhir_action_05 e in
      _menhir_goto_clause _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_28 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_NOT -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_NOT (_menhir_stack, _menhir_s) = _menhir_stack in
      let e = _v in
      let _v = _menhir_action_04 e in
      _menhir_goto_clause _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_29 : type  ttv_stack. (ttv_stack, _menhir_box_parse_query) _menhir_cell1_PLUS -> _ -> _ -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _tok ->
      let MenhirCell1_PLUS (_menhir_stack, _menhir_s) = _menhir_stack in
      let e = _v in
      let _v = _menhir_action_06 e in
      _menhir_goto_clause _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
  
  and _menhir_run_16 : type  ttv_stack. ((ttv_stack, _menhir_box_parse_query) _menhir_cell1_BARE_WORD as 'stack) -> _ -> _ -> _ -> ('stack, _menhir_box_parse_query) _menhir_state -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match (_tok : MenhirBasics.token) with
      | COMMA ->
          let _menhir_stack = MenhirCell1_value (_menhir_stack, _menhir_s, _v) in
          _menhir_run_05 _menhir_stack _menhir_lexbuf _menhir_lexer
      | AND | BARE_WORD _ | EOF | INT _ | LPAREN | MINUS | NOT | OR | PLUS | QUOTED_STRING _ | RPAREN | TILDE ->
          let MenhirCell1_BARE_WORD (_menhir_stack, _menhir_s, f) = _menhir_stack in
          let vs = _v in
          let _v = _menhir_action_14 f vs in
          _menhir_goto_simple _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  let _menhir_run_00 : type  ttv_stack. ttv_stack -> _ -> _ -> _menhir_box_parse_query =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer ->
      let _menhir_s = MenhirState00 in
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | TILDE ->
          _menhir_run_01 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | QUOTED_STRING _v ->
          _menhir_run_08 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | PLUS ->
          _menhir_run_09 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | NOT ->
          _menhir_run_10 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | MINUS ->
          _menhir_run_11 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | LPAREN ->
          _menhir_run_12 _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s
      | INT _v ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | BARE_WORD _v ->
          _menhir_run_14 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s
      | _ ->
          _eRR ()
  
end

let parse_query =
  fun _menhir_lexer _menhir_lexbuf ->
    let _menhir_stack = () in
    let MenhirBox_parse_query v = _menhir_run_00 _menhir_stack _menhir_lexbuf _menhir_lexer in
    v
