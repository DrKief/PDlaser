let () =
  let buf = Buffer.create 512 in
  try
    while true do
      let line = input_line stdin in
      Buffer.add_string buf line;
      (* Try to parse accumulated input *)
      let lexbuf = Lexing.from_string (Buffer.contents buf) in
      try
        let result = Parser.parse_query Lexer.token lexbuf in
        Printf.printf "%s\n" (Ast.to_string result);
        flush stdout;
        Buffer.clear buf
      with
      | Failure msg ->
        Printf.eprintf "Error: %s\n%!" msg;
        Buffer.clear buf
      | Parser.Error ->
        Printf.eprintf "Error: parse error\n%!";
        Buffer.clear buf
    done
  with
  | End_of_file -> ()
  | exn ->
    Printf.eprintf "Unexpected error: %s\n%!" (Printexc.to_string exn);
    exit 1
