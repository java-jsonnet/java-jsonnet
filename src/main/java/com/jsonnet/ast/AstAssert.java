package com.jsonnet.ast;

import com.jsonnet.lexer.LocationRange;


// AstAssert represents an assert expression (not an object-level assert).
// After parsing, message can be nil indicating that no message was specified. This AST is eliminated by de-sugaring.
public class AstAssert extends AstBaseNode {
  AstNode cond;
  AstNode message;
  AstNode rest;

  public AstAssert(LocationRange loc, AstNode cond, AstNode message, AstNode rest) {
    super(loc);
    this.cond = cond;
    this.message = message;
    this.rest = rest;
  }
}
