package com.example.lab3

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lab3.ui.theme.Lab3Theme





data class Regiones(
    val title: String,
    val url: String,
    val recepies: String
)

val regionesList = listOf(
    Regiones("Amazonas", "https://www.fundacionaquae.org/wp-content/uploads/2019/07/rio-amazonas.jpg","Pescado machucado,\n Patarasca de pirarucú,\n Sancocho huitoto, \nCaldo de pescado, \nPatarasca de mojojoi"),
    Regiones("Áncash","https://www.peru.travel/Contenido/Destino/Imagen/pe/26/1.3/Principal/Ancash.jpg","Llunca cashqui, \nTamales de Ancash, \nKuchi kanka o cuchi canca,\n Picante de chocho, \nPatasca Ancashina,\n Caldo de cuy o Aca cashqui"),
    Regiones("Apurimac","https://d1bvpoagx8hqbg.cloudfront.net/originals/apurimac-alturas-canones-naturales-fd503277fa8c8610123683d60f483e07.jpg","La Huatía,\n Chicharron de Cerdo, \nCuy relleno, \nEstofado de gallina, \nKapchi de chuño, \nCancacho o Kankacho"),
    Regiones("Arequipa","https://media.vogue.mx/photos/5e5c5b1f25623100081c437c/master/w_3000,h_1999,c_limit/Arequipa--paisaje.jpg","Rocoto Relleno, \nChupe de camarones, \nAdobo de chancho, \nPastel de papa,\n Cuy Chactado"),
    Regiones("Ayacucho","https://denomades.s3.us-west-2.amazonaws.com/blog/wp-content/uploads/2020/09/13172228/arco-ayacucho.jpg","Teqte ayacuchano, Patachi,\n Qapchi, \nCuy chactado ayacuchano,\n Puchero   "),
    Regiones("Cajamarca","https://www.regioncajamarca.gob.pe/media/portal/KJDIG/noticia/Plaza_de_Armas_WEB_1.jpg?r=1573668242","Caldo Verde, \nQuesillo en miel,\n Chicharrón con mote, \nSopa de chochoca con cecina, \nCuy con mote"),
    Regiones("Callao","https://elcomercio.pe/resizer/MfFipsfIj-o5SN8l6-fDYGTRqKY=/980x0/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/EVFPRGZ4W5G5DPOSRSWL2PMUZE.jpg","CHORITOS A LA CHALACA, \nMUCHAME DE ATÚN"),
    Regiones("Cusco","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/33/fc/ba/cusco.jpg?w=700&h=500&s=1","Chiri Uchu, \nTrucha Frita,\n Chairo,\n Sopa de Quinua, \nCuy al horno, \nKapchi de setas"),
    Regiones("Huancavelica","https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Panor%C3%A1mica_de_Huancavelica.jpg/1200px-Panor%C3%A1mica_de_Huancavelica.jpg","Sopa de mondongo,\n Carnero al palo, \nPatachi, \nTiqi, \nPachamanca"),
    Regiones("Huánuco","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/4a/85/5e/dscf7752-snapseed-largejpg.jpg?w=500&h=400&s=1","Locro de gallina,\n Pachamanca de Chancho, \nPicante de cuy,\n Juane de gallina, \nTacacho con cecina"),
    Regiones("Ica","https://www.peru.travel/Contenido/Destino/Imagen/es/5/1.2/Principal/SouthAmericaPeru2017_1010_180704-5912_AGP_HDR.jpg","Sopa seca,\n Carapulcra,\n Chupe de pallares,\nLas tejas"),
    Regiones("Junín","https://la.network/wp-content/uploads/2019/05/huancayo-cambio-clim%C3%A1tico.jpg","Trucha frita, \nPapa a la huancaína, \nPicante de cuy,\nCuy chactado, \nHuallpa chupe o chupe de gallina"),
    Regiones("La Libertad","https://elcomercio.pe/resizer/qQOmnlKQsSeLCb83LLmeY3VlnIc=/580x330/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/BUCL36J6JJECJJSP6FOW66AN3E.jpg","Shámbar,\nCeviche de conchas negras, \nSopa teóloga,\nSeco de cabrito a la norteña"),
    Regiones("Lambayeque","https://portal.andina.pe/EDPfotografia3/Thumbnail/2019/04/16/000579168W.jpg","Arroz con pato,\n Ceviche de pato, \nCabrito a la norteña,\nTortilla de raya,\nChiringuito"),
    Regiones("Lima Metropolitana","https://www.turiweb.pe/wp-content/uploads/2021/08/lima2-050821.jpg","Anticucho,\n Salchipapa"),
    Regiones("Lima (departamento)","https://www.ciudaris.com/blog/wp-content/uploads/consejos-comprar-departamento-lima.jpg","Anticucho, \nSalchipapa"),
    Regiones("Loreto","https://www.peru.travel/Contenido/Destino/Imagen/es/9/1.2/Principal/Loreto.jpg","Frejoles guisados con pollo,\n Juene de higado, \nSalpicon de pollo"),
    Regiones("Madre de Dios","https://1.bp.blogspot.com/-JFo1NbuGXj4/WshEIEx2-TI/AAAAAAAAAws/64n5zQqpPZUba9l7pcivg9bQUk7_DxxwwCPcBGAYYCw/s640/Madre%2Bde%2BDios%2BPeru.jpg","Seco de pollo, \nSaltado de riñon, Caigua rellena, \nPollo en salsa de mani"),
    Regiones("Moquegua","https://www.peru.travel/Contenido/Destino/Imagen/es/347/1.2/Principal/1.jpg","La Chacharrada,Patasca Moqueguana o Caldo de Mondongo,\n Cuy Frito y Picante de Cuy,\n Moqueguano de Camarón,\n Sudado de Machas"),
    Regiones("Pasco","https://www.ytuqueplanes.com/imagenes/fotos/regiones/RC-BANNER-INT-Pasco.jpg","Pescado frito con frejol, \nCau cau, \nRiñon de saltado,\n Carne saltada con arveja"),
    Regiones("Piura","https://turismoi.pe/uploads/photo/version3/photo_file/69804/piura.jpg","La malarrabia, \nSeco de cabrito a la norteña, \nPescado pasadito por agua ,\n" +
            "Sudado de cachema\n"),
    Regiones("Puno","https://www.peru.travel/Contenido/Destino/Imagen/pe/37/1.3/Principal/Los%20Uros.jpg","Pesque de quinua,\n El Chairo,\n Chupe de quinua,\n Chicharrón de alpaca, \nSancochado puneño"),
    Regiones("San Martín","https://www.peru.travel/Contenido/Evento/Imagen/pe/431/1.1/Evento/San%20Mart%C3%ADn%20Portada.jpg","Avispa Juane,\nPatarashca, \nChipado de pescado,\nRumo-Api y Sara-Api"),
    Regiones("Tacna","https://portal.andina.pe/EDPmedia/fotografia/2021/08/28/55136_paseocivico_tacna.jpg","Picante a la tacneña,\nAdobo tacneño,\nChoclo con queso,\nChicharrón de chancho con maíz tostado,\nCazuela tacneña"),
    Regiones("Tumbes","https://larepublica.pe/resizer/qBquSUeySt2THv_ZWEakaZ-sPrg=/1250x735/top/smart/cloudfront-us-east-1.images.arcpublishing.com/gruporepublica/73HJH4LFFBCMDHF2ZYHBZIMETE.jpg","Ceviche de conchas negras,\nChilcano de pescado,\nAlbóndiga de pulpa de cangrejo,\nChupe de cangrejo,\nMajarisco tumbesino"),
    Regiones("Ucayali","https://www.actualidadambiental.pe/wp-content/uploads/2019/03/pucallpa-ucayali-jaime-tranca.jpg","El Juane de Gallina,\nTacacho con cecina,\nChonta,\nPatarashca,\n Picadillo de Paiche")
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val NavController = rememberNavController()
            NavHost(navController = NavController, startDestination = "regionlist" ){
                composable( "regionlist" ){
                    RegionList(regionesList, NavController)
                }
                composable(
                    route = "recepies/{regionName}")
                {   backStackEntry ->
                        val regionId = backStackEntry.arguments?.getString("regionName")
                    //el argumento no puede ser null
                    requireNotNull(regionId)
                    Recepies(regionId)
                }
            }

        }
    }
}



