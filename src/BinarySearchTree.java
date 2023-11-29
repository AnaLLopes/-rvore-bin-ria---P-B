/**
 * CLASSE BinarySearchTree
 * Trabalhando com árvore binária de pesquisa
 */

class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Integer value) {
        root = insert(root, value);
    }

    private Node insert(Node _root, Integer value) {
        if (_root == null) {
            _root = new Node();
            _root.element = value;
            return _root;
        }

        if (value < _root.element) {
            _root.left = insert(_root.left, value);
        } else if (value > _root.element) {
            _root.right = insert(_root.right, value);
        } else {
            // duplicado
            return _root;
        }

        // fator de equilíbrio do nó
        int balance = getBalanceFactor(_root);

        // casos de rotação
        // L-L
        if (balance > 1 && value < _root.left.element) {
            return rotateRight(_root);
        }
        // R-R
        if (balance < -1 && value > _root.right.element) {
            return rotateLeft(_root);
        }
        // L-R
        if (balance > 1 && value > _root.left.element) {
            _root.left = rotateLeft(_root.left);
            return rotateRight(_root);
        }
        // R-L
        if (balance < -1 && value < _root.right.element) {
            _root.right = rotateRight(_root.right);
            return rotateLeft(_root);
        }

        return _root;
    }

    public Node contains(Integer v) {
        // se arvore vazia
        if (root == null)
            return null;

        // começa a procurar desde raiz
        Node current = root;
        // enquanto nao encontrou
        while (current.element != v) {
            if (v < current.element)
                current = current.left; // caminha para esquerda
            else
                current = current.right; // caminha para direita

            // encontrou uma folha -> sai
            if (current == null)
                return null;
        }

        // terminou o laço while e chegou aqui é pq encontrou item
        return current;
    }

    // Remoção com balanceamento AVL
    public boolean remove(Integer value) {
        // se arvore vazia
        if (root == null)
            return false;

        Node current = root;
        Node father = root;
        boolean child_left = true;
        // buscando o valor
        while (current.element != value) {
            // enquanto nao encontrou
            father = current;
            // caminha para esquerda
            if (value < current.element) {
                current = current.left;
                // é filho a esquerda? sim
                child_left = true;
            }
            // caminha para direita
            else {
                current = current.right;
                // é filho a esquerda? NAO
                child_left = false;
            }
            // encontrou uma folha -> sai
            if (current == null)
                return false;
        }

        root = remove(current);
        return true;
    }

    private Node remove(Node node) {

        // nó com 0 ou 1 filho
        if (node.left == null || node.right == null) {
            Node child = (node.left != null) ? node.left : node.right;

            // Se o nó é a raiz
            if (child == null) {
                node = null;
            } else {
                node = child;
            }
        } else {
            // nó com 2 filhos
            Node successor = findMin(node.right);

            node.element = successor.element;

            node.right = remove(successor);
        }
        // reequilibrar a árvore
        return balance(node);
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node balance(Node node) {

        // fator de equilíbrio
        int balance = getBalanceFactor(node);

        // casos de rotação
        if (balance > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (balance < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    // O sucessor é o nodo mais a esquerda da subarvore a direita do nodo que foi
    // passado como parâmetro do método
    public Node node_successor(Node node) {
        Node father_successor = node;
        Node successor = node;
        Node current = node.left;

        // enquanto nao chegar no nodo mais a esquerda
        while (current != null) {
            father_successor = successor;
            successor = current;
            // caminha para a esquerda
            current = current.left;
        }
        // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
        if (successor != node.right) {
            // pai herda os filhos do sucessor que sempre serão a direita
            father_successor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    void clearTree() {
        root = null;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.print(current.element + " ");
            inOrder(current.right);
        }
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.element + " ");
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void posOrder(Node current) {
        if (current != null) {
            posOrder(current.left);
            posOrder(current.right);
            System.out.print(current.element + " ");
        }
    }

    public int height(Node current) {
        if (current == null || (current.left == null && current.right == null)) {
            return 0;
        } else {
            if (height(current.left) > height(current.right))
                return (1 + height(current.left));
            else
                return (1 + height(current.right));
        }
    }

    public int countNodes(Node current) {
        if (current == null)
            return 0;
        else
            return (1 + countNodes(current.left) + countNodes(current.right));
    }

    public Node getRoot() {
        return root;
    }

    public void orders() {
        System.out.print("\n Caminhamento Central ou Em or (Interfixado): ");
        inOrder(root);
        System.out.print("\n Exibindo em Pós-ordem (Pós-fixado): ");
        posOrder(root);
        System.out.print("\n Exibindo em Pré-ordem (Pré-fixado): ");
        preOrder(root);
    }

    public void treeInfo() {
        System.out.println("Altura da arvore: " + height(root));
        System.out.println("Quantidade de Nós: " + countNodes(root));
        if (root != null) {
            System.out.println("Valor minimo: " + minNode());
            System.out.println("Valor maximo: " + maxNode());
        }
        System.out.println("Quantidade de folhas: " + countNodes(root));
    }

    public void printTree() {
        if (root != null) {
            TreeFormatter formatter = new TreeFormatter();
            System.out.println(formatter.topDown(root));
        } else {
            System.out.println("Árvore vazia!");
        }
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza alturas
        // updateHeight(x);
        // updateHeight(y);

        return y;
    }

    /**
     * Método minNode()
     * método que busca o menor valor existente na árvore
     * 
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor do menor nodo da árvore
     */
    public int minNode() {
        if (isEmpty()) {
            return 0;
        }
        Node current = this.root;
        while (current.left != null) {
            current = current.left;
        }
        return current.element;
    }

    /**
     * Método maxNode()
     * método que busca o maior valor existente na árvore
     * 
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor do maior nodo da árvore
     */

    public int maxNode() {
        if (isEmpty()) {
            return 0;
        }
        Node current = this.root;
        while (current.right != null) {
            current = current.right;
        }
        return current.element;
    }

    private boolean isEmpty() {
        return root == null;
    }

    /**
     * Método countLeaves()
     * método que conta os nodos folha de uma árvore binária
     * 
     * @param defina a necessidade de parâmetros de acordo com a sua implementação
     * @return valor inteiro correspondente a quantidade de nodos folha
     */

    public int countLeaves(Node current) {
        if (current == null)
            return 0;
        if (current.left == null && current.right == null)
            return 1;
        else
            return countLeaves(current.left) + countLeaves(current.right);
    }

    /**
     * Método sumBetween()
     * método soma os valores de uma sequência de nodos (não incluindo os valores
     * dos nodos de início e fim)
     * 
     * @param start  valor que corresponde ao nodo de início
     * @param end    valor que corresponde ao nodo de fim
     * @param defina outros caso haja necessidade na sua implementação
     * @return valor inteiro correspondente a quantidade de nodos folha
     */
    public int sumBetween(int start, int end) {
        return sumBetweenRecursive(root, start, end);
    }

    private int sumBetweenRecursive(Node current, int start, int end) {
        if (current == null) {
            return 0;
        }

        int sum = 0;

        if (current.element > start && current.element < end) {
            sum += current.element;
        }

        // adiciona os valores das subárvores esquerda e direita
        sum += sumBetweenRecursive(current.left, start, end);
        sum += sumBetweenRecursive(current.right, start, end);

        return sum;
    }

}
