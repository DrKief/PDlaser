type expr =
  | Tag of string
  | FieldOp of string * string list
  | Semantic of string list
  | And of expr * expr
  | Or of expr * expr
  | Not of expr
  | Group of expr

let rec to_string = function
  | Tag s -> Printf.sprintf "Tag(%S)" s
  | FieldOp (f, vs) ->
      Printf.sprintf "FieldOp(%S, [%s])" f
        (String.concat "; " (List.map (Printf.sprintf "%S") vs))
  | Semantic vs ->
      Printf.sprintf "Semantic([%s])"
        (String.concat "; " (List.map (Printf.sprintf "%S") vs))
  | And (l, r) -> Printf.sprintf "And(%s, %s)" (to_string l) (to_string r)
  | Or (l, r) -> Printf.sprintf "Or(%s, %s)" (to_string l) (to_string r)
  | Not e -> Printf.sprintf "Not(%s)" (to_string e)
  | Group e -> Printf.sprintf "Group(%s)" (to_string e)
