package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentStage by remember { mutableStateOf(1) }
    var pressCount by remember { mutableStateOf(0) }
    var maxPressesRequired by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFEB3B))
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(
                                color = Color(0xFFC3ECD2),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                if (currentStage == 1) {
                                    currentStage = 2
                                    maxPressesRequired = (2..4).random()
                                    pressCount = 0
                                } else if (currentStage == 2) {
                                    pressCount++
                                    if (pressCount >= maxPressesRequired) {
                                        currentStage = 3
                                    }
                                } else if (currentStage == 3) {
                                    currentStage = 4
                                } else if (currentStage == 4) {
                                    currentStage = 1
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (currentStage == 1) {
                                    R.drawable.lemon_tree
                                } else if (currentStage == 2) {
                                    R.drawable.lemon_squeeze
                                } else if (currentStage == 3) {
                                    R.drawable.lemon_drink
                                } else {
                                    R.drawable.lemon_restart
                                }
                            ),
                            contentDescription = stringResource(
                                id = if (currentStage == 1) {
                                    R.string.lemon_tree
                                } else if (currentStage == 2) {
                                    R.string.lemon
                                } else if (currentStage == 3) {
                                    R.string.glass_of_lemonade
                                } else {
                                    R.string.empty_glass
                                }
                            ),
                            modifier = Modifier.size(200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (currentStage == 1) {
                            stringResource(R.string.tap_lemon_tree)
                        } else if (currentStage == 2) {
                            stringResource(R.string.keep_tapping_lemon)
                        } else if (currentStage == 3) {
                            stringResource(R.string.tap_lemonade)
                        } else {
                            stringResource(R.string.tap_empty_glass)
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}