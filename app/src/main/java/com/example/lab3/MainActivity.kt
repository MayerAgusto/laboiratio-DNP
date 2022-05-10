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
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lab3.ui.theme.Lab3Theme
data class Regiones(
    val title: String,
    val url: String
)
val regionesList = listOf(
    Regiones("Amazonas", "https://www.fundacionaquae.org/wp-content/uploads/2019/07/rio-amazonas.jpg"),
    Regiones("Áncash","https://www.peru.travel/Contenido/Destino/Imagen/pe/26/1.3/Principal/Ancash.jpg"),
    Regiones("Apurimac","https://d1bvpoagx8hqbg.cloudfront.net/originals/apurimac-alturas-canones-naturales-fd503277fa8c8610123683d60f483e07.jpg"),
    Regiones("Arequipa","https://media.vogue.mx/photos/5e5c5b1f25623100081c437c/master/w_3000,h_1999,c_limit/Arequipa--paisaje.jpg"),
    Regiones("Ayacucho","https://denomades.s3.us-west-2.amazonaws.com/blog/wp-content/uploads/2020/09/13172228/arco-ayacucho.jpg"),
    Regiones("Cajamarca","https://www.regioncajamarca.gob.pe/media/portal/KJDIG/noticia/Plaza_de_Armas_WEB_1.jpg?r=1573668242"),
    Regiones("Callao","https://elcomercio.pe/resizer/MfFipsfIj-o5SN8l6-fDYGTRqKY=/980x0/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/EVFPRGZ4W5G5DPOSRSWL2PMUZE.jpg"),
    Regiones("Cusco","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/33/fc/ba/cusco.jpg?w=700&h=500&s=1"),
    Regiones("Huancavelica","https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Panor%C3%A1mica_de_Huancavelica.jpg/1200px-Panor%C3%A1mica_de_Huancavelica.jpg"),
    Regiones("Huánuco","https://dynamic-media-cdn.tripadvisor.com/media/photo-o/10/4a/85/5e/dscf7752-snapseed-largejpg.jpg?w=500&h=400&s=1"),
    Regiones("Ica","https://www.peru.travel/Contenido/Destino/Imagen/es/5/1.2/Principal/SouthAmericaPeru2017_1010_180704-5912_AGP_HDR.jpg"),
    Regiones("Junín","https://la.network/wp-content/uploads/2019/05/huancayo-cambio-clim%C3%A1tico.jpg"),
    Regiones("La Libertad","https://elcomercio.pe/resizer/qQOmnlKQsSeLCb83LLmeY3VlnIc=/580x330/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/BUCL36J6JJECJJSP6FOW66AN3E.jpg"),
    Regiones("Lambayeque","https://portal.andina.pe/EDPfotografia3/Thumbnail/2019/04/16/000579168W.jpg"),
    Regiones("Lima Metropolitana","https://www.turiweb.pe/wp-content/uploads/2021/08/lima2-050821.jpg"),
    Regiones("Lima (departamento)","https://www.ciudaris.com/blog/wp-content/uploads/consejos-comprar-departamento-lima.jpg"),
    Regiones("Loreto","https://www.peru.travel/Contenido/Destino/Imagen/es/9/1.2/Principal/Loreto.jpg"),
    Regiones("Madre de Dios","https://1.bp.blogspot.com/-JFo1NbuGXj4/WshEIEx2-TI/AAAAAAAAAws/64n5zQqpPZUba9l7pcivg9bQUk7_DxxwwCPcBGAYYCw/s640/Madre%2Bde%2BDios%2BPeru.jpg"),
    Regiones("Moquegua","https://www.peru.travel/Contenido/Destino/Imagen/es/347/1.2/Principal/1.jpg"),
    Regiones("Pasco","https://www.ytuqueplanes.com/imagenes/fotos/regiones/RC-BANNER-INT-Pasco.jpg"),
    Regiones("Piura","https://turismoi.pe/uploads/photo/version3/photo_file/69804/piura.jpg"),
    Regiones("Puno","https://www.peru.travel/Contenido/Destino/Imagen/pe/37/1.3/Principal/Los%20Uros.jpg"),
    Regiones("San Martín","https://www.peru.travel/Contenido/Evento/Imagen/pe/431/1.1/Evento/San%20Mart%C3%ADn%20Portada.jpg"),
    Regiones("Tacna","https://portal.andina.pe/EDPmedia/fotografia/2021/08/28/55136_paseocivico_tacna.jpg"),
    Regiones("Tumbes","https://larepublica.pe/resizer/qBquSUeySt2THv_ZWEakaZ-sPrg=/1250x735/top/smart/cloudfront-us-east-1.images.arcpublishing.com/gruporepublica/73HJH4LFFBCMDHF2ZYHBZIMETE.jpg"),
    Regiones("Ucayali","https://www.actualidadambiental.pe/wp-content/uploads/2019/03/pucallpa-ucayali-jaime-tranca.jpg")

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegionList(regionesList)
        }
    }
}
@Composable
fun RegionCard(region:Regiones){
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
                SimpleButton(region = region.title)
            }

        }
    }
}
@Composable
fun SimpleButton(region:String) {
   Button(onClick = { /* En esta seccion se envia a las vistas de recetas*/
        /*
         val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra( "departamento", message)
        }
        startActivity(intent)

         */
   }) {
       Text(text = "Recetas")
   }
}

@Composable
fun RegionList(regionList: List<Regiones>){
    LazyColumn( modifier = Modifier.fillMaxWidth()){
        items(regionList){
                region ->
                RegionCard(region = region)

        }
    }
}

@Preview
@Composable
fun RegionesPreview(){
    RegionCard(regionesList[0])
}