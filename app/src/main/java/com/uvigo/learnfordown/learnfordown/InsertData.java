package com.uvigo.learnfordown.learnfordown;

import android.content.Context;

/**
 * Created by Juani on 13/02/2017.
 */

public class InsertData {
    DataBaseManager db;
    private Context context;
    private String[] subnivelesDirectas = new String[]{"p","m","t","bv","n","kcq","l","s","d","zc","f","j","lly","g","ch","r"};
    private String[] subnivelesInversas = new String[]{"m","n","l","s","r"};
    private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","cr","fr","gl","gr","tr","tl","fr","fr","bvr"};
    public InsertData(Context context) {
        this.context =context;
        db = new DataBaseManager(context);


    }
    public void insertar() {
        insertarNiveles();
        insertarFotos();
        db.close();
    }

    private void insertarFotos() {

        db.insertar_foto("a","a","directa","abeja","poner frase aqui ",R.drawable.abeja,"animales",3,"vocales");
        db.insertar_foto("a","a","directa","agua","Bebo bastante",R.drawable.agua,"otro",2,"vocales");
        db.insertar_foto("a","a","directa","amarillo","Me gusta mucho el color",R.drawable.amarillo,"otro",4,"vocales");

        db.insertar_foto("b","bu","directa","bufanda","La bufanda abriga",R.drawable.bufanda,"ropa",3,"bv");
        db.insertar_foto("b","ba","directa","baca","Mi padre puso las maletas en la *",R.drawable.baca,"otros",2,"bv");
        db.insertar_foto("b","be","directa","bebe","Mi hermano pequeño es un",R.drawable.bebe,"otros",2,"bv");
        db.insertar_foto("b","bi","directa","biberon","Cuando era pequeño tomaba el",R.drawable.biberon,"comida",3,"bv");
        db.insertar_foto("b","bi","directa","bicicleta","Fui al parque en",R.drawable.bicicleta,"otros",4,"bv");
        db.insertar_foto("b","bu","directa","buho","poner frase aqui",R.drawable.buho,"animales",2,"bv");
        db.insertar_foto("b","bu","directa","burro","poner frase aqui",R.drawable.burro,"animales",2,"bv");
        db.insertar_foto("b","bru","trabada","brujula","Al monte llevamos una",R.drawable.brujula,"otro",3,"bvr");
        db.insertar_foto("b","blu","trabada","blusa","Mi madre tiene una",R.drawable.blusa,"ropa",3,"bvr");
        db.insertar_foto("b","bra","trabada","brazo","Me duele el",R.drawable.brazo,"otro",2,"bvr");
        db.insertar_foto("b","bre","trabada","brecol","Me encanta el",R.drawable.brecol,"comida",2,"bvr");
        db.insertar_foto("b","bri","trabada","bricolaje","poner frase aqui",R.drawable.bricolaje,"otro",4,"bvr");
        db.insertar_foto("b","bro","trabada","brote","poner frase aqui",R.drawable.brote,"otro",2,"bvr");

        db.insertar_foto("c","co","directa","coche","El coche de mi madre es gris ",R.drawable.coche,"vehiculos",2,"kcq");
        db.insertar_foto("c","ca","directa","caballo","Ayer monté a ",R.drawable.caballo,"animales",3,"kcq");
        db.insertar_foto("c","ca","directa","cabra","Mi abuela tiene",R.drawable.cabra,"animales",2,"kcq");
        db.insertar_foto("c","ca","directa","casa","La casa es blanca",R.drawable.casa,"casa",2,"kcq");
        db.insertar_foto("c","ca","directa","camello","poner frase aqui",R.drawable.camello,"animales",3,"kcq");
        db.insertar_foto("c","co","directa","cocina","poner frase aqui",R.drawable.cocina,"casa",3,"kcq");
        db.insertar_foto("c","co","directa","cocodrilo","poner frase aqui",R.drawable.cocodrilo,"animales",4,"kcq");
        db.insertar_foto("c","co","directa","conejo","poner frase aqui",R.drawable.conejo,"animales",3,"kcq");
        db.insertar_foto("c","cu","directa","cubo","poner frase aqui",R.drawable.cubo,"otros",2,"kcq");
        db.insertar_foto("c","cu","directa","cuchillo","poner frase aqui",R.drawable.cuchillo,"casa",3,"kcq");
        db.insertar_foto("c","cu","directa","cuna","poner frase aqui",R.drawable.cuna,"casa",2,"kcq");
        db.insertar_foto("c","cla","trabada","clarinete","poner frase aqui",R.drawable.clarinete,"musica",2,"cl");
        db.insertar_foto("c","cla","trabada","clave","poner frase aqui",R.drawable.clave,"musica",2,"cl");
        db.insertar_foto("c","cro","trabada","croquetas","poner frase aqui",R.drawable.croquetas,"comida",2,"cr");

        db.insertar_foto("c","ci","directa","cine","poner frase aqui",R.drawable.cine,"otros",2,"zc");
        db.insertar_foto("c","ce","directa","cebolla","poner frase aqui",R.drawable.cebolla,"comida",3,"zc");
        db.insertar_foto("c","ce","directa","cerezas","poner frase aqui",R.drawable.cerezas,"comida",3,"zc");
        db.insertar_foto("c","ci","directa","ciruela","poner frase aqui",R.drawable.ciruela,"comida",3,"zc");


        db.insertar_foto("ch","cha","directa","chaleco","poner frase aqui",R.drawable.chaleco,"ropa",3,"ch");
        db.insertar_foto("ch","cha","directa","chaqueta","poner frase aqui",R.drawable.chaqueta,"ropa",3,"ch");
        db.insertar_foto("ch","chi","directa","chicles","poner frase aqui",R.drawable.chicles,"comida",2,"ch");
        db.insertar_foto("ch","cho","directa","chocolate","poner frase aqui",R.drawable.chocolate,"comida",4,"ch");
        db.insertar_foto("ch","cho","directa","chorizos","poner frase aqui",R.drawable.chorizos,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","chupete","poner frase aqui",R.drawable.chupete,"otros",3,"ch");
        db.insertar_foto("ch","chu","directa","churrasco","poner frase aqui",R.drawable.churrasco,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","churros","poner frase aqui",R.drawable.churro,"comida",2,"ch");

        db.insertar_foto("d","da","directa","damas","poner frase aqui",R.drawable.damas,"otros",2,"d");
        db.insertar_foto("d","de","directa","desierto","poner frase aqui",R.drawable.desierto,"otros",3,"d");
        db.insertar_foto("d","di","directa","dibujar","poner frase aqui",R.drawable.dibujar,"otros",3,"d");
        db.insertar_foto("d","di","directa","dinero","poner frase aqui",R.drawable.dinero,"otros",3,"d");
        db.insertar_foto("d","di","directa","diploma","poner frase aqui",R.drawable.diploma,"otros",3,"d");
        db.insertar_foto("d","do","directa","domino","poner frase aqui",R.drawable.domino,"otros",3,"d");
        db.insertar_foto("d","du","directa","ducha","poner frase aqui",R.drawable.ducha,"casa",2,"d");

        db.insertar_foto("e","e","directa","elefante","poner frase aqui",R.drawable.elefante,"animales",4,"vocales");

        db.insertar_foto("f","fa","directa","familia","poner frase aqui",R.drawable.familia,"otros",3,"f");
        db.insertar_foto("f","fa","directa","faro","poner frase aqui",R.drawable.faro,"otros",2,"f");
        db.insertar_foto("f","fi","directa","fideos","poner frase aqui",R.drawable.fideos,"comida",3,"f");
        db.insertar_foto("f","fi","directa","figuras","poner frase aqui",R.drawable.figuras,"otros",3,"f");
        db.insertar_foto("f","fla","trabada","flamenco","poner frase aqui",R.drawable.flamenco,"animales",3,"f");
        db.insertar_foto("f","fla","trabada","flan","poner frase aqui",R.drawable.flan,"comida",1,"fl");
        db.insertar_foto("f","flo","trabada","flores","poner frase aqui",R.drawable.flores,"otros",2,"fl");
        db.insertar_foto("f","fre","trabada","fresa","poner frase aqui",R.drawable.fresa,"comida",2,"fr");
        db.insertar_foto("f","fru","trabada","frutero","poner frase aqui",R.drawable.frutero,"comida",3,"fr");
        db.insertar_foto("f","fu","directa","fular","poner frase aqui",R.drawable.fular,"ropa",2,"f");

        db.insertar_foto("g","ga","directa","gato","poner frase aqui",R.drawable.gato,"animales",1,"g");
        db.insertar_foto("g","gui","directa","guitarra","poner frase aqui",R.drawable.guitarra,"otro",3,"g");
        db.insertar_foto("g","ga","directa","gallina","poner frase aqui",R.drawable.gallina,"animales",3,"g");
        db.insertar_foto("g","ga","directa","gallo","poner frase aqui",R.drawable.gallo,"animales",2,"g");
        db.insertar_foto("g","gi","directa","girasol","poner frase aqui",R.drawable.girasol,"otro",3,"g");
        db.insertar_foto("g","go","directa","goma","poner frase aqui",R.drawable.goma,"otro",2,"g");
        db.insertar_foto("g","go","directa","gorila","poner frase aqui",R.drawable.gorila,"animales",3,"g");
        db.insertar_foto("g","go","directa","gorro","poner frase aqui",R.drawable.gorro,"ropa",2,"g");
        db.insertar_foto("g","gu","directa","gusasnitos","poner frase aqui",R.drawable.gusanitos,"comida",4,"g");
        db.insertar_foto("g","gu","directa","gusano","poner frase aqui",R.drawable.gusano,"animales",3,"g");

        db.insertar_foto("i","i","directa","iglu","poner frase aqui",R.drawable.iglu,"casa",2,"vocales");
        db.insertar_foto("i","i","directa","imán","poner frase aqui",R.drawable.iman,"otro",2,"vocales");

        db.insertar_foto("j","ja","directa","jabali","poner frase aqui",R.drawable.jabali,"animales",3,"j");
        db.insertar_foto("j","ja","directa","jabon","poner frase aqui",R.drawable.jabon,"otro",2,"j");
        db.insertar_foto("j","ja","directa","jamon","poner frase aqui",R.drawable.jamon,"comida",2,"j");
        db.insertar_foto("j","je","directa","jeringa","poner frase aqui",R.drawable.jeringa,"otro",3,"j");
        db.insertar_foto("j","ji","directa","jirafa","poner frase aqui",R.drawable.jirafa,"animales",3,"j");
        db.insertar_foto("j","jo","directa","joyas","poner frase aqui",R.drawable.joyas,"otro",2,"j");
        db.insertar_foto("j","ju","directa","judias","poner frase aqui",R.drawable.judias,"comida",3,"j");

        db.insertar_foto("l","la","directa","lapiz","poner frase aqui",R.drawable.lapiz,"otro",2,"l");
        db.insertar_foto("l","la","directa","labios","poner frase aqui",R.drawable.labios,"otro",2,"l");
        db.insertar_foto("l","la","directa","lata","poner frase aqui",R.drawable.lata,"otro",2,"l");
        db.insertar_foto("l","le","directa","leon","poner frase aqui",R.drawable.leon,"animales",2,"l");
        db.insertar_foto("l","li","directa","libros","poner frase aqui",R.drawable.libros,"otro",2,"l");
        db.insertar_foto("l","li","directa","limon","poner frase aqui",R.drawable.limon,"comida",2,"l");
        db.insertar_foto("l","lo","directa","lobo","poner frase aqui",R.drawable.lobo,"animales",2,"l");
        db.insertar_foto("l","lo","directa","loro","poner frase aqui",R.drawable.loro,"animales",2,"l");
        db.insertar_foto("l","lu","directa","luna","poner frase aqui",R.drawable.luna,"otro",2,"l");
        db.insertar_foto("l","lu","directa","lupa","poner frase aqui",R.drawable.lupa,"otro",2,"l");
        db.insertar_foto("a","al","inversa","almeja","poner frase aqui",R.drawable.almeja,"comida",3,"l");
        db.insertar_foto("a","al","inversa","almendras","poner frase aqui",R.drawable.almendras,"comida",3,"l");
        db.insertar_foto("a","al","inversa","alcachofa","poner frase aqui",R.drawable.alcachofa,"comida",4,"l");
        db.insertar_foto("a","al","inversa","albanil","poner frase aqui",R.drawable.albanil,"otro",3,"l");
        db.insertar_foto("a","al","inversa","almohada","poner frase aqui",R.drawable.almohada,"casa",4,"l");

        db.insertar_foto("ll","lla","directa","llama","poner frase aqui",R.drawable.llama,"animales",2,"lly");
        db.insertar_foto("ll","lla","directa","llave","poner frase aqui",R.drawable.llave,"otro",2,"lly");
        db.insertar_foto("ll","lla","directa","llavero","poner frase aqui",R.drawable.llavero,"otro",3,"lly");
        db.insertar_foto("ll","llu","directa","lluvia","poner frase aqui",R.drawable.lluvia,"otro",2,"lly");

        db.insertar_foto("m","ma","directa","mano","Mi mano es pequeña",R.drawable.mano,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mapa","poner frase aqui",R.drawable.mapa,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mariposa","poner frase aqui",R.drawable.mariposa,"animales",4,"m");
        db.insertar_foto("m","ma","directa","mariquita","poner frase aqui",R.drawable.mariquita,"animales",4,"m");
        db.insertar_foto("m","me","directa","melon","poner frase aqui",R.drawable.melon,"comida",2,"m");
        db.insertar_foto("m","me","directa","mesa","poner frase aqui",R.drawable.mesa,"otro",2,"m");
        db.insertar_foto("m","mi","directa","microfono","poner frase aqui",R.drawable.microfono,"otro",4,"m");
        db.insertar_foto("m","mo","directa","mochila","poner frase aqui",R.drawable.mochila,"otro",3,"m");
        db.insertar_foto("m","mo","directa","mono","poner frase aqui",R.drawable.mono,"animal",2,"m");
        db.insertar_foto("m","mu","directa","muleta","poner frase aqui",R.drawable.muleta,"otro",3,"m");
        db.insertar_foto("m","mu","directa","muñeca","poner frase aqui",R.drawable.muneca,"otro",3,"m");
        db.insertar_foto("e","em","inversa","embudo","poner frase aqui",R.drawable.embudo,"otro",3,"m");
        db.insertar_foto("e","em","inversa","empanada","poner frase aqui",R.drawable.empanada,"comida",4,"m");
        db.insertar_foto("e","em","inversa","empanadilla","poner frase aqui",R.drawable.empanadilla,"comida",5,"m");
        db.insertar_foto("i","im","inversa","impresora","poner frase aqui",R.drawable.impresora,"otro",4,"m");

        db.insertar_foto("n","na","directa","naranja","Comí una naranja de postre",R.drawable.naranja,"comida",3,"n");
        db.insertar_foto("n","na","directa","nariz","poner frase aqui",R.drawable.nariz,"otro",2,"n");
        db.insertar_foto("n","ne","directa","nevera","poner frase aqui",R.drawable.nevera,"casa",3,"n");
        db.insertar_foto("n","ni","directa","nido","poner frase aqui",R.drawable.nido,"otro",2,"n");
        db.insertar_foto("n","no","directa","noria","poner frase aqui",R.drawable.noria,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nubes","poner frase aqui",R.drawable.nubes,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nuez","poner frase aqui",R.drawable.nuez,"comida",2,"n");
        db.insertar_foto("a","an","inversa","ancla","poner frase aqui",R.drawable.ancla,"otro",2,"n");
        db.insertar_foto("e","en","inversa","enchufe","poner frase aqui",R.drawable.enchufe,"casa",3,"n");
        db.insertar_foto("i","in","inversa","indio","poner frase aqui",R.drawable.indio,"otro",2,"n");
        db.insertar_foto("o","on","inversa","once","poner frase aqui",R.drawable.once,"otro",2,"n");
        db.insertar_foto("a","an","inversa","antena","poner frase aqui",R.drawable.antena,"casa",3,"n");
        db.insertar_foto("e","en","inversa","enfermera","poner frase aqui",R.drawable.enfermera,"otro",4,"n");
        db.insertar_foto("i","in","inversa","incendio","poner frase aqui",R.drawable.incendio,"otro",3,"n");
        db.insertar_foto("i","in","inversa","interruptor","poner frase aqui",R.drawable.interruptor,"casa",4,"n");

        db.insertar_foto("o","o","directa","ojo","poner frase aqui",R.drawable.ojo,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","olla","poner frase aqui",R.drawable.olla,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","oreja","poner frase aqui",R.drawable.oreja,"otro",3,"vocales");
        db.insertar_foto("o","o","directa","oruga","poner frase aqui",R.drawable.oruga,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oveja","poner frase aqui",R.drawable.oveja,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oso","El oso es bonito",R.drawable.oso,"animal",2,"vocales");

        db.insertar_foto("p","pla","trabada","planeta","poner frase aqui",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pe","directa","perro","El * es marrón",R.drawable.perro,"animales",4,"p");
        db.insertar_foto("p","pla","trabada","platano","poner frase aqui",R.drawable.platano,"comida",3,"pl");
        db.insertar_foto("p","pla","trabada","playa","Me gusta estar en la *",R.drawable.playa,"playa",2,"pl");
        db.insertar_foto("p","pa","directa","patata","Me comi una *",R.drawable.patata,"comida",3,"p");
        db.insertar_foto("p","pan","directa","pan","Me comi un *",R.drawable.pan,"comida",1,"p");
        db.insertar_foto("p","pi","directa","pimiento","El * es rojo",R.drawable.pimiento,"comida",3,"p");
        db.insertar_foto("p","po","directa","policia","El * esta fuerte",R.drawable.policia,"otro",3,"p");
        db.insertar_foto("p","pu","directa","puma","El * es negro",R.drawable.puma,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","plato","me compre un *",R.drawable.plato,"comida",2,"pl");
        db.insertar_foto("p","plu","trabada","pluma","poner frase aqui",R.drawable.pluma,"otro",2,"pl");

        db.insertar_foto("r","ra","directa","ramas","poner frase aqui",R.drawable.ramas,"otro",2,"r");
        db.insertar_foto("r","ra","directa","raton","El raton es bonito ",R.drawable.raton,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rana","poner frase aqui",R.drawable.rana,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rayador","poner frase aqui",R.drawable.rayador,"casa",3,"r");
        db.insertar_foto("r","re","directa","regla","poner frase aqui",R.drawable.regla,"otro",2,"r");
        db.insertar_foto("r","re","directa","reloj","poner frase aqui",R.drawable.reloj,"otro",2,"r");
        db.insertar_foto("r","ri","directa","rio","poner frase aqui",R.drawable.rio,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rojo","poner frase aqui",R.drawable.rojo,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rosa","poner frase aqui",R.drawable.rosa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","arbol","poner frase aqui",R.drawable.arbol,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","arbitro","poner frase aqui",R.drawable.arbitro,"otro",3,"r");
        db.insertar_foto("a","as","inversa","aspa","poner frase aqui",R.drawable.aspa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","armario","poner frase aqui",R.drawable.armario,"casa",3,"r");
        db.insertar_foto("i","ir","inversa","invernadero","poner frase aqui",R.drawable.invernadero,"otro",5,"r");

        db.insertar_foto("s","sa","directa","salero","poner frase aqui",R.drawable.salero,"casa",3,"s");
        db.insertar_foto("s","si","directa","silla","poner frase aqui",R.drawable.silla,"otro",2,"s");
        db.insertar_foto("s","so","directa","sofa","poner frase aqui",R.drawable.sofa,"otro",2,"s");
        db.insertar_foto("s","so","directa","sopa","poner frase aqui",R.drawable.sopa,"casa",2,"s");
        db.insertar_foto("s","su","directa","suma","poner frase aqui",R.drawable.suma,"otro",2,"s");
        db.insertar_foto("a","as","inversa","ascensor","poner frase aqui",R.drawable.ascensor,"otro",3,"s");
        db.insertar_foto("a","as","inversa","aspiradora","poner frase aqui",R.drawable.aspiradora,"otro",4,"s");
        db.insertar_foto("e","es","inversa","espagueti","poner frase aqui",R.drawable.espagueti,"comida",4,"s");
        db.insertar_foto("e","es","inversa","escalera","poner frase aqui",R.drawable.escalera,"otro",4,"s");
        db.insertar_foto("e","es","inversa","estrella","poner frase aqui",R.drawable.estrella,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escoba","poner frase aqui",R.drawable.escoba,"otro",3,"s");
        db.insertar_foto("i","is","inversa","isla","poner frase aqui",R.drawable.isla,"playa",2,"s");
        db.insertar_foto("e","es","inversa","escarola","poner frase aqui",R.drawable.escarola,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escribir","poner frase aqui",R.drawable.escribir,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escuadra","poner frase aqui",R.drawable.escuadra,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escultura","poner frase aqui",R.drawable.escultura,"otro",4,"s");
        db.insertar_foto("e","es","inversa","esparragos","poner frase aqui",R.drawable.esparragos,"comida",4,"s");
        db.insertar_foto("e","es","inversa","espejo","poner frase aqui",R.drawable.espejo,"otro",3,"s");
        db.insertar_foto("e","es","inversa","espinacas","poner frase aqui",R.drawable.espinacas,"comida",4,"s");
        db.insertar_foto("e","es","inversa","esponja","poner frase aqui",R.drawable.esponja,"otro",3,"s");
        db.insertar_foto("e","es","inversa","estropajo","poner frase aqui",R.drawable.estropajo,"otro",4,"s");
        db.insertar_foto("o","os","inversa","ostras","poner frase aqui",R.drawable.ostras,"comida",2,"s");

        db.insertar_foto("t","ta","directa","tapa","poner frase aqui",R.drawable.tapa,"otro",2,"t");
        db.insertar_foto("t","ta","directa","taza","poner frase aqui",R.drawable.taza,"otro",2,"t");
        db.insertar_foto("t","te","directa","tenis","poner frase aqui",R.drawable.tenis,"otro",2,"t");
        db.insertar_foto("t","te","directa","tesoro","poner frase aqui",R.drawable.tesoro,"otro",3,"t");
        db.insertar_foto("t","ti","directa","tiburon","poner frase aqui",R.drawable.tiburon,"animales",3,"t");
        db.insertar_foto("t","ti","directa","timon","poner frase aqui",R.drawable.timon,"otro",2,"t");
        db.insertar_foto("t","to","directa","tomate","poner frase aqui",R.drawable.tomate,"comida",3,"t");
        db.insertar_foto("t","to","directa","toro","poner frase aqui",R.drawable.toro,"animales",2,"t");
        db.insertar_foto("t","tren","trabada","tren","El tren esta la estación ",R.drawable.tren,"vehiculos",1,"tr");
        db.insertar_foto("t","tri","trabada","triangulo","poner frase aqui",R.drawable.triangulo,"otro",4,"tr");
        db.insertar_foto("t","tru","trabada","trufas","poner frase aqui",R.drawable.trufas,"comida",2,"tr");

        db.insertar_foto("u","u","directa","uniforme","poner frase aqui",R.drawable.uniforme,"otro",4,"vocales");
        db.insertar_foto("u","u","directa","uva","poner frase aqui",R.drawable.uva,"comida",2,"vocales");
        db.insertar_foto("u","u","directa","una","poner frase aqui",R.drawable.una,"otro",2,"vocales");

        db.insertar_foto("v","va","directa","vaca","poner frase aqui",R.drawable.vaca,"animales",2,"bv");
        db.insertar_foto("v","va","directa","vaso","poner frase aqui",R.drawable.vaso,"otro",2,"bv");
        db.insertar_foto("v","ve","directa","vela","poner frase aqui",R.drawable.vela,"otros",2,"bv");
        db.insertar_foto("v","vo","directa","volante","poner frase aqui",R.drawable.volante,"otros",3,"bv");

        db.insertar_foto("y","ye","directa","yegua","poner frase aqui",R.drawable.yegua,"animales",2,"lly");
        db.insertar_foto("y","yo","directa","yogurt","poner frase aqui",R.drawable.yogurt,"comida",2,"lly");

        db.insertar_foto("z","za","directa","zanahoria","poner frase aqui",R.drawable.zanahoria,"comida",4,"cz");
        db.insertar_foto("z","za","directa","zapato","poner frase aqui",R.drawable.zapato,"comida",3,"cz");
        db.insertar_foto("z","zo","directa","zorro","poner frase aqui",R.drawable.zorro,"animales",2,"cz");

    }

    private void insertarNiveles() {
        for(int j=1;j<5;j++){
            db.insertar_nivel ("leerletras",j,"vocales");
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel ("leerletras",j, subnivelesDirectas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel ("silabasdirectas",j, subnivelesDirectas[i]);
            }
        }

        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel("palabrassilabasdirectas",j,subnivelesDirectas[i]);
            }
        }
       // db.insertar_nivel("palabrassilabasdirectas",1,"vocales");
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesInversas.length; i++){
                db.insertar_nivel ("silabasinversas",j, subnivelesInversas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesInversas.length; i++){
                db.insertar_nivel("palabrassilabasinversas",j,subnivelesInversas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesTrabadas.length; i++){
                db.insertar_nivel ("silabastrabadas",j, subnivelesTrabadas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesDirectas.length; i++){
                db.insertar_nivel("frasessilabasdirectas",j,subnivelesDirectas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesInversas.length; i++){
                db.insertar_nivel("frasessilabasinversas",j,subnivelesInversas[i]);
            }
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesTrabadas.length; i++){
                db.insertar_nivel("palabrassilabastrabadas",j,subnivelesTrabadas[i]);
            }
        }
    }
}
