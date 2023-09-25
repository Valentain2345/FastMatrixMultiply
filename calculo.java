
public class calculo
{

  private int contador;		//guarda en una variable la cantidad de operaciones realizadas hasta el momento

  public calculo ()
  {
    contador = 0;
  }

  public int cantOperaciones ()
  {
    return contador;
  }

  // pasa la matriz a una lista por las filas
  public double[] pasarALista1 (float[][]m, int rn)
  {
    double[] l1 = new double[4 * rn];
    double a = 0;		//producto escalar con el 1,1,1,1,1...
    double b = 0;		// suma de cuadrados
    double e = 0;		// variable de producto escalar con el -1,1,-1,1,-1... pues es perpendicular al 1,1,1,1,....
    double aux = 0;		// variable auxiliar, guarda el valor anterior 

    for (int i = 0; i < m.length; i++)
      {
	for (int j = 0; j < m[0].length; j++)
	  {
	    double f = m[i][j];
	    a += f;		// suma todos los valores,es decir, hace el producto escalar del la fila o columna con el 1,1,1,1,...
	    b += f * f;		// suma los valores de los cuadrados    

	    if (j % 2 == 0)
	      {
		e += f;		// cicla entre 1 y -1 segun i
	      }
	    else
	      {
		e += -f;
	      }
	    aux = f;		// guarda el valor de f pero cuando es Float.MAX_VALUE tiene guardado el anterior
	  }
	if (rn % 2 == 1)
	  {			// por si el vector tiene longitud impar reemplaza la ultima componente por 0
	    e -= aux;
	  }
	l1[4*i] = e;		// luego los del producto escalar con el -1,1,-1,1,...
	l1[4*i+1] = a;		// luego los del 1,1,1,1,1,....
	l1[4*i + 2] = b;		// luego guarda la suma de cuadrados
	l1[4*i + 3] = Float.MAX_VALUE;	// luego un Float.MAX_VALUE para separar
	a = 0;
	b = 0;			// resetea todas las variables de nuevo para usarlas en el proximo vector
	e = 0;
      }

    return l1;
  }

  // pasa la matriz a una lista por las columnas
  public double[] pasarALista2 (float[][]m, int rn)
  {
   double[] l1 = new double[4 * rn];
    double a = 0;		//producto escalar con el 1,1,1,1,1...
    double b = 0;		// suma de cuadrados
    double e = 0;		// variable de producto escalar con el -1,1,-1,1,-1... pues es perpendicular al 1,1,1,1,....
    double aux = 0;		// variable auxiliar, guarda el valor anterior 

    for (int i = 0; i < m[0].length; i++)
      {
	for (int j = 0; j < m.length; j++)
	  {
	    double f = m[j][i];
	    a += f;		// suma todos los valores,es decir, hace el producto escalar del la fila o columna con el 1,1,1,1,...
	    b += f * f;		// suma los valores de los cuadrados    

	    if (j % 2 == 0)
	      {
		e += f;		// cicla entre 1 y -1 segun i
	      }
	    else
	      {
		e += -f;
	      }
	    aux = f;		// guarda el valor de f pero cuando es Float.MAX_VALUE tiene guardado el anterior
	  }
	if (rn % 2 == 1)
	  {			// por si el vector tiene longitud impar reemplaza la ultima componente por 0
	    e -= aux;
	  }
	l1[4*i] = e;		// luego los del producto escalar con el -1,1,-1,1,...
	l1[4*i+1] = a;		// luego los del 1,1,1,1,1,....
	l1[4*i + 2] = b;		// luego guarda la suma de cuadrados
	l1[4*i + 3] = Float.MAX_VALUE;	// luego un Float.MAX_VALUE para separar
	a = 0;
	b = 0;			// resetea todas las variables de nuevo para usarlas en el proximo vector
	e = 0;
      }

    return l1;
  }


