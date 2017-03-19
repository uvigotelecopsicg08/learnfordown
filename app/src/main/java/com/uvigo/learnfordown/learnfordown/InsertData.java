package com.uvigo.learnfordown.learnfordown;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Juani on 13/02/2017.
 */

public class InsertData {
    DataBaseManager db;
    private Context context;
    private String[] subnivelesDirectas = new String[]{"p","m","t","bv","n","kcq","l","s","d","zc","f","j","lly","gu","ch","r"};
    private String[] subnivelesInversas = new String[]{"m","n","l","s","r"};
    private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","cr","fr","gl","gr","tr","tl","fr","fr","bvr"};
    private String[] subnivelesVocales = new String[] {"a","e","i","o","u"};
    private String[] subnivelesEscribirConsonantes=new String[]{"b","c","d","f","g","h","j","k","l","m","n","ñ","p","q","r","s","t","v","w","x","y","z"};
    public InsertData(Context context) {
        this.context =context;
        db = new DataBaseManager(context);


    }
    public void insertar(String nombre, int edad, HashMap<String,Boolean>gustos) {
        insertarNiveles();
        insertarFotos();
        db.insertar_user(nombre,edad,gustos);
        db.close();
    }

    private void insertarFotos() {

        db.insertar_foto("a","a","directa","abeja","La * hace miel ",R.drawable.abeja,"animales",3,"vocales");
        db.insertar_foto("a","a","directa","agua","Bebo bastante * ",R.drawable.agua,"otro",2,"vocales");
        db.insertar_foto("a","a","directa","amarillo","Me gusta mucho el color *",R.drawable.amarillo,"otro",4,"vocales");

        db.insertar_foto("b","bu","directa","bufanda","La * abriga",R.drawable.bufanda,"ropa",3,"bv");
        db.insertar_foto("b","ba","directa","baca","Mi padre puso las maletas en la *",R.drawable.baca,"otros",2,"bv");
        db.insertar_foto("b","be","directa","bebe","Mi hermano pequeño es un *",R.drawable.bebe,"otros",2,"bv");
        db.insertar_foto("b","bi","directa","biberon","Cuando era pequeño tomaba el *",R.drawable.biberon,"comida",3,"bv");
        db.insertar_foto("b","bi","directa","bicicleta","Fui al parque en *",R.drawable.bicicleta,"otros",4,"bv");
        db.insertar_foto("b","bu","directa","buho","El * es un animal nocturno",R.drawable.buho,"animales",2,"bv");
        db.insertar_foto("b","bu","directa","burro","Cuando era pequeño monté en * ",R.drawable.burro,"animales",2,"bv");
        db.insertar_foto("b","bru","trabada","brujula","Al monte llevamos una *",R.drawable.brujula,"otro",3,"bvr");
        db.insertar_foto("b","blu","trabada","blusa","Mi madre tiene una *",R.drawable.blusa,"ropa",2,"bvr");
        db.insertar_foto("b","bra","trabada","brazo","Me duele el *",R.drawable.brazo,"otro",2,"bvr");
        db.insertar_foto("b","bre","trabada","brecol","Mi comida favorita es el *",R.drawable.brecol,"comida",2,"bvr");
        db.insertar_foto("b","bri","trabada","bricolaje","poner frase aqui",R.drawable.bricolaje,"otro",4,"bvr");
        db.insertar_foto("b","bro","trabada","brote","Aquí hay un * de hortensias",R.drawable.brote,"otro",2,"bvr");
        db.insertar_foto("b","bol","directa","bol","Pon agua en es *",R.drawable.bol,"otros",1,"mono");
        db.insertar_foto("b","buey","directa","buey","Qué bonito es ese *",R.drawable.buey,"animales",1,"mono");

        db.insertar_foto("c","co","directa","coche","El * de mi madre es gris ",R.drawable.coche,"vehiculos",2,"kcq");
        db.insertar_foto("c","ca","directa","caballo","Ayer monté a * ",R.drawable.caballo,"animales",3,"kcq");
        db.insertar_foto("c","ca","directa","cabra","Mi abuela tiene muchos *",R.drawable.cabra,"animales",2,"kcq");
        db.insertar_foto("c","ca","directa","casa","La * es blanca",R.drawable.casa,"casa",2,"kcq");
        db.insertar_foto("c","ca","directa","camello","Nunca he montado a * ",R.drawable.camello,"animales",3,"kcq");
        db.insertar_foto("c","co","directa","cocina","La parte de la casa donde se hace la comida es la *",R.drawable.cocina,"casa",3,"kcq");
        db.insertar_foto("c","co","directa","cocodrilo","No me gusta el *",R.drawable.cocodrilo,"animales",4,"kcq");
        db.insertar_foto("c","co","directa","conejo","En el bosque encontramos un *",R.drawable.conejo,"animales",3,"kcq");
        db.insertar_foto("c","cu","directa","cubo","Hicimos un * con cartón",R.drawable.cubo,"otros",2,"kcq");
        db.insertar_foto("c","cu","directa","cuchillo","El filete se corta con un *",R.drawable.cuchillo,"casa",3,"kcq");
        db.insertar_foto("c","cu","directa","cuna","Mi prima pequeña aún duerme en la *",R.drawable.cuna,"casa",2,"kcq");
        db.insertar_foto("c","cla","trabada","clarinete","Mi madre toca el *",R.drawable.clarinete,"musica",2,"cl");
        db.insertar_foto("c","cla","trabada","clave","Estoy aprendiendo a dibujar *",R.drawable.clave,"musica",2,"cl");
        db.insertar_foto("c","cro","trabada","croquetas","A mi padre no le gustan las *",R.drawable.croquetas,"comida",2,"cr");
        db.insertar_foto("c","clip","trabada","clip","",R.drawable.clip,"otros",1,"mono");

        db.insertar_foto("c","ci","directa","cine","Voy al * una vez al mes",R.drawable.cine,"otros",2,"zc");
        db.insertar_foto("c","ce","directa","cebolla","Mi madre prepara muchas comidas con *",R.drawable.cebolla,"comida",3,"zc");
        db.insertar_foto("c","ce","directa","cerezas","Mi abuela nos ha dado muchas *",R.drawable.cerezas,"comida",3,"zc");
        db.insertar_foto("c","ci","directa","ciruela","Ayer vi en la frutería *",R.drawable.ciruela,"comida",3,"zc");


        db.insertar_foto("ch","cha","directa","chaleco","Mi abuela me calcetó un *",R.drawable.chaleco,"ropa",3,"ch");
        db.insertar_foto("ch","cha","directa","chaqueta","Pon una * antes de salir de casa",R.drawable.chaqueta,"ropa",3,"ch");
        db.insertar_foto("ch","che","directa","cheque","Ese * vale mucho dinero",R.drawable.cheque,"otro",2,"ch");
        db.insertar_foto("ch","chi","directa","chicles","Cuando era pequeño no me dejaban comer *",R.drawable.chicles,"comida",2,"ch");
        db.insertar_foto("ch","cho","directa","chocolate","Puedo comer una tableta entera de *",R.drawable.chocolate,"comida",4,"ch");
        db.insertar_foto("ch","cho","directa","chorizos","No me gustan los * picantes",R.drawable.chorizos,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","chupete","El hermano pequeño de Pepe tiene un *",R.drawable.chupete,"otros",3,"ch");
        db.insertar_foto("ch","chu","directa","churrasco","Los domingos comemos *",R.drawable.churrasco,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","churros","La churrera de la esquina vende * muy ricos",R.drawable.churro,"comida",2,"ch");
        db.insertar_foto("ch","chi","directa","chimos","Voy a comer * de varios colores",R.drawable.chimos,"comida",2,"ch");
        db.insertar_foto("ch","chu","directa","chucherías","Las * son malas para los dientes",R.drawable.chucherias,"comida",4,"ch");

        db.insertar_foto("d","da","directa","damas","Tengo que aprender a jugar a las *",R.drawable.damas,"otros",2,"d");
        db.insertar_foto("d","de","directa","desierto","En el * hay escasez de agua",R.drawable.desierto,"otros",3,"d");
        db.insertar_foto("d","di","directa","dibujar","Me encanta * y pintar",R.drawable.dibujar,"otros",3,"d");
        db.insertar_foto("d","di","directa","dinero","Mis abuelos a veces me dan *",R.drawable.dinero,"otros",3,"d");
        db.insertar_foto("d","di","directa","diploma","De mayor quiero tener un *",R.drawable.diploma,"otros",3,"d");
        db.insertar_foto("d","do","directa","domino","Uno más uno es  *",R.drawable.domino,"otros",3,"d");
        db.insertar_foto("d","du","directa","ducha","Deberían poner más * en las playas",R.drawable.ducha,"casa",2,"d");
        db.insertar_foto("d","da","directa","datiles","*",R.drawable.datiles,"comida",3,"d");
        db.insertar_foto("d","de","directa","dedal","*",R.drawable.dedal,"casa",2,"d");
        db.insertar_foto("d","de","directa","dedo"," * ",R.drawable.dedo,"otros",2,"d");
        db.insertar_foto("d","di","directa","dinosaurio"," * ",R.drawable.dinosaurio,"animales",4,"d");
        db.insertar_foto("d","do","directa","doce"," * ",R.drawable.doce,"otros",2,"d");
        db.insertar_foto("d","dos","directa","dos","Uno mas uno es * ",R.drawable.dos,"otros",1,"mono");

        db.insertar_foto("e","e","directa","elefante","En en zoo hay *",R.drawable.elefante,"animales",4,"vocales");

        db.insertar_foto("f","fa","directa","familia","Mi * me quiere",R.drawable.familia,"otros",3,"f");
        db.insertar_foto("f","fa","directa","faro","Por la noche el * tiene luz",R.drawable.faro,"otros",2,"f");
        db.insertar_foto("f","fi","directa","fideos","poner frase aqui",R.drawable.fideos,"comida",3,"f");
        db.insertar_foto("f","fi","directa","figuras","poner frase aqui",R.drawable.figuras,"otros",3,"f");
        db.insertar_foto("f","fla","trabada","flamenco","Tengo una foto en que salgo con * del zoo",R.drawable.flamenco,"animales",3,"f");
        db.insertar_foto("f","flan","trabada","flan","De postre había *",R.drawable.flan,"comida",1,"mono");
        db.insertar_foto("f","flo","trabada","flores","poner frase aqui",R.drawable.flores,"otros",2,"fl");
        db.insertar_foto("f","fre","trabada","fresa","Me encantan las * con nata",R.drawable.fresa,"comida",2,"fr");
        db.insertar_foto("f","fru","trabada","frutero","En el * hay varios tipos de fruta",R.drawable.frutero,"comida",3,"fr");
        db.insertar_foto("f","fu","directa","fular","Me compraron un * nuevo",R.drawable.fular,"ropa",2,"f");
        db.insertar_foto("f","fax","directa","fax","poner frase aqui",R.drawable.fax,"otros",1,"mono");
        db.insertar_foto("f","flor","trabada","flor","poner frase aqui",R.drawable.flor,"otros",1,"mono");

        db.insertar_foto("g","ga","directa","gato","Me arañó el *",R.drawable.gato,"animales",1,"gu");
        db.insertar_foto("g","gui","directa","guitarra","Quiero aprender a tocar la *",R.drawable.guitarra,"otro",3,"gu");
        db.insertar_foto("g","ga","directa","gallina","poner frase aqui",R.drawable.gallina,"animales",3,"gu");
        db.insertar_foto("g","ga","directa","gallo","Me desperté con el canto del *",R.drawable.gallo,"animales",2,"gu");
        db.insertar_foto("g","gue","directa","guepardo","Me desperté con el canto del *",R.drawable.guepardo,"animales",3,"gu");
        db.insertar_foto("g","gi","directa","girasol","Las pipas salen del *",R.drawable.girasol,"otro",3,"j");
        db.insertar_foto("g","go","directa","goma","Borro tanto que ya gasté toda la *",R.drawable.goma,"otro",2,"gu");
        db.insertar_foto("g","go","directa","gorila","poner frase aqui",R.drawable.gorila,"animales",3,"gu");
        db.insertar_foto("g","go","directa","gorro","poner frase aqui",R.drawable.gorro,"ropa",2,"gu");
        db.insertar_foto("g","gu","directa","gusasnitos","No puedo comer tantos *",R.drawable.gusanitos,"comida",4,"gu");
        db.insertar_foto("g","gu","directa","gusano","Vi un * junto a las flores del jardín",R.drawable.gusano,"animales",3,"gu");
        db.insertar_foto("g","gel","directa","gel","",R.drawable.gel,"otro",1,"mono");
        db.insertar_foto("g","golf","directa","golf","",R.drawable.golf,"otro",1,"mono");
        db.insertar_foto("g","gol","directa","gol","",R.drawable.gol,"otro",1,"mono");
        db.insertar_foto("g","gris","trabada","gris","",R.drawable.gris,"otro",1,"mono");

        db.insertar_foto("i","i","directa","iglu","poner frase aqui",R.drawable.iglu,"casa",2,"vocales");
        db.insertar_foto("i","i","directa","imán","poner frase aqui",R.drawable.iman,"otro",2,"vocales");

        db.insertar_foto("j","ja","directa","jabali","poner frase aqui",R.drawable.jabali,"animales",3,"j");
        db.insertar_foto("j","ja","directa","jabon","poner frase aqui",R.drawable.jabon,"otro",2,"j");
        db.insertar_foto("j","ja","directa","jamon","Esta tarde merendaré un bocadillo de *",R.drawable.jamon,"comida",2,"j");
        db.insertar_foto("j","je","directa","jeringa","Me pusieron la vacuna con una *",R.drawable.jeringa,"otro",3,"j");
        db.insertar_foto("j","ji","directa","jirafa","Las * tienen el cuello muy largo",R.drawable.jirafa,"animales",3,"j");
        db.insertar_foto("j","jo","directa","joyas","poner frase aqui",R.drawable.joyas,"otro",2,"j");
        db.insertar_foto("j","ju","directa","judias","Necesito comprar unas * para hacer ensaladilla",R.drawable.judias,"comida",3,"j");

        db.insertar_foto("k","ki","directa","kiwi","Necesito comprar unos * ",R.drawable.kiwi,"comida",2,"kcq");

        db.insertar_foto("l","la","directa","lapiz","poner frase aqui",R.drawable.lapiz,"otro",2,"l");
        db.insertar_foto("l","la","directa","labios","Mi abuela se está pintando los *",R.drawable.labios,"otro",2,"l");
        db.insertar_foto("l","la","directa","lata","Compramos en el super una * de sardinas",R.drawable.lata,"otro",2,"l");
        db.insertar_foto("l","le","directa","leon","poner frase aqui",R.drawable.leon,"animales",2,"l");
        db.insertar_foto("l","li","directa","libros","poner frase aqui",R.drawable.libros,"otro",2,"l");
        db.insertar_foto("l","li","directa","limon","En verano tomo cada día agua con *",R.drawable.limon,"comida",2,"l");
        db.insertar_foto("l","lo","directa","lobo","poner frase aqui",R.drawable.lobo,"animales",2,"l");
        db.insertar_foto("l","lo","directa","loro","El * de mi vecina habla mucho",R.drawable.loro,"animales",2,"l");
        db.insertar_foto("l","lu","directa","luna","poner frase aqui",R.drawable.luna,"otro",2,"l");
        db.insertar_foto("l","lu","directa","lupa","poner frase aqui",R.drawable.lupa,"otro",2,"l");
        db.insertar_foto("a","al","inversa","almeja","poner frase aqui",R.drawable.almeja,"comida",3,"l");
        db.insertar_foto("a","al","inversa","almendras","De los frutos secos lo que más me gusta son las *",R.drawable.almendras,"comida",3,"l");
        db.insertar_foto("a","al","inversa","alcachofa","Esta ensalada lleva *",R.drawable.alcachofa,"comida",4,"l");
        db.insertar_foto("a","al","inversa","albanil","Mi padre trabaja de *",R.drawable.albanil,"otro",3,"l");
        db.insertar_foto("a","al","inversa","almohada","Mi * es muy blandita",R.drawable.almohada,"casa",4,"l");
        db.insertar_foto("u","ul","inversa","baul","En mi casa hay un * para guardar los juguetes",R.drawable.baul,"casa",2,"l"); //La vocal y el sonido de esto...

        db.insertar_foto("ll","lla","directa","llama","poner frase aqui",R.drawable.llama,"animales",2,"lly");
        db.insertar_foto("ll","lla","directa","llave","No puedo perder la * de casa",R.drawable.llave,"otro",2,"lly");
        db.insertar_foto("ll","lla","directa","llavero","Tengo un * con la llave de casa",R.drawable.llavero,"otro",3,"lly");
        db.insertar_foto("ll","llu","directa","lluvia","Me mojaré con la * si no llevo el paraguas",R.drawable.lluvia,"otro",2,"lly");
        db.insertar_foto("ll","llo","directa","cepillo","poner frase aqui",R.drawable.cepillo,"casa",3,"lly");
        db.insertar_foto("ll","lla","directa","cerilla","poner frase aqui",R.drawable.cerilla,"casa",3,"lly");
        db.insertar_foto("ll","llo","directa","criollo","poner frase aqui",R.drawable.criollo,"comida",2,"lly");
        db.insertar_foto("ll","lla","directa","escobilla","poner frase aqui",R.drawable.escobilla,"casa",4,"lly");
        db.insertar_foto("ll","llo","directa","grillo","poner frase aqui",R.drawable.grillo,"animales",2,"lly");
        db.insertar_foto("ll","lla","directa","medalla","poner frase aqui",R.drawable.medalla,"otros",3,"lly");
        db.insertar_foto("ll","lla","directa","natilla","poner frase aqui",R.drawable.natillas,"comida",3,"lly");
        db.insertar_foto("ll","llo","directa","ovillo","poner frase aqui",R.drawable.ovillos,"ropa",3,"lly");
        db.insertar_foto("ll","lla","directa","papilla","poner frase aqui",R.drawable.papilla,"comida",3,"lly");
        db.insertar_foto("ll","lla","directa","rodilla","poner frase aqui",R.drawable.rodilla,"otros",3,"lly");
        db.insertar_foto("ll","lla","directa","toalla","poner frase aqui",R.drawable.toallas,"casa",3,"lly");

        db.insertar_foto("m","ma","directa","mano","Mi * es pequeña",R.drawable.mano,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mapa","poner frase aqui",R.drawable.mapa,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mariposa","poner frase aqui",R.drawable.mariposa,"animales",4,"m");
        db.insertar_foto("m","ma","directa","mariquita","poner frase aqui",R.drawable.mariquita,"animales",4,"m");
        db.insertar_foto("m","me","directa","melon","poner frase aqui",R.drawable.melon,"comida",2,"m");
        db.insertar_foto("m","me","directa","mesa","poner frase aqui",R.drawable.mesa,"otro",2,"m");
        db.insertar_foto("m","mi","directa","microfono","poner frase aqui",R.drawable.microfono,"otro",4,"m");
        db.insertar_foto("m","mo","directa","mochila","poner frase aqui",R.drawable.mochila,"otro",3,"m");
        db.insertar_foto("m","mo","directa","mono","poner frase aqui",R.drawable.mono,"animal",2,"m");
        db.insertar_foto("m","mu","directa","muleta","Cuando Eva cayó tuvo que usar una * para poder caminar",R.drawable.muleta,"otro",3,"m");
        db.insertar_foto("m","mu","directa","muñeca","Le regalaron una * a mi hermana",R.drawable.muneca,"otro",3,"m");
        db.insertar_foto("e","em","inversa","embudo","poner frase aqui",R.drawable.embudo,"otro",3,"m");
        db.insertar_foto("e","em","inversa","empanada","Mi abuela hizo una * de pan de maiz con mejillones",R.drawable.empanada,"comida",4,"m");
        db.insertar_foto("e","em","inversa","empanadilla","Esta * está un poco quemada",R.drawable.empanadilla,"comida",5,"m");
        db.insertar_foto("i","im","inversa","impresora","Necesito una * para imprimir este dibujo",R.drawable.impresora,"otro",4,"m");
        db.insertar_foto("m","mar","directa","mar","",R.drawable.mar,"otro",1,"mono");
        db.insertar_foto("m","miel","directa","miel","",R.drawable.miel,"comida",1,"mono");
        db.insertar_foto("m","mil","directa","mil","",R.drawable.mil,"otro",1,"mono");

        db.insertar_foto("n","na","directa","naranja","Comí una * de postre",R.drawable.naranja,"comida",3,"n");
        db.insertar_foto("n","na","directa","nariz","Me pica la *",R.drawable.nariz,"otro",2,"n");
        db.insertar_foto("n","ne","directa","nevera","En la * se mantienen las cosas frías",R.drawable.nevera,"casa",3,"n");
        db.insertar_foto("n","ni","directa","nido","Ese pájaro hizo un * en el manzano de nuestro jardín",R.drawable.nido,"otro",2,"n");
        db.insertar_foto("n","no","directa","noria","La * da muchas vueltas",R.drawable.noria,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nubes","El cielo está cubierto de *",R.drawable.nubes,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nuez","poner frase aqui",R.drawable.nuez,"comida",2,"n");
        db.insertar_foto("a","an","inversa","ancla","poner frase aqui",R.drawable.ancla,"otro",2,"n");
        db.insertar_foto("e","en","inversa","enchufe","Ten cuidado con el *",R.drawable.enchufe,"casa",3,"n");
        db.insertar_foto("i","in","inversa","indio","Me disfrazaré de *",R.drawable.indio,"otro",2,"n");
        db.insertar_foto("o","on","inversa","once","Tengo * años",R.drawable.once,"otro",2,"n");
        db.insertar_foto("a","an","inversa","antena","poner frase aqui",R.drawable.antena,"casa",3,"n");
        db.insertar_foto("e","en","inversa","enfermera","La * me puso las vacunas",R.drawable.enfermera,"otro",4,"n");
        db.insertar_foto("i","in","inversa","incendio","poner frase aqui",R.drawable.incendio,"otro",3,"n");
        db.insertar_foto("i","in","inversa","interruptor","Dale al * para encender la luz",R.drawable.interruptor,"casa",4,"n");

        db.insertar_foto("o","o","directa","ojo","Ana tiene los * de color azul",R.drawable.ojo,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","olla","poner frase aqui",R.drawable.olla,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","oreja","poner frase aqui",R.drawable.oreja,"otro",3,"vocales");
        db.insertar_foto("o","o","directa","oruga","poner frase aqui",R.drawable.oruga,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oveja","poner frase aqui",R.drawable.oveja,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oso","El * es bonito",R.drawable.oso,"animal",2,"vocales");

        db.insertar_foto("p","pla","trabada","planeta","poner frase aqui",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pe","directa","perro","El * es marrón",R.drawable.perro,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","platano","poner frase aqui",R.drawable.platano,"comida",3,"pl");
        db.insertar_foto("p","pla","trabada","playa","Me gusta estar en la *",R.drawable.playa,"playa",2,"pl");
        db.insertar_foto("p","pa","directa","patata","Me comi una *",R.drawable.patata,"comida",3,"p");
        db.insertar_foto("p","pan","directa","pan","Me comi un *",R.drawable.pan,"comida",1,"mono");
        db.insertar_foto("p","pi","directa","pimiento","El * es rojo",R.drawable.pimiento,"comida",3,"p");
        db.insertar_foto("p","pez","directa","pez","El * vive en el agua",R.drawable.pez,"animales",1,"mono");
        //db.insertar_foto("p","po","directa","policia","El * esta fuerte",R.drawable.policia,"otro",3,"p");
        //db.insertar_foto("p","pu","directa","puma","El * es negro",R.drawable.puma,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","plato","Me compre un *",R.drawable.plato,"comida",2,"pl");
        db.insertar_foto("p","plu","trabada","pluma","A la gallina se le cayó una *",R.drawable.pluma,"otro",2,"pl");

        db.insertar_foto("q","que","directa","queso","Me gusta mucho el *",R.drawable.queso,"comida",2,"kcq");

        db.insertar_foto("r","ra","directa","ramas","poner frase aqui",R.drawable.ramas,"otro",2,"r");
        db.insertar_foto("r","ra","directa","raton","El raton es bonito ",R.drawable.raton,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rana","poner frase aqui",R.drawable.rana,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rayador","poner frase aqui",R.drawable.rayador,"casa",3,"r");
        db.insertar_foto("r","re","directa","regla","poner frase aqui",R.drawable.regla,"otro",2,"r");
        db.insertar_foto("r","re","directa","reloj","Mira el * y dime que hora es",R.drawable.reloj,"otro",2,"r");
        db.insertar_foto("r","ri","directa","rio","poner frase aqui",R.drawable.rio,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rojo","Mi pantalón es de color *",R.drawable.rojo,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rosa","Mi muñeca tiene un vestido de color *",R.drawable.rosa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","arbol","poner frase aqui",R.drawable.arbol,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","arbitro","El * acertó pitando fuera de juego en esa jugada",R.drawable.arbitro,"otro",3,"r");
        db.insertar_foto("a","as","inversa","aspa","Los molinos tienen * muy grandes",R.drawable.aspa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","armario","El * de mi habitación es muy amplio",R.drawable.armario,"casa",3,"r");
        db.insertar_foto("i","ir","inversa","invernadero","En el * hay pimientos plantados",R.drawable.invernadero,"otro",5,"r");

        db.insertar_foto("s","sa","directa","salero","Se rompió el * y cayó toda la sal",R.drawable.salero,"casa",3,"s");
        db.insertar_foto("s","si","directa","silla","Me senté en una *",R.drawable.silla,"otro",2,"s");
        db.insertar_foto("s","so","directa","sofa","Estoy viendo la televisión mientras estoy sentado en el *",R.drawable.sofa,"otro",2,"s");
        db.insertar_foto("s","so","directa","sopa","En las noches de frío me gusta tomar *",R.drawable.sopa,"casa",2,"s");
        db.insertar_foto("s","su","directa","suma","La * de 1 y 2 da como resultado 3",R.drawable.suma,"otro",2,"s");
        db.insertar_foto("s","sal","directa","sal","",R.drawable.sal,"comida",1,"mono");
        db.insertar_foto("s","seis","directa","seis","",R.drawable.seis,"otros",1,"mono");
        db.insertar_foto("s","sol","directa","sol","",R.drawable.sol,"otros",1,"mono");
    //    db.insertar_foto("s","sa","directa","salero","Se rompió el * y cayó toda la sal",R.drawable.salero,"casa",3,"s");
        db.insertar_foto("a","as","inversa","ascensor","Para ir a la última planta de edificio subimos en *",R.drawable.ascensor,"otro",3,"s");
        db.insertar_foto("a","as","inversa","aspiradora","Mi padre está pasando la * en el salón",R.drawable.aspiradora,"otro",4,"s");
        db.insertar_foto("e","es","inversa","espagueti","poner frase aqui",R.drawable.espagueti,"comida",4,"s");
        db.insertar_foto("e","es","inversa","escalera","poner frase aqui",R.drawable.escalera,"otro",4,"s");
        db.insertar_foto("e","es","inversa","estrella","Podemos mirar al cielo por la noche para ver las *",R.drawable.estrella,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escoba","Coge la * y ponte a barrer",R.drawable.escoba,"otro",3,"s");
        db.insertar_foto("i","is","inversa","isla","Iremos en barco a la *",R.drawable.isla,"playa",2,"s");
        db.insertar_foto("e","es","inversa","escarola","Haremos una ensalada con *",R.drawable.escarola,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escribir","Vamos a * una carta para mis abuelos",R.drawable.escribir,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escuadra","poner frase aqui",R.drawable.escuadra,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escultura","poner frase aqui",R.drawable.escultura,"otro",4,"s");
        db.insertar_foto("e","es","inversa","esparragos","poner frase aqui",R.drawable.esparragos,"comida",4,"s");
        db.insertar_foto("e","es","inversa","espejo","Puedes verlo si te miras al *",R.drawable.espejo,"otro",3,"s");
        db.insertar_foto("e","es","inversa","espinacas","Me gusta la tortilla de *",R.drawable.espinacas,"comida",4,"s");
        db.insertar_foto("e","es","inversa","esponja","Siempre me ducho con una * rosa",R.drawable.esponja,"otro",3,"s");
        db.insertar_foto("e","es","inversa","estropajo","poner frase aqui",R.drawable.estropajo,"otro",4,"s");
        db.insertar_foto("o","os","inversa","ostras","poner frase aqui",R.drawable.ostras,"comida",2,"s");

        db.insertar_foto("t","ta","directa","tapa","Ponle la * a la pota",R.drawable.tapa,"otro",2,"t");
        db.insertar_foto("t","ta","directa","taza","Necesito una * nueva",R.drawable.taza,"otro",2,"t");
        db.insertar_foto("t","te","directa","tenis","Esta tarde jugaremos al *",R.drawable.tenis,"otro",2,"t");
        db.insertar_foto("t","te","directa","te","",R.drawable.te,"comida",1,"mono");
        db.insertar_foto("t","tres","directa","tres","",R.drawable.tres,"otro",1,"mono");
        db.insertar_foto("t","te","directa","tesoro","poner frase aqui",R.drawable.tesoro,"otro",3,"t");
        db.insertar_foto("t","ti","directa","tiburon","poner frase aqui",R.drawable.tiburon,"animales",3,"t");
        db.insertar_foto("t","ti","directa","timon","poner frase aqui",R.drawable.timon,"otro",2,"t");
        db.insertar_foto("t","to","directa","tomate","Me gusta el * en la ensalada",R.drawable.tomate,"comida",3,"t");
        db.insertar_foto("t","to","directa","toro","poner frase aqui",R.drawable.toro,"animales",2,"t");
        db.insertar_foto("t","tu","directa","tuna","poner frase aqui",R.drawable.tuna,"musica",2,"t");
        db.insertar_foto("t","tren","trabada","tren","El * está en la estación ",R.drawable.tren,"vehiculos",1,"mono");
        db.insertar_foto("t","tri","trabada","triangulo","poner frase aqui",R.drawable.triangulo,"otro",4,"tr");
        db.insertar_foto("t","tru","trabada","trufas","poner frase aqui",R.drawable.trufas,"comida",2,"tr");

        db.insertar_foto("u","u","directa","uniforme","Tengo que llevar * al colegio",R.drawable.uniforme,"otro",4,"vocales");
        db.insertar_foto("u","u","directa","uva","No me gustan las * negras",R.drawable.uva,"comida",2,"vocales");
        db.insertar_foto("u","u","directa","una","La * del dedo pulgar se rompió",R.drawable.una,"otro",2,"vocales");
        db.insertar_foto("u","u","directa","uno","",R.drawable.uno,"otro",1,"mono");

        db.insertar_foto("v","va","directa","vaca","La * da leche",R.drawable.vaca,"animales",2,"bv");
        db.insertar_foto("v","va","directa","vaso","Quiero un * de zumo",R.drawable.vaso,"otro",2,"bv");
        db.insertar_foto("v","ve","directa","vela","Esta es una * perfumada",R.drawable.vela,"otros",2,"bv");
        db.insertar_foto("v","vo","directa","volante","Mi padre cuando conduce mantiene las 2 manos en el *",R.drawable.volante,"otros",3,"bv");

        db.insertar_foto("y","ye","directa","yegua","poner frase aqui",R.drawable.yegua,"animales",2,"lly");
        db.insertar_foto("y","yo","directa","yogurt","poner frase aqui",R.drawable.yogurt,"comida",2,"lly");

        db.insertar_foto("z","za","directa","zanahoria","Me gustan las * crudas",R.drawable.zanahoria,"comida",4,"zc");
        db.insertar_foto("z","za","directa","zapato","Tengo unos * rojos",R.drawable.zapato,"comida",3,"zc");
        db.insertar_foto("z","zo","directa","zorro","poner frase aqui",R.drawable.zorro,"animales",2,"zc");
        db.insertar_foto("z","zu","directa","zumo","Que rico ese *",R.drawable.zumo,"animales",2,"zc");

    }

    private void insertarNiveles() {
        for(int j=1;j<5;j++){
            for(int i=0;i< subnivelesVocales.length; i++) {
                db.insertar_nivel("leerletras", j,subnivelesVocales[i]);
            }
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
            //for(int i = 0; i< subnivelesDirectas.length; i++){
            //    db.insertar_nivel("palabrassilabasdirectas",j,subnivelesDirectas[i]);
          //  }
            db.insertar_nivel("palabrassilabasdirectas",j,"subnivelunico");
        }
       // db.insertar_nivel("palabrassilabasdirectas",1,"vocales");
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesInversas.length; i++){
                db.insertar_nivel ("silabasinversas",j, subnivelesInversas[i]);
            }
        }
        for(int j=1;j<5;j++){
           // for(int i = 0; i< subnivelesInversas.length; i++){
            //    db.insertar_nivel("palabrassilabasinversas",j,subnivelesInversas[i]);
          //  }
            db.insertar_nivel("palabrassilabasinversas",j,"subnivelunico");
        }
        for(int j=1;j<5;j++){
            for(int i = 0; i< subnivelesTrabadas.length; i++){
                db.insertar_nivel ("silabastrabadas",j, subnivelesTrabadas[i]);
            }
        }
        for(int j=1;j<5;j++){

            db.insertar_nivel("palabrassilabastrabadas",j,"subnivelunico");
        }
        for(int j=1;j<5;j++){
         //   for(int i = 0; i< subnivelesDirectas.length; i++){
          //      db.insertar_nivel("frasessilabasdirectas",j,subnivelesDirectas[i]);
           // }
            db.insertar_nivel("frasessilabasdirectas",j,"subnivelunico");
        }
        for(int j=1;j<5;j++){
        //    for(int i = 0; i< subnivelesInversas.length; i++){
           //     db.insertar_nivel("frasessilabasinversas",j,subnivelesInversas[i]);
           // }
            db.insertar_nivel("frasessilabasinversas",j,"subnivelunico");
        }
        for(int j=1;j<5;j++){
         //   for(int i = 0; i< subnivelesTrabadas.length; i++){
           //     db.insertar_nivel("frasessilabastrabadas",j,subnivelesTrabadas[i]);
            //   }
            db.insertar_nivel("frasessilabastrabadas",j,"subnivelunico");
        }
        for(int i = 0; i< subnivelesVocales.length; i++) {
            db.insertar_nivel("escribirletras", 1, subnivelesVocales[i]);
        }
        for(int i=0;i<subnivelesEscribirConsonantes.length;i++) {
            db.insertar_nivel("escribirletras", 1,subnivelesEscribirConsonantes[i]);
        }
    }
}
