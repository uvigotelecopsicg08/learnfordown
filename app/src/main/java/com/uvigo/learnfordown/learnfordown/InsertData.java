package com.uvigo.learnfordown.learnfordown;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Juani on 13/02/2017.
 */

public class InsertData {
    DataBaseManager db;
    private Context context;
  //  private String[] subnivelesDirectas = new String[]{"p","m","t","bv","n","ñ","kcq","l","s","d","zc","h","f","j","lly","gu","ch","r"};
  private String[] subnivelesDirectas = new String[]{"p"};
    private String[] subnivelesInversas = new String[]{"m","n","l","s","r"};
   // private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","cr","fr","gl","gr","tr","tl","fr","fr"};
   private String[] subnivelesTrabadas = new String[]{"pl","pr","br","bl","cl","cr","fr","gr","gl","tr"};
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
        db.insertar_foto("b","be","directa","bebé","Mi hermano pequeño es un *",R.drawable.bebe,"otros",2,"bv");
        db.insertar_foto("b","bi","directa","biberón","Cuando era pequeño tomaba el *",R.drawable.biberon,"comida",3,"bv");
        db.insertar_foto("b","bi","directa","bicicleta","Fui al parque en *",R.drawable.bicicleta,"otros",4,"bv");
        db.insertar_foto("b","bu","directa","búho","El * es un animal nocturno",R.drawable.buho,"animales",2,"bv");
        db.insertar_foto("b","bu","directa","burro","Cuando era pequeño monté en * ",R.drawable.burro,"animales",2,"bv");
        db.insertar_foto("b","bru","trabada","brújula","Al monte llevamos una *",R.drawable.brujula,"otro",3,"br");
        db.insertar_foto("b","blu","trabada","blusa","Mi madre tiene una * amarilla",R.drawable.blusa,"ropa",2,"bl");
        db.insertar_foto("b","bra","trabada","brazo","Me duele el *",R.drawable.brazo,"otro",2,"br");
        db.insertar_foto("b","bre","trabada","brécol","Mi comida favorita es el *",R.drawable.brecol,"comida",2,"br");
        db.insertar_foto("b","bri","trabada","bricolaje","Se me da muy bien el *",R.drawable.bricolaje,"otro",4,"br");
        db.insertar_foto("b","bro","trabada","brote","Aquí hay un * de hortensias",R.drawable.brote,"otro",2,"br");
        db.insertar_foto("b","bol","directa","bol","Me como la ensalada en un *",R.drawable.bol,"otros",1,"mono");
       // db.insertar_foto("b","bol","directa","bol","Pon agua en el *",R.drawable.bol,"otros",1,"mono");
        db.insertar_foto("b","buey","directa","buey","Qué bonito es ese *",R.drawable.buey,"animales",1,"mono");

        db.insertar_foto("c","co","directa","coche","El * de mi madre es gris ",R.drawable.coche,"vehiculos",2,"kcq");
        db.insertar_foto("c","ca","directa","caballo","Ayer monté a * ",R.drawable.caballo,"animales",3,"kcq");
        db.insertar_foto("c","ca","directa","cabra","Mi abuela tiene una *",R.drawable.cabra,"animales",2,"kcq");
        db.insertar_foto("c","ca","directa","casa","La * es pequeña",R.drawable.casa,"casa",2,"kcq");
        db.insertar_foto("c","ca","directa","camello","Nunca he montado en un * ",R.drawable.camello,"animales",3,"kcq");
        db.insertar_foto("c","co","directa","cocina","Hacemos la comida en la *",R.drawable.cocina,"casa",3,"kcq");
        db.insertar_foto("c","co","directa","cocodrilo","El * tiene la boca muy grande",R.drawable.cocodrilo,"animales",4,"kcq");
        db.insertar_foto("c","co","directa","conejo","En el bosque encontramos un *",R.drawable.conejo,"animales",3,"kcq");
        db.insertar_foto("c","cu","directa","cubo","En el pozo cogemos agua con un *",R.drawable.cubo,"otros",2,"kcq");
        db.insertar_foto("c","cu","directa","cuchillo","El filete se corta con un *",R.drawable.cuchillo,"casa",3,"kcq");
        db.insertar_foto("c","cu","directa","cuna","Mi prima pequeña aún duerme en la *",R.drawable.cuna,"casa",2,"kcq");
        db.insertar_foto("c","cla","trabada","clarinete","Mi madre toca el *",R.drawable.clarinete,"musica",4,"cl");
        db.insertar_foto("c","cla","trabada","clave","Estoy aprendiendo a dibujar una * de sol",R.drawable.clave,"musica",2,"cl");
        db.insertar_foto("c","cro","trabada","croquetas","A mi padre no le gustan las *",R.drawable.croquetas,"comida",2,"cr");
        db.insertar_foto("c","clip","trabada","clip","Este * es de color rojo",R.drawable.clip,"otros",1,"mono");