  //convierte una lista de n*(n+1) elementos en una de 4n elementos
  public double[] prod (double[]l, int rn)
  {
    double piS2 = Math.PI / 2;	// pi/2 es decir 90 grados
    double r1 = 1 / Math.sqrt (rn);	// inverso del modulo del 1,1,1,1,...
    double r2 = rn % 2 == 0 ? r1 : 1 / Math.sqrt (rn - 1);	//inverso del modulo de los vectores 1,-1,1,-1,... segun la longitud del vector
    double h;			//guarda el arcoseno del vector con el 1,1,1,1,...
    double c = 0;		//gaurda el angulo entre el vector y su perpendicular
    //calcula el modulo del vector y luego su inverso
    // calcula el angulo entre el vector 1,1,1,1 a partir del poducto escalar y el modulo
    // hay que programar los casos ahora
    // si k<0 entonces calcula el angulo con el -1,1,-1,1,.... sino con el 1,-1,1,-1,...
    
    for (int i = 0; i < l.length; i++)
      {
	double pf = l[i];
	if (pf == Float.MAX_VALUE)
	  {
	    double t2 = (Math.sqrt (l[i - 1]));	// Toma la suma de cuadrados y calcula su raiz cudrada
	    // Ahora la reemplaza en la lista pues en la lista de pares tiene que aparecer ese valor
	    l[i - 1] = t2;
	    double t1 = l[i - 2];	// producto escalar entre 1,1,1,1,...
	    t2 = t2 != 0 ? 1 / t2 : 0;	// inverso del modulo del vector y si el modulo es 0 entonces es 0 el inverso
	    double t3 = l[i - 3];	// producto escalar -1,1,-1,1,...
	    double k = t1 * r1 * t2;	// representa el coseno del angulo entre el vector y el 1,1,1,1,....
	    double z = t3 * t2 * r2;	// representa el coseno del angulo entre el vector y el -1,1,-1,1,...

	    //Correcciones en los double por si acaso alguno da mayor a 1 o menor a -1
	    if (k > 1)
	      k = 1;
	    if (k < -1)
	      k = -1;
	    if (z > 1)
	      k = 1;
	    if (z < -1)
	      k = -1;

	    h = Math.acos (k);	// Calcula el angulo entre el vector y el 1,1,1,1,...
	    if (k < 0)
	      {			// Si el angulo es negativo calcula si el angulo entre los vectores esta en el 2do o 3er cuadrante 
		c = Math.acos (-z);	// Calcula el angulo entre el vector y el 1,-1,1,-1,...
		if (c > piS2)	// si el angulo es mayor a 90 entonces esta en el 3er cudrante
		  c = h + piS2;	// le suma 90 grados al angulo entre el vector y el 1,1,1,1,...                                               
		else
		  c = h;	//Sino deja el angulo entre el vector y el 1,1,1,1,... 

	      }
	    else
	      {
		c = Math.acos (z);	//Ahora calcula si el angulo es positivo calcula si esta en el 1er o 4to cuadrante 
		if (c < piS2)	//Si esta el en el 4to cuadrante 
		  c = h + 3 * piS2;	// le suma 270 grados al angulo entre el vector y el 1,1,1,1,....
		else
		  c = h;	// sino deja el angulo entre el 1,1,1,1... y nada mas
	      }
	    contador += 7;	//suma una 7 al contador que son la cantidad de operaciones realizadas en el pero caso
	    l[i] = c;
	  }
      }

    return l;
  }

  // organiza los angulos y modulos en pares, le pone esa longitud porque solo va usar 2 lugares por cada vector de 3
  public double[] pasarAPares (double[]l)
  {
    double[] lp = new double[l.length/4*2];
    int i = 0;
    int j = 0;
    while (i < lp.length && j < l.length)
      {
	lp[i] = l[j+2];
	lp[i + 1] = l[j+3];
	i+=2;
	j+=4;

      }
    return lp;
  }


