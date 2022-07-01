package items;

public sealed interface Item permits Import,Block, Class, Method, Variable,Constructor {
}