        db.insertar_foto("c","ci","directa","cine","Voy al * una vez al mes",R.drawable.cine,"otros",2,"zc");
        db.insertar_foto("c","ce","directa","cebolla","Si cortas * te lloran los ojos",R.drawable.cebolla,"comida",3,"zc");
        db.insertar_foto("c","ce","directa","cerezas","Las * son pequeñas y rojas",R.drawable.cerezas,"comida",3,"zc");
        db.insertar_foto("c","ci","directa","ciruela","Ayer compré una * en la frutería",R.drawable.ciruela,"comida",3,"zc");


        db.insertar_foto("ch","cha","directa","chaleco","Mi abuela me calcetó un *",R.drawable.chaleco,"ropa",3,"ch");
        db.insertar_foto("ch","cha","directa","chaqueta","Ponte una * antes de salir de casa",R.drawable.chaqueta,"ropa",3,"ch");
        db.insertar_foto("ch","che","directa","cheque","Ese * vale mucho dinero",R.drawable.cheque,"otro",2,"ch");
        db.insertar_foto("ch","chi","directa","chicles","Los * son pegajosos",R.drawable.chicles,"comida",2,"ch");
        db.insertar_foto("ch","cho","directa","chocolate","Puedo comerme una tableta entera de *",R.drawable.chocolate,"comida",4,"ch");
        db.insertar_foto("ch","cho","directa","chorizos","No me gustan los * picantes",R.drawable.chorizos,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","chupete","El hermano pequeño de Pepe tiene un *",R.drawable.chupete,"otros",3,"ch");
        db.insertar_foto("ch","chu","directa","churrasco","Los domingos comemos *",R.drawable.churrasco,"comida",3,"ch");
        db.insertar_foto("ch","chu","directa","churros","La churrera de la esquina vende * muy ricos",R.drawable.churro,"comida",2,"ch");
        //db.insertar_foto("ch","chi","directa","chimos","Voy a comer * de varios colores",R.drawable.chimos,"comida",2,"ch");
        db.insertar_foto("ch","chu","directa","chucherías","Las * son malas para los dientes",R.drawable.chucherias,"comida",4,"ch");

        db.insertar_foto("d","da","directa","damas","Tengo que aprender a jugar a las *",R.drawable.damas,"otros",2,"d");
        db.insertar_foto("d","de","directa","desierto","En el * hay poca de agua",R.drawable.desierto,"otros",3,"d");
        db.insertar_foto("d","di","directa","dibujar","Me encanta * y pintar",R.drawable.dibujar,"otros",3,"d");
        db.insertar_foto("d","di","directa","dinero","Mis abuelos a veces me dan *",R.drawable.dinero,"otros",3,"d");
        db.insertar_foto("d","di","directa","diploma","De mayor quiero tener un *",R.drawable.diploma,"otros",3,"d");
        db.insertar_foto("d","do","directa","dominó","A mi abuela le gusta jugar al *",R.drawable.domino,"otros",3,"d");
        db.insertar_foto("d","du","directa","ducha","Si estoy sucio tengo que ir a la *",R.drawable.ducha,"casa",2,"d");
        db.insertar_foto("d","da","directa","dátiles","Los * crecen en las palmeras",R.drawable.datiles,"comida",3,"d");
        db.insertar_foto("d","de","directa","dedal","Mi abuela se pone un * cuando cose",R.drawable.dedal,"casa",2,"d");
        db.insertar_foto("d","de","directa","dedo","Me duele el * anular",R.drawable.dedo,"otros",2,"d");
        db.insertar_foto("d","di","directa","dinosaurios","En este documental aparecen muchos  * ",R.drawable.dinosaurio,"animales",4,"d");
        db.insertar_foto("d","do","directa","doce","Mi hermano tiene * años",R.drawable.doce,"otros",2,"d");
        db.insertar_foto("d","dos","directa","dos","Uno mas uno son * ",R.drawable.dos,"otros",1,"mono");

        db.insertar_foto("e","e","directa","elefante","En en zoo hay un *",R.drawable.elefante,"animales",4,"vocales");

        db.insertar_foto("f","fa","directa","familia","Mi * me quiere",R.drawable.familia,"otros",3,"f");
        db.insertar_foto("f","fa","directa","faro","Por la noche el * tiene luz",R.drawable.faro,"otros",2,"f");
        db.insertar_foto("f","fi","directa","fideos","Hoy para comer hay *",R.drawable.fideos,"comida",3,"f");
        db.insertar_foto("f","fi","directa","figuras","En matemáticas aprendimos las * geométricas",R.drawable.figuras,"otros",3,"f");
        db.insertar_foto("f","fla","trabada","flamenco","Tengo una foto en la que salgo con un * del zoo",R.drawable.flamenco,"animales",3,"f");
        db.insertar_foto("f","flan","trabada","flan","De postre había *",R.drawable.flan,"comida",1,"mono");
        db.insertar_foto("f","flo","trabada","flores","En primavera hay muchísimas *",R.drawable.flores,"otros",2,"fl");
        db.insertar_foto("f","fre","trabada","fresa","La * está muy rica",R.drawable.fresa,"comida",2,"fr");
        db.insertar_foto("f","fru","trabada","frutero","En el * hay varios tipos de fruta",R.drawable.frutero,"comida",3,"fr");
        db.insertar_foto("f","fu","directa","fular","Me compraron un * nuevo",R.drawable.fular,"ropa",2,"f");
        db.insertar_foto("f","fax","directa","fax","Enviaron un * esta mañana",R.drawable.fax,"otros",1,"mono");
        db.insertar_foto("f","flor","trabada","flor","Esta * es de color lila",R.drawable.flor,"otros",1,"mono");

        db.insertar_foto("g","ga","directa","gato","Me arañó el *",R.drawable.gato,"animales",2,"gu");
        db.insertar_foto("g","gui","directa","guitarra","Quiero aprender a tocar la *",R.drawable.guitarra,"otro",3,"gu");
        db.insertar_foto("g","ga","directa","gallina","La * pone huevos",R.drawable.gallina,"animales",3,"gu");
        db.insertar_foto("g","ga","directa","gallo","Me desperté con el canto del *",R.drawable.gallo,"animales",2,"gu");
        db.insertar_foto("g","gue","directa","guepardo","El * ruge muy fuerte",R.drawable.guepardo,"animales",3,"gu");
        db.insertar_foto("g","gi","directa","girasol","Las pipas crecen en el *",R.drawable.girasol,"otro",3,"j");
        db.insertar_foto("g","go","directa","goma","La * se utiliza para borrar",R.drawable.goma,"otro",2,"gu");
        db.insertar_foto("g","go","directa","gorila","Me gusta más el * que el mono",R.drawable.gorila,"animales",3,"gu");
        db.insertar_foto("g","go","directa","gorro","Llevé mi * favorito a la nieve",R.drawable.gorro,"ropa",2,"gu");
        db.insertar_foto("g","gu","directa","gusanitos","No puedo comer tantos *",R.drawable.gusanitos,"comida",4,"gu");
        db.insertar_foto("g","gu","directa","gusano","Vi un * junto a las flores del jardín",R.drawable.gusano,"animales",3,"gu");
        db.insertar_foto("g","gel","directa","gel","Me lavo con un * que huele a vainilla",R.drawable.gel,"otro",1,"mono");
        db.insertar_foto("g","golf","directa","golf","En frente de mi casa hay un campo de *",R.drawable.golf,"otro",1,"mono");
        db.insertar_foto("g","gol","directa","gol","Ronaldo marcó un * durante el partido",R.drawable.gol,"otro",1,"mono");
        db.insertar_foto("g","gris","trabada","gris","El cielo está muy *, lloverá en breves",R.drawable.gris,"otro",1,"mono");
        db.insertar_foto("g","glo","trabada","globo","Me regalaron un * azul",R.drawable.globo,"otro",2,"gl");
        db.insertar_foto("g","gre","trabada","grelos","Hoy para comer hay lacón con *",R.drawable.grelos,"comida",2,"gr");


        db.insertar_foto("h","ho","directa","hotel","En mis  vacaciones dormí en un *",R.drawable.hotel,"casa",2,"h");
        db.insertar_foto("h","ho","directa","horario","Miré a que hora tengo clase en el *",R.drawable.horario,"casa",3,"h");
        db.insertar_foto("h","ho","directa","horóscopo","Leo cada semana el *",R.drawable.horoscopo,"otro",4,"h");

        db.insertar_foto("i","i","directa","iglú","Los esquimales viven en un *",R.drawable.iglu,"casa",2,"vocales");
        db.insertar_foto("i","i","directa","imán","Tenemos un * en la nevera",R.drawable.iman,"otro",2,"vocales");

        db.insertar_foto("j","ja","directa","jabalí","El * destrozó todo el maíz",R.drawable.jabali,"animales",3,"j");
        db.insertar_foto("j","ja","directa","jabón","Me lavo las manos con *",R.drawable.jabon,"otro",2,"j");
        db.insertar_foto("j","ja","directa","jamón","Esta tarde merendaré un bocadillo de *",R.drawable.jamon,"comida",2,"j");
        db.insertar_foto("j","je","directa","jeringa","Me pusieron la vacuna con una *",R.drawable.jeringa,"otro",3,"j");
        db.insertar_foto("j","ji","directa","jirafas","Las * tienen el cuello muy largo",R.drawable.jirafa,"animales",3,"j");
        db.insertar_foto("j","jo","directa","joyas","A mi abuela le gusta llevar *",R.drawable.joyas,"otro",2,"j");
        db.insertar_foto("j","ju","directa","judías","Necesito comprar unas * para hacer ensaladilla",R.drawable.judias,"comida",3,"j");

        db.insertar_foto("k","ki","directa","kiwis","Necesito comprar unos * ",R.drawable.kiwi,"comida",2,"kcq");

        db.insertar_foto("l","la","directa","lápiz","Siempre escribo con * para poder borrar",R.drawable.lapiz,"otro",2,"l");
        db.insertar_foto("l","la","directa","labios","Mi abuela se está pintando los *",R.drawable.labios,"otro",2,"l");
        db.insertar_foto("l","la","directa","lata","Compramos en el super una * de sardinas",R.drawable.lata,"otro",2,"l");
        db.insertar_foto("l","le","directa","león","Me da miedo ese *",R.drawable.leon,"animales",2,"l");
        db.insertar_foto("l","li","directa","libros","En verano leí unos * muy entretenidos",R.drawable.libros,"otro",2,"l");
        db.insertar_foto("l","li","directa","limón","En verano tomo cada día agua con *",R.drawable.limon,"comida",2,"l");
        db.insertar_foto("l","lo","directa","lobo","El * comió 3 gallinas",R.drawable.lobo,"animales",2,"l");
        db.insertar_foto("l","lo","directa","loro","El * de mi vecina habla mucho",R.drawable.loro,"animales",2,"l");
        db.insertar_foto("l","lu","directa","luna","Esta noche la * está creciente",R.drawable.luna,"otro",2,"l");
        db.insertar_foto("l","lu","directa","lupa","En el colegio tenemos una *",R.drawable.lupa,"otro",2,"l");
        db.insertar_foto("a","al","inversa","almejas","Este domingo comimos * a la marinera",R.drawable.almeja,"comida",3,"l");
        db.insertar_foto("a","al","inversa","almendras","De los frutos secos lo que más me gustan son las *",R.drawable.almendras,"comida",3,"l");
        db.insertar_foto("a","al","inversa","alcachofa","Esta ensalada lleva *",R.drawable.alcachofa,"comida",4,"l");
        db.insertar_foto("a","al","inversa","albañil","Mi padre trabaja de *",R.drawable.albanil,"otro",3,"l");
        db.insertar_foto("a","al","inversa","almohada","Mi * es muy blandita",R.drawable.almohada,"casa",4,"l");
        db.insertar_foto("u","ul","inversa","baúl","En mi casa hay un * para guardar los juguetes",R.drawable.baul,"casa",2,"l"); //La vocal y el sonido de esto...

        db.insertar_foto("ll","lla","directa","llama","Nunca he visto una *",R.drawable.llama,"animales",2,"lly");
        db.insertar_foto("ll","lla","directa","llave","No puedo perder la * de casa",R.drawable.llave,"otro",2,"lly");
        db.insertar_foto("ll","lla","directa","llavero","Tengo un * con la llave de casa",R.drawable.llavero,"otro",3,"lly");
        db.insertar_foto("ll","llu","directa","lluvia","Me mojaré con la * si no llevo paraguas",R.drawable.lluvia,"otro",2,"lly");
        db.insertar_foto("ll","llo","directa","cepillo","Los dientes se lavan con un *",R.drawable.cepillo,"casa",3,"lly");
        db.insertar_foto("ll","lla","directa","cerilla","Con la * se hace fuego",R.drawable.cerilla,"casa",3,"lly");
        db.insertar_foto("ll","llo","directa","criollo","El * en el churrasco me encanta",R.drawable.criollo,"comida",2,"lly");
        db.insertar_foto("ll","lla","directa","escobilla","Nustra * del baño es azul",R.drawable.escobilla,"casa",4,"lly");
        db.insertar_foto("ll","llo","directa","grillo","En el jardín hay un * cantando",R.drawable.grillo,"animales",2,"lly");
        db.insertar_foto("ll","lla","directa","medalla","Gané una carrara y recibí una *",R.drawable.medalla,"otros",3,"lly");
        db.insertar_foto("ll","lla","directa","natillas","De postre tocan *",R.drawable.natillas,"comida",3,"lly");
        db.insertar_foto("ll","llo","directa","ovillo","Compró un * de lana para hacerse una chaqueta",R.drawable.ovillos,"ropa",3,"lly");
        db.insertar_foto("ll","lla","directa","papilla","Mi hermano pequeño come *",R.drawable.papilla,"comida",3,"lly");
        db.insertar_foto("ll","lla","directa","rodilla","Me duele mucho la *",R.drawable.rodilla,"otros",3,"lly");
        db.insertar_foto("ll","lla","directa","toalla","Hoy me sequé con una * verde",R.drawable.toallas,"casa",3,"lly");

        db.insertar_foto("m","ma","directa","mano","Mi * es pequeña",R.drawable.mano,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mapa","Me regalaron un * para aprender todos los países",R.drawable.mapa,"otro",2,"m");
        db.insertar_foto("m","ma","directa","mariposa","La * tiene las alas de colores",R.drawable.mariposa,"animales",4,"m");
        db.insertar_foto("m","ma","directa","mariquita","La * es roja y negra",R.drawable.mariquita,"animales",4,"m");
        db.insertar_foto("m","me","directa","melón","Mi madre tomó de postre un trozo de *",R.drawable.melon,"comida",2,"m");
        db.insertar_foto("m","me","directa","mesa","Tengo una * de estudio en mi habitación",R.drawable.mesa,"otro",2,"m");
        db.insertar_foto("m","mi","directa","micrófono","El cantante usó el *",R.drawable.microfono,"otro",4,"m");
        db.insertar_foto("m","mo","directa","mochila","Tengo una * de los Minions",R.drawable.mochila,"otro",3,"m");
        db.insertar_foto("m","mo","directa","mono","El * está subido al árbol",R.drawable.mono,"animal",2,"m");
        db.insertar_foto("m","mu","directa","muleta","Cuando Eva cayó tuvo que usar una * para poder caminar",R.drawable.muleta,"otro",3,"m");
        db.insertar_foto("m","mu","directa","muñeca","Le regalaron una * a mi hermana",R.drawable.muneca,"otro",3,"m");
        db.insertar_foto("e","em","inversa","embudo","Este * es de color naranja",R.drawable.embudo,"otro",3,"m");
        db.insertar_foto("e","em","inversa","empanada","Mi abuela hizo una * de pan de maiz con mejillones",R.drawable.empanada,"comida",4,"m");
        db.insertar_foto("e","em","inversa","empanadilla","Esta * está un poco quemada",R.drawable.empanadilla,"comida",5,"m");
        db.insertar_foto("i","im","inversa","impresora","Necesito una * para imprimir este dibujo",R.drawable.impresora,"otro",4,"m");
        db.insertar_foto("m","mar","directa","mar","Me gusta respirar el aire cerca del *",R.drawable.mar,"otro",1,"mono");
        db.insertar_foto("m","miel","directa","miel","Las abejas hacen *",R.drawable.miel,"comida",1,"mono");
        db.insertar_foto("m","mil","directa","mil","Este número es el * ",R.drawable.mil,"otro",1,"mono");

        db.insertar_foto("n","na","directa","naranja","Comí una * de postre",R.drawable.naranja,"comida",3,"n");
        db.insertar_foto("n","na","directa","nariz","Me pica la *",R.drawable.nariz,"otro",2,"n");
        db.insertar_foto("n","ne","directa","nevera","En la * se mantienen las cosas frías",R.drawable.nevera,"casa",3,"n");
        db.insertar_foto("n","ni","directa","nido","Ese pájaro hizo un * en el manzano de nuestro jardín",R.drawable.nido,"otro",2,"n");
        db.insertar_foto("n","no","directa","noria","La * da muchas vueltas",R.drawable.noria,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nubes","El cielo está cubierto de *",R.drawable.nubes,"otro",2,"n");
        db.insertar_foto("n","nu","directa","nuez","El nogal da *",R.drawable.nuez,"comida",2,"n");
        db.insertar_foto("a","an","inversa","ancla","Todos los barcos llevan *",R.drawable.ancla,"otro",2,"n");
        db.insertar_foto("e","en","inversa","enchufe","Ten cuidado con el *",R.drawable.enchufe,"casa",3,"n");
        db.insertar_foto("i","in","inversa","indio","Me disfrazaré de *",R.drawable.indio,"otro",2,"n");
        db.insertar_foto("o","on","inversa","once","Tengo * años",R.drawable.once,"otro",2,"n");
        db.insertar_foto("a","an","inversa","antena","Tenemos una * en el tejado",R.drawable.antena,"casa",3,"n");
        db.insertar_foto("e","en","inversa","enfermera","La * me puso las vacunas",R.drawable.enfermera,"otro",4,"n");
        db.insertar_foto("i","in","inversa","incendio","En el * de la fábrica no hubo heridos",R.drawable.incendio,"otro",3,"n");
        db.insertar_foto("i","in","inversa","interruptor","Dale al * para encender la luz",R.drawable.interruptor,"casa",4,"n");

        db.insertar_foto("ñ","ña","directa","leña","Recogimos * en el bosque",R.drawable.lena,"otro",2,"ñ");
        db.insertar_foto("ñ","ña","directa","niña","Esa * tiene 6 años",R.drawable.nina,"otro",2,"ñ");
        db.insertar_foto("ñ","ño","directa","niño","Ese * es muy bueno",R.drawable.nino,"otro",2,"ñ");
        db.insertar_foto("ñ","ña","directa","piña","Me gusta mucho la *",R.drawable.pina,"comida",2,"ñ");

        db.insertar_foto("o","o","directa","ojos","Ana tiene los * de color azul",R.drawable.ojo,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","olla","Mi padre utiliza una * para cocinar",R.drawable.olla,"otro",2,"vocales");
        db.insertar_foto("o","o","directa","orejas","Mi hermana tiene pendientes en las *",R.drawable.oreja,"otro",3,"vocales");
        db.insertar_foto("o","o","directa","oruga","Había una * en la lechuga",R.drawable.oruga,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","ovejas","En aquel campo hay siete *",R.drawable.oveja,"animales",3,"vocales");
        db.insertar_foto("o","o","directa","oso","El * es muy grande y peludo",R.drawable.oso,"animal",2,"vocales");

        db.insertar_foto("p","pla","trabada","planeta","La Tierra es un *",R.drawable.planeta,"otro",3,"pl");
        db.insertar_foto("p","pe","directa","perro","El * es marrón",R.drawable.perro,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","plátano","Le echamos * a la macedonia",R.drawable.platano,"comida",3,"pl");
        db.insertar_foto("p","pla","trabada","playa","Me gusta estar en la *",R.drawable.playa,"playa",2,"pl");
        db.insertar_foto("p","pa","directa","patata","Me comí una *",R.drawable.patata,"comida",3,"p");
        db.insertar_foto("p","pan","directa","pan","Me comí un trozo de *",R.drawable.pan,"comida",1,"mono");
        db.insertar_foto("p","pi","directa","pimiento","El * es verde",R.drawable.pimiento,"comida",3,"p");
        db.insertar_foto("p","pez","directa","pez","El * vive en el agua",R.drawable.pez,"animales",1,"mono");
        db.insertar_foto("p","po","directa","policía","El * capturó a los ladrones",R.drawable.policia,"otro",3,"p");
        db.insertar_foto("p","pu","directa","puma","El * es negro",R.drawable.puma,"animales",2,"p");
        db.insertar_foto("p","pla","trabada","plato","Me compre un *",R.drawable.plato,"comida",2,"pl");
        db.insertar_foto("p","plu","trabada","pluma","A la gallina se le cayó una *",R.drawable.pluma,"otro",2,"pl");
        db.insertar_foto("p","pra","trabada","prado","Me encanta estar tirado y rodar por el *",R.drawable.prado,"otro",2,"pr");
        db.insertar_foto("p","pre","trabada","precio","Para ahorrar dinero comprando tenemos que mirar el * ",R.drawable.precios,"otro",2,"pr");
        db.insertar_foto("p","pre","trabada","premio","A mi hermana le dieron un * por jugar muy bien al fútbol",R.drawable.premio,"otro",2,"pr");
        db.insertar_foto("p","pro","trabada","problemas","En matemáticas se hacen muchos *",R.drawable.problemas,"otro",3,"pr");
        db.insertar_foto("p","pro","trabada","profesor","El * me enseñó a multiplicar",R.drawable.profesor,"otro",3,"pr");

        db.insertar_foto("q","que","directa","queso","Me gusta mucho el *",R.drawable.queso,"comida",2,"kcq");

        db.insertar_foto("r","ra","directa","ramas","El leñador cortó las * de los árboles",R.drawable.ramas,"otro",2,"r");
        db.insertar_foto("r","ra","directa","ratón","Cuando se me cae un diente el * Pérez me trae un regalo ",R.drawable.raton,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rana","El charco está lleno de *",R.drawable.rana,"animales",2,"r");
        db.insertar_foto("r","ra","directa","rayador","Rayamos la zanahoria con un *",R.drawable.rayador,"casa",3,"r");
        db.insertar_foto("r","re","directa","regla","Para medir uso la *",R.drawable.regla,"otro",2,"r");
        db.insertar_foto("r","re","directa","reloj","Mira el * y dime qué hora es",R.drawable.reloj,"otro",2,"r");
        db.insertar_foto("r","ri","directa","río","Hay muchos peces en el *",R.drawable.rio,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rojo","Cuando tengo verguenza me pongo *",R.drawable.rojo,"otro",2,"r");
        db.insertar_foto("r","ro","directa","rosa","Mi muñeca tiene un vestido de color *",R.drawable.rosa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","árbol","Mi padre está plantando un * en el jardín",R.drawable.arbol,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","árbitro","El * le sacó al jugador una tarjeta roja",R.drawable.arbitro,"otro",3,"r");
        db.insertar_foto("a","as","inversa","aspas","Los molinos tienen * muy grandes",R.drawable.aspa,"otro",2,"r");
        db.insertar_foto("a","ar","inversa","armario","El * de mi habitación es muy amplio",R.drawable.armario,"casa",3,"r");
        db.insertar_foto("i","ir","inversa","invernadero","En el * hay pimientos plantados",R.drawable.invernadero,"otro",5,"r");

        db.insertar_foto("s","sa","directa","salero","Se rompió el * y cayó toda la sal",R.drawable.salero,"casa",3,"s");
        db.insertar_foto("s","si","directa","silla","Me senté en una *",R.drawable.silla,"otro",2,"s");
        db.insertar_foto("s","so","directa","sofá","Estoy viendo la televisión mientras estoy sentado en el *",R.drawable.sofa,"otro",2,"s");
        db.insertar_foto("s","so","directa","sopa","En las noches de frío me gusta tomar *",R.drawable.sopa,"casa",2,"s");
        db.insertar_foto("s","su","directa","suma","La * de uno y dos da como resultado tres",R.drawable.suma,"otro",2,"s");
        db.insertar_foto("s","sal","directa","sal","La comida tenía mucha *",R.drawable.sal,"comida",1,"mono");
        db.insertar_foto("s","seis","directa","seis","Mi primo tiene * años",R.drawable.seis,"otros",1,"mono");
        db.insertar_foto("s","sol","directa","sol","En verano me gusta tomar el *",R.drawable.sol,"otros",1,"mono");
        db.insertar_foto("s","sa","directa","salero","Se rompió el * y cayó toda la sal",R.drawable.salero,"casa",3,"s");
        db.insertar_foto("a","as","inversa","ascensor","Para ir a la última planta de edificio subimos en *",R.drawable.ascensor,"otro",3,"s");
        db.insertar_foto("a","as","inversa","aspiradora","Mi padre está pasando la * en el salón",R.drawable.aspiradora,"otro",4,"s");
        db.insertar_foto("e","es","inversa","espaguetti","Me encantan los * con tomamte y atún",R.drawable.espagueti,"comida",4,"s");
        db.insertar_foto("e","es","inversa","escalera","Para cambiar la bombilla mi padre usó una *",R.drawable.escalera,"otro",4,"s");
        db.insertar_foto("e","es","inversa","estrellas","Podemos mirar al cielo por la noche para ver las *",R.drawable.estrella,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escoba","Coge la * y ponte a barrer",R.drawable.escoba,"otro",3,"s");
        db.insertar_foto("i","is","inversa","isla","Iremos en barco a la *",R.drawable.isla,"playa",2,"s");
        db.insertar_foto("e","es","inversa","escarola","Haremos una ensalada con *",R.drawable.escarola,"otro",4,"s");
        db.insertar_foto("e","es","inversa","escribir","Vamos a * una carta para mis abuelos",R.drawable.escribir,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escuadra","En plástica usamos * y cartabón",R.drawable.escuadra,"otro",3,"s");
        db.insertar_foto("e","es","inversa","escultura","El escultor hizo una * preciosa",R.drawable.escultura,"otro",4,"s");
        db.insertar_foto("e","es","inversa","espárragos","Le echamos * a la ensalada",R.drawable.esparragos,"comida",4,"s");
        db.insertar_foto("e","es","inversa","espejo","Puedes verlo si te miras al *",R.drawable.espejo,"otro",3,"s");
        db.insertar_foto("e","es","inversa","espinacas","Me gusta la tortilla de *",R.drawable.espinacas,"comida",4,"s");
        db.insertar_foto("e","es","inversa","esponja","Siempre me ducho con una * rosa",R.drawable.esponja,"otro",3,"s");
        db.insertar_foto("e","es","inversa","estropajo","Para fregar las potas se usa un *",R.drawable.estropajo,"otro",4,"s");
        db.insertar_foto("o","os","inversa","ostras","El marisco que más me gusta son las *",R.drawable.ostras,"comida",2,"s");

        db.insertar_foto("t","ta","directa","tapa","Ponle la * a la olla",R.drawable.tapa,"otro",2,"t");
        db.insertar_foto("t","ta","directa","taza","Necesito una * nueva",R.drawable.taza,"otro",2,"t");
        db.insertar_foto("t","te","directa","tenis","Esta tarde jugaremos al *",R.drawable.tenis,"otro",2,"t");
        db.insertar_foto("t","te","directa","té","A media mañana mi madre siempre toma un *",R.drawable.te,"comida",1,"mono");
        db.insertar_foto("t","tres","directa","tres","Martín tiene * años",R.drawable.tres,"otro",1,"mono");
        db.insertar_foto("t","te","directa","tesoro","Los piratas buscan *",R.drawable.tesoro,"otro",3,"t");
        db.insertar_foto("t","ti","directa","tiburón","En el acuario tenían algún *",R.drawable.tiburon,"animales",3,"t");
        db.insertar_foto("t","ti","directa","timón","El capitán de un barco lleva el *",R.drawable.timon,"otro",2,"t");
        db.insertar_foto("t","to","directa","tomate","Me gusta el * en la ensalada",R.drawable.tomate,"comida",3,"t");
        db.insertar_foto("t","to","directa","toro","El * es un animal grande y fuerte",R.drawable.toro,"animales",2,"t");
        db.insertar_foto("t","tu","directa","tunas","Me gustan las canciones de las *",R.drawable.tuna,"musica",2,"t");
        db.insertar_foto("t","tren","trabada","tren","El * está en la estación ",R.drawable.tren,"vehiculos",1,"mono");
        db.insertar_foto("t","tri","trabada","triángulo","El * tiene tres lados",R.drawable.triangulo,"otro",4,"tr");
        db.insertar_foto("t","tru","trabada","trufas","Me encantan las * de postre",R.drawable.trufas,"comida",2,"tr");
        db.insertar_foto("t","tro","trabada","electrodomésticos","Tenemos que comprar varios *",R.drawable.electrodomesticos,"varios",4,"tr");

        db.insertar_foto("u","u","directa","uniforme","Tengo que llevar * al colegio",R.drawable.uniforme,"otro",4,"vocales");
        db.insertar_foto("u","u","directa","uvas","No me gustan las * negras",R.drawable.uva,"comida",2,"vocales");
        db.insertar_foto("u","u","directa","uñas","No te muerdas las *",R.drawable.una,"otro",2,"vocales");
        db.insertar_foto("u","u","directa","uno","Este número es el *",R.drawable.uno,"otro",1,"mono");

        db.insertar_foto("v","va","directa","vaca","La * da leche",R.drawable.vaca,"animales",2,"bv");
        db.insertar_foto("v","va","directa","vaso","Quiero un * de zumo",R.drawable.vaso,"otro",2,"bv");
        db.insertar_foto("v","ve","directa","vela","Esta es una * perfumada",R.drawable.vela,"otros",2,"bv");
        db.insertar_foto("v","vo","directa","volante","Mi padre cuando conduce mantiene las dos manos en el *",R.drawable.volante,"otros",3,"bv");


      //  db.insertar_foto("w","wa","directa","waterpolo","El * se juega en el agua",R.drawable.waterpolo,"otro",4,"gu");
        db.insertar_foto("x","xi","directa","xilófono","El * es un instrumento musical",R.drawable.xilofono,"otro",4,"x");
        db.insertar_foto("w","wa","directa","waterpolo","Hemos jugado al * en la piscina de casa",R.drawable.waterpolo,"otro",4,"w");


        db.insertar_foto("y","ye","directa","yegua","La * ha tenido un potro",R.drawable.yegua,"animales",2,"lly");
        db.insertar_foto("y","yo","directa","yogurt","Siempre ceno un * de postre",R.drawable.yogurt,"comida",2,"lly");

        db.insertar_foto("z","za","directa","zanahoria","Comí una * cruda",R.drawable.zanahoria,"comida",4,"zc");
        db.insertar_foto("z","za","directa","zapatos","Tengo unos * rojos",R.drawable.zapato,"comida",3,"zc");
        db.insertar_foto("z","zo","directa","zorro","El * es astuto",R.drawable.zorro,"animales",2,"zc");
        db.insertar_foto("z","zu","directa","zumo","Que rico está el * de melocotón",R.drawable.zumo,"animales",2,"zc");

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

        db.insertar_nivel("escribirconsombreado",1,"subniveunico");
        db.insertar_nivel("escribirsinsombreado",1,"subniveunico");
        db.insertar_nivel("escribirtecladopalabra",1,"subniveunico");


        db.inicializarEstrellas();

    }
}
