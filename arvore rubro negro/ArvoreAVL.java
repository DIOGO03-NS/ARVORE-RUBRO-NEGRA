public class ArvoreAVL {
    private Elemento ele;
    private ArvoreAVL dir;
    private ArvoreAVL esq;
    private int bal;
        
    public ArvoreAVL(){
        this.ele = null;
        this.esq = null;
        this.dir = null;
        this.bal = 0;
    }
        
    public ArvoreAVL(Elemento elem){
        this.ele = elem;
        this.dir = null;
        this.esq = null;
        this.bal = 0;
    }

    public ArvoreAVL remover(Elemento elem){
        if (this.ele.getValor() == elem.getValor()){
            if (this.dir == null && this.esq == null){
                return null;
            }else{
            if (this.esq != null && this.dir == null){
                return this.esq;
            }else if (this.dir != null && this.esq == null){
                return this.dir;
        }else{
            ArvoreAVL aux = this.esq;
            while (aux.dir != null){ 
                aux = aux.dir;
            }
            this.ele = aux.getElemento();
            aux.setElemento(elem);
            this.esq = esq.remover(elem);
                }
            }
        }
        else if (elem.getValor() < this.ele.getValor()){ 
        this.dir = this.dir.remover(elem);
        }
        return this;
        }
        
        // metodos de controle;
        public boolean isEmpty(){
            return (this.ele == null);
        }
        
        public void imprimirPreOrdem(){
            if (!isEmpty()){
                System.out.print(this.ele.getValor() + "");
                if (this.esq != null){
                this.esq.imprimirPreOrdem();
                }
                if (this.dir != null){
                    this.dir.imprimirPreOrdem();
                }
            }
        }
        
        public void imprimirInOrdem(){
            if (!isEmpty()){
                if (this.esq != null){
                    this.esq.imprimirInOrdem();
                }
                System.out.print(this.ele.getValor() + " ");
                if (this.dir != null){
                    this.dir.imprimirInOrdem();
                }
            }
        }
        
        public void imprimirPosOrdem(){
            if (!isEmpty()){
                if (this.dir != null){
                    this.dir.imprimirPosOrdem();
                }
                if (this.esq != null){
                    this.esq.imprimirPosOrdem();
                }
                System.out.print(this.ele.getValor() + " ");
            }
        }
        
        public ArvoreAVL inserir(Elemento novo){
            if (isEmpty()){
                this.ele = novo;
            }else{
                ArvoreAVL novaArvore = new ArvoreAVL(novo);
                if (novo.getValor() < this.ele.getValor()){
                    if (this.esq == null){
                        this.esq = novaArvore;
                    }else{
                        this.esq = this.esq.inserir(novo);
                    }
                }
                else if(novo.getValor() > this.ele.getValor()){
                    if(this.dir == null){
                        this.dir = novaArvore;
                    }else{
                        this.dir = this.dir.inserir(novo);
                    }
                }
            }
            return this;
        }
    

    public int calcularAltura(){
        if(this.esq == null && this.dir == null){
            return 1;
        }else if(this.esq != null && this.dir == null){
            return 1 + this.esq.calcularAltura();
        }else if(this.esq == null && this.dir != null){
            return 1 + this.dir.calcularAltura();
        }else{
            return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura());
        }
    }    
    
    public void calcularBalanceamento(){
        if(this.dir == null && this.esq == null){
            this.bal = 0;
        }else if(this.esq == null && this.dir != null){
            this.bal = this.dir.calcularAltura() - 0;
        }else if(this.esq != null && this.dir == null){
            this.bal = 0 - this.esq.calcularAltura();
        }else{
            this.bal = this.dir.calcularAltura() - this.esq.calcularAltura();
        }
        if(this.dir != null) this.dir.calcularBalanceamento();
        if(this.esq != null) this.esq.calcularBalanceamento();
    }
        
    public boolean busca(int valor){
                if (isEmpty()){
                return false;
                }
                if (this.ele.getValor() == valor){
                return true;
                }
                else{
                if (valor < this.ele.getValor() ){ if (this.esq == null){ return false; } else{ return this.esq.busca(valor);
                } } else if (valor > this.ele.getValor()){
                if (this.dir == null){
                return false;
                }
                else{
                return this.dir.busca(valor);
                }
                }
                return false;
                }
    }


    //depurar
    public String printArvore(int level){
        String str = this.toString() + "\n";
        for(int i = 0; i < level; i++){
                str += "\t";
            }
        if(this.esq != null){
            str += "+-ESQ: " + this.esq.printArvore(level + 1);
        }else{
            str += "+-ESQ: NULL";
        }
        str += "\n";
        for(int i = 0; i < level; i++){
                str += "\t";
        }
        if(this.dir != null){
            str += "+-DIR: " + this.dir.printArvore(level + 1);
        }else{
            str += "+-DIR: NULL";
        }
        str += "\n";
        return str;
        
    }

    //get set
    public String toString(){
        return "[" + this.ele.getValor() +"] (" + this.bal + ")";
    }


    //rotacao
    public ArvoreAVL verificarBalanceamento(){
        if(this.bal >= 2 || this.bal <= -2){
            if(this.bal >= 2){
                if(this.bal * this.dir.getBalanceamento() > 0){
                    return rotacaoSimplesDireita();
                }else{
                    return rotacaoDuplaDireita();
                }
            }else{
                if(this.bal * this.esq.getBalanceamento() > 0){
                    return rotacaoSimplesEsquerda();
                }else{
                    return rotacaoDuplaEsquerda();
                }
            }
        }
        this.calcularBalanceamento();
        if(this.esq != null) this.esq = this.esq.verificarBalanceamento();
        if(this.dir != null) this.dir = this.dir.verificarBalanceamento();
        return this;
    }

    //rotacoes
    public ArvoreAVL rotacaoSimplesDireita(){
        ArvoreAVL filhoDir;
        ArvoreAVL filhoDoFilho = null;

        filhoDir = this.getDireita();
        if(this.dir != null){
            if(this.dir.getEsquerda() != null){
                filhoDoFilho = filhoDir.getEsquerda();
            }
        }
        filhoDir.setEsq(this);
        this.setDir(filhoDoFilho);
        return filhoDir;
    }
    public ArvoreAVL rotacaoSimplesEsquerda(){
        ArvoreAVL filhoEsq;
        ArvoreAVL filhoDoFilho = null;

        filhoEsq = this.getEsquerda();
        if(this.esq != null){
            if(this.esq.getDireita() != null){
                filhoDoFilho = filhoEsq.getDireita();
            }
        }
        filhoEsq.setDir(this);
        this.setEsq(filhoDoFilho);
        return filhoEsq;
    }

    public ArvoreAVL rotacaoDuplaDireita(){
        ArvoreAVL arvore = this;
        ArvoreAVL filhoDir = this.getDireita();
        ArvoreAVL filhoDoFilho = filhoDir.getEsquerda();
        ArvoreAVL noInserido = filhoDoFilho.getDireita();

        filhoDir.setEsq(noInserido);
        filhoDoFilho.setDir(filhoDir);
        this.setDir(filhoDoFilho);

        ArvoreAVL novoFilhoDireita = this.getDireita();
        arvore.setDir(null);
        novoFilhoDireita.setEsq(arvore);
        return novoFilhoDireita;
    }
    public ArvoreAVL rotacaoDuplaEsquerda(){
        ArvoreAVL arvore = this;
        ArvoreAVL filhoEsq = this.getEsquerda();
        ArvoreAVL filhoDoFilho = filhoEsq.getDireita();
        ArvoreAVL noInserido = filhoDoFilho.getEsquerda();

        filhoEsq.setDir(noInserido);
        filhoDoFilho.setEsq(filhoEsq);
        this.setEsq(filhoDoFilho);

        ArvoreAVL novoFilhoEsquerda = this.getEsquerda();
        arvore.setEsq(null);
        novoFilhoEsquerda.setDir(arvore);
        return novoFilhoEsquerda;
    }
    

    ////
        
    public void setElemento(Elemento ele){
        this.ele = ele;
    }
    public void setDir(ArvoreAVL dir){
        this.dir = dir;
    }
    public void setEsq(ArvoreAVL esq){
        this.esq = esq;
    }
    public void setBalanceamento(int bal){
        this.bal = bal;
    }
        
    public ArvoreAVL getDireita(){
        return this.dir;
    }
    public ArvoreAVL getEsquerda(){
        return this.esq;
    }
    public Elemento getElemento(){
        return this.ele;
    }
    public int getBalanceamento(){
        return this.bal;
    }
}
