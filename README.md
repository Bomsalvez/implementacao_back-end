# Implementação Back-End - Senzalla

## Dados de Inscrição
- **Nome**: Senzalla
- **Email**: senzalla@senzalla.com.br
- **Telefone**: (11) 99999-9999

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
```env
POSTGRES_DB=senzalla_db
POSTGRES_USER=senzalla
POSTGRES_PASSWORD=senzalla123
MINIO_ACCESS_KEY=seu-access-key
MINIO_SECRET_KEY=seu-secret-key
```

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
- `GET /api/fotos-pessoa/{id}` - Busca uma foto específica
- `POST /api/fotos-pessoa` - Salva uma nova foto
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
  -H "Content-Type: application/json" \
  -d '{
    "nomeFoto": "foto-teste.jpg",
    "urlFoto": "http://minio:9000/fotos-servidores/foto-teste.jpg"
  }'
```

2. Listar todas as fotos:
```bash
curl -X GET http://localhost:8080/api/fotos-pessoa
```

## Estrutura do Projeto

```
src/main/java/dev/senzalla/implementacao_backend/
├── config/                 # Configurações do Spring
├── model/                  # Entidades e camadas de negócio
│   ├── fotopessoa/        # Módulo de fotos de pessoas
│   ├── lotacao/           # Módulo de lotações
│   └── lotacaofoto/       # Módulo de fotos de lotação
├── service/               # Serviços compartilhados
│   └── storage/          # Serviço de armazenamento MinIO
└── ImplementacaoBackEndApplication.java
```

## Observações Importantes

- O MinIO está configurado para rodar na porta 9000
- O PostgreSQL está configurado para rodar na porta 5432
- As credenciais do MinIO devem ser configuradas no arquivo `.env` e `application.properties`
- O bucket `fotos-servidores` será criado automaticamente na primeira execução

## Suporte

Para suporte ou dúvidas, entre em contato através do email: senzalla@senzalla.com.br 