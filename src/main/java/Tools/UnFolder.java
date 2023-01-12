package Tools;

import java.util.List;

public class UnFolder {

}

abstract class Expression{}
class Plus extends Expression {
    Expression l;
    Expression r;
}
class Mul extends Expression {
    Expression l;
    Expression r;
}
class Min extends Expression {
    Expression l;
    Expression r;
}
class Div extends Expression {
    Expression l;
    Expression r;
}
class Variable extends Expression {
    char symbol;
}
class Root extends Expression {
    Expression r;
}
class Pow extends Expression {
    Expression p;
    Expression e;
}

class Function{
    List<Expression> expression;
}