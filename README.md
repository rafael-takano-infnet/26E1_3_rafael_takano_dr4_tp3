# DevCalc API

API REST em Java usada para demonstrar práticas de CI/CD com GitHub Actions. O TP3 adiciona uma nova operação matemática na API e expande o pipeline com runner auto-hospedado, variables/secrets, contextos de ambiente, permissões do `GITHUB_TOKEN` e environments de deploy.

## Tecnologias

- Java 17
- Spring Boot 3.2.2
- Maven 3.9+
- JUnit 5
- GitHub Actions

## Como executar localmente

```bash
mvn clean test
mvn spring-boot:run
```

Aplicação disponível em `http://localhost:8080`.

## Endpoints da API

```text
GET /add?a=10&b=5
GET /subtract?a=10&b=5
GET /multiply?a=10&b=5
GET /divide?a=10&b=5
GET /sqrt?x=16
```

Exemplos:

```bash
curl "http://localhost:8080/add?a=10&b=5"
curl "http://localhost:8080/divide?a=10&b=5"
curl "http://localhost:8080/sqrt?x=16"
```

Para `GET /sqrt`, valores negativos retornam `400 Bad Request`.

## TP3

### Etapa 1: Runner auto-hospedado

Workflow: `.github/workflows/self-hosted-runner-demo.yml`

O workflow executa via `workflow_dispatch` em `runs-on: self-hosted`, imprime diagnósticos do runner (`uname -a`, `java -version`) e instala um pacote adicional (`tree`) via `apt`.

Configuração manual necessária no GitHub:

1. Registrar um runner Linux local no repositório.
2. Garantir que o runner esteja online.
3. Executar o workflow manualmente na aba Actions.

Checkpoint de evidência:

- Capturar a tela do runner registrado/online.
- Capturar os logs mostrando `self-hosted`, os comandos de diagnóstico e a instalação do pacote.

### Etapa 2: Variables e secrets

Workflow: `.github/workflows/dr4_tp3.yml`

O job `variables-and-secrets-demo` usa:

- `vars.APP_MODE`
- `vars.SUPPORT_EMAIL`
- `secrets.PROD_TOKEN`

Configuração manual necessária no GitHub:

1. Criar as repository variables `APP_MODE` e `SUPPORT_EMAIL`.
2. Criar o secret `PROD_TOKEN`.
3. Executar manualmente o workflow `DR4 TP3 Pipeline` com `run_variable_demo=true`.

Checkpoint de evidência:

- Capturar a tela das variables e secrets configuradas.
- Capturar o log do job mostrando `vars` e a execução protegida com o secret sem exibir o valor bruto.

### Etapa 3: Contextos e escopos

Workflow obrigatório: `.github/workflows/env-context-demo.yml`

Esse workflow demonstra:

- `github.actor`
- `runner.os`
- `STAGE=test`
- variáveis em nível de workflow, job e step
- sobrescrita de valores por escopo

Checkpoint de evidência:

- Executar manualmente o workflow.
- Capturar os logs de cada step mostrando a mudança dos valores entre workflow, job e step.

### Etapa 4: Permissões e `GITHUB_TOKEN`

Workflow: `.github/workflows/dr4_tp3.yml`

O job `open-issue-when-config-missing`:

- define `permissions` com `issues: write`
- valida a presença da variable `REQUIRED_CONFIG`
- cria uma issue automaticamente se essa variável estiver ausente

Configuração manual necessária no GitHub:

1. Não criar `REQUIRED_CONFIG` antes do teste da etapa.
2. Executar manualmente o workflow com `run_issue_demo=true`.
3. Verificar a aba Issues do repositório.

Checkpoint de evidência:

- Capturar o trecho YAML com `permissions`.
- Capturar os logs do job.
- Capturar a issue criada automaticamente.

### Etapa 5: Environments `dev` e `prod`

Workflow: `.github/workflows/dr4_tp3.yml`

Comportamento implementado:

- push na branch `dev` aciona `deploy-dev` com `environment: dev`
- push na branch `main` aciona `deploy-prod` com `environment: prod`
- o deploy é simulado por logs
- o job de `prod` exige um secret de ambiente chamado `PROD_DEPLOY_TOKEN`

Configuração manual necessária no GitHub:

1. Criar o environment `dev` com liberação automática e variable `DEV_API_URL`.
2. Criar o environment `prod` com secret `PROD_DEPLOY_TOKEN`.
3. Em `prod`, ativar regra de proteção com aprovação manual.
4. Fazer push em `dev` e em `main`.

Checkpoint de evidência:

- Capturar a tela dos environments configurados.
- Capturar o workflow executando em `dev`.
- Capturar o pedido de aprovação manual em `prod`.
- Capturar o deploy de `prod` após aprovação.

### Etapa 6: Nova funcionalidade da API

Implementações realizadas:

- método `sqrt(double x)` em `CalculatorService`
- endpoint `GET /sqrt?x=...` em `CalculatorController`
- testes de serviço para sucesso e erro
- testes HTTP com `MockMvc` para validar `200` e `400`

Checkpoint de evidência:

- Capturar execução de `mvn test`.
- Capturar chamada HTTP para `/sqrt?x=16`.

## Estrutura principal

```text
.github/workflows/dr4_tp3.yml
.github/workflows/env-context-demo.yml
.github/workflows/self-hosted-runner-demo.yml
src/main/java/com/devcalc/controller/CalculatorController.java
src/main/java/com/devcalc/service/CalculatorService.java
src/test/java/com/devcalc/controller/CalculatorControllerTest.java
src/test/java/com/devcalc/service/CalculatorServiceTest.java
```

## Evidências

Salvar os prints e logs na pasta `evidencias/` com nomes claros por etapa, por exemplo:

```text
EX01_RUNNER_ONLINE.png
EX01_SELF_HOSTED_LOGS.png
EX02_VARIABLES_AND_SECRETS.png
EX03_ENV_CONTEXT_LOGS.png
EX04_GITHUB_TOKEN_ISSUE.png
EX05_DEV_ENVIRONMENT.png
EX05_PROD_APPROVAL.png
EX06_SQRT_ENDPOINT.png
EX06_MVN_TEST.png
```
