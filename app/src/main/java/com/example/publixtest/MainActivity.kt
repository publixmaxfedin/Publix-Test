package com.example.publixtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.publixtest.ui.theme.PublixTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {

            val character = viewModel.characters.collectAsState(initial = emptyList())
            val isLoading = viewModel.isLoading.collectAsState(initial = false)
            val error = viewModel.error.collectAsState(initial = "")


            CharacterListScreen(
                character = character.value,
                isLoading = isLoading.value,
                error = error.value,
                onRefresh = {viewModel.fetchCharacters()},
                onCharacterClick = { char ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("name", char.name)
                    intent.putExtra("status", char.status)
                    intent.putExtra("species", char.species)
                    intent.putExtra("image", char.image)
                    startActivity(intent)
                }
            )
        }
    }




@Composable
fun CharacterListScreen(
    character: List<Character>,
    isLoading : Boolean,
    error : String?,
    onRefresh:()->Unit,
    onCharacterClick : (Character) -> Unit
) {
   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(10.dp)
   ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error !=null -> {
                Text(
                    text = "Error"
                )
            }
            else -> {
                LazyColumn (
                    modifier =  Modifier.weight(1f)
                ) {
                    items(character) { character->
                        CharacterCard(
                            character,
                            onClick = { onCharacterClick(character)}
                        )
                    }
                }
            }

        }
       Button(
           onClick = onRefresh,
           modifier =  Modifier.fillMaxWidth()
               .padding(top = 16.dp)
       ) {
           Text("Refresh")
       }
   }
}



@Composable
fun CharacterCard(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = "${character.name} image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                placeholder = painterResource(R.drawable.ic_placeholder_image),
                error = painterResource(R.drawable.ic_error_image)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text= "Status : ${character.status}"
                )
                Text(
                    text = "Species: ${character.species}"
                )
            }
        }
    }
}


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PublixTestTheme {

    }
}