class Nodo {
	public Integer v;
	public Nodo p, esq, dir; 
	public boolean ver; 

  public Nodo(int n){
		this.v = n;
		this.ver = false;
		this.esq = this.dir = this.p = null;
	} 

	public Nodo(int n, boolean ver) {
		this.v = n;
		this.ver = ver;
		this.p = this.esq = this.dir = Arvore.nil;
	}

	public Nodo encontra(int n) {
		if (n < this.v && this.esq != Arvore.nil) return this.esq.encontra(n);
		else if (n > this.v && this.dir != Arvore.nil) return this.dir.encontra(n);
		else return this;
	}

 	 public void mostra(){
		if (this.esq != Arvore.nil) this.esq.mostra();
		System.out.println(this + ": " + this.esq + " <- (" + this.v + " + " + (this.ver ? "V" : "P") + ") -> " + this.dir);
		if (this.dir != Arvore.nil) this.dir.mostra();
	} 

	public Nodo minimo() {
		if (this.esq != Arvore.nil) return esq.minimo();
		else return this;
	}

 public Nodo maximo() {
		if (this.dir != Arvore.nil) return dir.maximo();
		else return this;
	}

	public void in() {
		if (this.esq != Arvore.nil) this.esq.in();
		System.out.println(this.v);
		if (this.dir != Arvore.nil) this.dir.in();
	}

	/* 
	public int inBusca(int x) {
		int resultado = 0;
		if (this.esq != Arvore.nil) this.esq.inBusca(x);
		if(x == this.v){
			resultado++;
			resultado += inBusca(x);
		}
		if (this.dir != Arvore.nil) this.dir.inBusca(x);
		return resultado;
	}
	*/

	public int inBusca(int x) {
		int resultado = 0;
		
		if (this.esq != Arvore.nil) {
			resultado += this.esq.inBusca(x);
		}
	
		if (x == this.v) {
			resultado++;
		}
	
		if (this.dir != Arvore.nil) {
			resultado += this.dir.inBusca(x);
		}
	
		return resultado;
	}

	 public Nodo predecessor() {
		if (this.esq != Arvore.nil) return this.esq.maximo();
		else return this;
	}

	public Nodo sucessor(){
		if (this.dir != Arvore.nil) return this.dir.minimo();
		else return this;
	}

  

	public void grafico() {
		if (this.ver) {
				System.out.println("\t" + this.v + " [style = filled, fillcolor = red];");
		} else {
				System.out.println("\t" + this.v + " [style = filled, fillcolor = black, fontcolor = white];");
		}

		if (this.esq != Arvore.nil) {
				System.out.println("\t" + this.v + " -> " + this.esq.v + " [label = \" esq\"];");
				this.esq.grafico();
		}
		else {
				System.out.println("\t" + this.v + " -> nil [label = \" esq\"];");
		}

		if (this.dir != Arvore.nil) {
				System.out.println("\t" + this.v + " -> " + this.dir.v + " [label = \" dir\"];");
				this.dir.grafico();
		}
		else {
				System.out.println("\t" + this.v + " -> nil [label = \" dir\"];");
		}
	}
}