  public float[][] multiC (float[][]m1, float[][]m2)
  {
    int Rn = m1.length;		// cantidad de columnas de m1
    int Rm = m2[0].length;	// cantidad de filas de m2
    double[] l1 = pasarALista1 (m1, Rn);
   
    double[] l2 = pasarALista2 (m2, Rm);
   
    double[] l3 = prod (l1, Rn);
   
    double[] l4 = prod (l2, Rm);
   
    double[] l5 = pasarAPares (l3);
    
   
    double[] l6 = pasarAPares (l4);
  

    float[][] l9 = new float[l5.length/2][l6.length/2];	//almacena los valores de la matriz resultante de multiplicar m1 por m2

    //Pasa la multiplicacion de los modulos a una nueva lista
    for (int i = 0; i < l5.length-1; i+=2)
      {
	for (int j = 0; j < l6.length-1; j+=2)
	  {
	    //multiplica los modulos de los vectores fila y columna
	    double n = l5[i] * l6[j];	// anyade a la lista de productos de modulo el resultado
	    //Hace la diferencia de angulos en una nueva variable
	    //como el coseno es simetrico entonces no importa el orden de la resta
	    double k = l5[i+1] - l6[j+1];
	    k = Math.cos (k);	//Calcula el coseno de la diferencia de angulos
	    //Correge posibles errores en los double por si el coseno dio un numero que no era exactamente 1 o -1
	    if (k < -1)
	      k = -1;
	    if (k > 1)
	      k = 1;
	    //Correge posibles errores en los double por si el coseno dio un numero muy cercano a 0
	    if (Math.abs (k) < 0.0000001)
	      k = 0;
	    float n1 = (float) n;	//Casteo producto modulo vectores a float
	    float k2 = (float) k;	//Casteo angulo entre vectores a float
	    l9[i/2][j/2] = n1 * k2;
	    contador += 3;
	  }
      }

    //Se perdio precision en el proceso creo yo por utilizar operaciones como coseno, raiz cuadrada y arco coseno por eso en 
    //los testers se utiliza round o floor
    // tambien se puede perder precision por los casteos, aunque son solo 2
    return l9;
  }


  public static void main(String a[]){
		float[][] prueba = new float[1000][1000]; // limite 11026x11026
		for (int i = 0; i < prueba.length; i++) {
			for (int j = 0; j < prueba[0].length; j++)
				prueba[i][j] = i;
		}

		float[][] prueba1 = new float[prueba[0].length][prueba.length];
		for (int i = 0; i < prueba1.length; i++) {
			for (int j = 0; j < prueba1[0].length; j++)
				prueba1[i][j] = j;
		}

		long cuentas = 0;
		calculo c = new calculo();

		float[][] res = new float[prueba.length][prueba[0].length];
		long start1 = System.currentTimeMillis();
		// Metodo clasico multiplicar matrices de nxn
		for (int i = 0; i < prueba.length; i++)
			for (int j = 0; j < prueba1.length; j++)
				for (int k = 0; k < prueba[0].length; k++) {
					res[i][j] += prueba[i][k] * prueba1[k][j];
					cuentas++;
				}
		long end1 = System.currentTimeMillis();

		// Imprimir Matriz

//		 for (int i=0;i<res.length;i++) {
//		        for(int j=0; j<res[0].length;j++)
//		        	System.out.print(res[i][j]+" ");
//		        System.out.println();
//		 }

		long start2 = System.currentTimeMillis();
		float[][] res1 = c.multiC(prueba, prueba1); // empieza a ser mas rapido en el 700 x 700
		long end2 = System.currentTimeMillis();
		System.out.println();

		// Imprimir Matriz

//		 for (int i=0;i<res1.length;i++) {
//		        for(int j=0; j<res1[0].length;j++)
//		        	System.out.print(Math.floor(res1[i][j])+" ");
//		        System.out.println();
//		 } 

		System.out.println();
		System.out.println("clasico");
		System.out.println(res[res.length / 2][res.length / 2]);
		System.out.println("Mi metodo");
		System.out.println(res1[res1.length / 2][res1.length / 2]);
		System.out.println();
		System.out.println("Cantidad de operaciones realizadas.... ");
		System.out.println("Mi metodo " + c.cantOperaciones() + " aprox " + Math.pow(prueba.length, 2) * 12);
		System.out.println("tiempo transcurrido en milisegundos " + (end2 - start2));
		System.out.println(
				"Cantidad de filas de la matriz resultante " + res1.length + " Cantidad de columnas " + res1[0].length);
		System.out.println();
		System.out.println("Clasico " + cuentas + " aprox " + Math.pow(prueba.length, 3));
		System.out.println("tiempo transcurrido en milisegundos " + (end1 - start1));
//		 Math.floor

	}
}

