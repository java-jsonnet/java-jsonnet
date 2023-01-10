package com.jsonnet;

import com.jsonnet.lexer.Lexer;
import com.jsonnet.lexer.Token;
import java.util.LinkedList;
import java.util.List;
import org.testng.annotations.Test;


public class TestParser {
  List<String> happyCase = new LinkedList<>() {{
    add("true");
    add("1");
    add("1.2e3");
    add("!true");
    add("null");

    add("$.foo.bar");
    add("self.foo.bar");
    add("super.foo.bar");
    add("super[1]");
    add("error \"Error!\"");

    add("\"world\"");
    add("'world'");
    add("|||\n" + "   world\n" + "|||");

    add("foo(bar)");
    add("foo.bar");
    add("foo[bar]");

    add("true || false");
    add("0 && 1 || 0");
    add("0 && (1 || 0)");

    add("local foo = \"bar\"; foo");
    add("local foo(bar) = bar; foo(1)");
    add("{ local foo = \"bar\", baz: 1}");
    add("{ local foo(bar) = bar, baz: foo(1)}");

    add("{ foo(bar, baz): bar+baz }");

    add("{ [\"foo\" + \"bar\"]: 3 }");
    add("{ [\"field\" + x]: x for x in [1, 2, 3] }");
    add("{ [\"field\" + x]: x for x in [1, 2, 3] if x <= 2 }");
    add("{ [\"field\" + x + y]: x + y for x in [1, 2, 3] if x <= 2 for y in [4, 5, 6]}");

    add("[]");
    add("[a, b, c]");
    add("[x for x in [1,2,3] ]");
    add("[x for x in [1,2,3] if x <= 2]");
    add("[x+y for x in [1,2,3] if x <= 2 for y in [4, 5, 6]]");

    add("{}");
    add("{ hello: \"world\" }");
    add("{ hello +: \"world\" }");
    add("{\n" + "  hello: \"world\",\n" + "\t\"name\":: joe,\n" + "\t'mood'::: \"happy\",\n" + "\t|||\n"
        + "\t  key type\n" + "|||: \"block\",\n" + "}");

    add("assert true: 'woah!'; true");
    add("{ assert true: 'woah!', foo: bar }");

    add("if n > 1 then 'foos' else 'foo'");

    add("local foo = function(x) x + 1; true");

    add("import 'foo.jsonnet'");
    add("importstr 'foo.text'");

    add("{a: b} + {c: d}");
    add("{a: b}{c: d}");
  }};

  @Test
  public void testParser() {
    for (int i = 0; i < happyCase.size(); i++) {
      Lexer lexer = new Lexer("test_" + i, happyCase.get(i));
      List<Token> tokens = lexer.lex();
      System.out.println(tokens);
    }
  }
}
