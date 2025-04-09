# Implementação Back-End

## Informações Pessoais:

- **Nome**: Carlos Henrique de Freitas Ribeiro
- **CPF**: 116.939.316-05
- **RG**: 18190772
- **Data de Nascimento**: 13/07/1993
- **Gênero**: Masculino
- **PCD**: Não
- **Escolaridade**: Ensino Superior Completo
- **E-mail**: bomsalvez@gmail.com
- **Celular**: (65) 99230-0223
- **Endereço**: Rua Paineiras, 64
- **Complemento**: Ap, bloco...
- **Bairro**: Praeiro
- **Estado**: Mato Grosso-MT
- **Cidade**: Cuiabá
- **CEP**: 78070-520
- **Cargo Pretendido**: Desenvolvedor Java Pleno

## Descrição do Projeto
Este projeto é uma implementação back-end em Java utilizando Spring Boot para gerenciamento de fotos de pessoas. A solução utiliza:
- Spring Boot 3.2.3
- PostgreSQL para armazenamento de dados
- MinIO para armazenamento de arquivos
- Docker e Docker Compose para containerização

## Pré-requisitos
- Java 17 ou superior
- Docker e Docker Compose instalados
- Maven instalado

## Configuração do Ambiente

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/implementacao_back-end.git
cd implementacao_back-end
```

2. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

3. Configure o arquivo `application.properties` com as credenciais do MinIO:
```properties
minio.url=http://localhost:9000
minio.access-key=seu-access-key
minio.secret-key=seu-secret-key
minio.bucket=fotos-servidores
```

## Executando a Aplicação

1. Inicie os containers com Docker Compose:
```bash
docker-compose up -d
```

2. Compile e execute a aplicação:
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Testando a API

### Endpoints Disponíveis

#### FotoPessoa
- `GET /api/fotos-pessoa` - Lista todas as fotos
- `GET /api/fotos-pessoa/{id}` - Busca uma foto específica e retorna um link temporário
- `POST /api/fotos-pessoa` - Salva uma nova foto (aceita MultipartFile)
- `DELETE /api/fotos-pessoa/{id}` - Remove uma foto

#### Lotação
- `GET /api/lotacao` - Lista todas as lotações
- `GET /api/lotacao/{id}` - Busca uma lotação específica
- `POST /api/lotacao` - Cria uma nova lotação
- `PUT /api/lotacao/{id}` - Atualiza uma lotação
- `DELETE /api/lotacao/{id}` - Remove uma lotação

#### LotaçãoFoto
- `GET /api/lotacao-foto` - Lista todas as fotos de lotação
- `GET /api/lotacao-foto/{id}` - Busca uma foto de lotação específica
- `POST /api/lotacao-foto` - Cria uma nova foto de lotação
- `PUT /api/lotacao-foto/{id}` - Atualiza uma foto de lotação
- `DELETE /api/lotacao-foto/{id}` - Remove uma foto de lotação

### Exemplo de Uso

1. Criar uma nova foto:
```bash
curl -X POST http://localhost:8080/api/fotos-pessoa \
  -F "file=@caminho/para/sua/foto.jpg" \
  -F "id=123"
```

2. Listar todas as fotos:
```bash
curl -X GET http://localhost:8080/api/fotos-pessoa
```

3. Obter link temporário para uma foto específica:
```bash
curl -X GET http://localhost:8080/api/fotos-pessoa/123
```
## Estrutura do Projeto

## Acessando a Documentação da API

A documentação da API pode ser acessada através do Swagger na seguinte URL:

```http request
http://localhost:8080/swagger-ui.html
```