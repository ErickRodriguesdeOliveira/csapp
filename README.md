# CS2 Catalog App

Aplicativo Android desenvolvido em Kotlin para consulta de informações do jogo Counter-Strike 2, utilizando a CSGO-API (ByMykel).  
O app apresenta skins, stickers, crates, agentes e highlights, com listagens completas e telas de detalhes.

## Funcionalidades

Skins
- Lista completa de skins do CS2
- Pesquisa por nome ou arma
- Tela de detalhes com arma, raridade e imagem

Stickers
- Lista de todos os stickers
- Pesquisa por nome ou raridade
- Tela de detalhes com nome, descrição e imagem

Crates
- Listagem de caixas e coleções
- Tipo, data de lançamento e imagem
- Tela de detalhes personalizada

Highlights
- Informações completas de cada highlight
- Descrição, evento, times, mapa e imagem
- Reprodução de vídeo integrada com controles

Agents
- Lista de agentes jogáveis
- Detalhes com imagem, raridade e descrição completa (lore)

## Tecnologias Utilizadas

- Kotlin
- Android Jetpack
- Retrofit e Gson para requisições HTTP
- Coil para carregamento de imagens
- RecyclerView
- Material Design
- CSGO-API (ByMykel)

## API Utilizada

Os dados são obtidos diretamente do repositório:

https://github.com/ByMykel/CSGO-API

Arquivos consumidos:
- skins.json
- stickers.json
- crates.json
- agents.json
- highlights.json

## Estrutura do Projeto

app/src/main/java/com/example/csapp
- data: modelos e configuração do Retrofit
- adapters: adapters dos RecyclerViews
- activities: todas as telas do aplicativo
- MainActivity.kt: tela inicial

app/src/main/res/layout
- arquivos XML das telas de lista e detalhes

app/src/main/res/values
- cores, estilos, strings

## Como Executar

1. Clonar o repositório:

   git clone https://github.com/ErickRodriguesdeOliveira/csapp

2. Abrir o projeto no Android Studio

3. Sincronizar as dependências (Sync Now)

4. Executar em um emulador ou dispositivo físico