@Composable
fun RegionCard(region:Regiones,navController: NavController){
    Surface(shape = RoundedCornerShape(8.dp),
    elevation = 8.dp,
    modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp),
        ) {
            val modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
            val painter = rememberAsyncImagePainter(model = region.url)
            val estado = painter.state
            Image(painter = painter,
                contentDescription =null,
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
            if(estado is AsyncImagePainter.State.Loading){
                CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = region.title, style = MaterialTheme.typography.h6)
                SimpleButton(region = region.title,navController)
            }

        }
    }
}
@Composable
fun SimpleButton(region:String,navController: NavController) {
   Button(onClick = { /* En esta seccion se envia a las vistas de recetas*/
        /*
         val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra( "departamento", message)
        }
        startActivity(intent)

         */
        navController.navigate("recepies/${region}")
   }) {
       Text(text = "Recetas")
   }
}

@Composable
fun RegionList(regionList: List<Regiones>,navController: NavController){
    LazyColumn( modifier = Modifier.fillMaxWidth()){
        items(regionList){
                region ->
                RegionCard(region = region,navController)

        }
    }
}

@Composable
fun Recepies(regionName:String){

    Surface(shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp),
        ) {
        for (region in regionesList) {
            if (region.title.equals(regionName)) {
                val modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                val painter = rememberAsyncImagePainter(model = region.url)
                val estado = painter.state
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.Crop
                )
                if (estado is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Recetas de "+region.title + "\n" + region.recepies,
                        style = MaterialTheme.typography.h6
                    )
                }

            }
        }
        }

    }
}

/*@Preview
@Composable
fun RegionesPreview(){
    RegionCard(regionesList[0])
}*/