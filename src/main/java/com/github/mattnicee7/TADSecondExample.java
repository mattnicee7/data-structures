package com.github.mattnicee7;

import java.util.Random;
import java.util.Scanner;

public class TADSecondExample {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int tamanho, minimo, maximo, vaga, repete;

        while (true) {

            System.out.println("================================================");
            System.out.println("      CONFIGURACOES INICIAIS DO VETOR");
            System.out.println("================================================");

            //* TAMANHO DO VETOR
            System.out.print("\nTamanho do vetor: ");
            tamanho = scn.nextInt();
            if (tamanho <= 0) {
                System.out.println("ERRO: Vetor deve ter tamanho maior que zero!!");
                continue;
            }

            //* VALORES MINIMO E MAXIMO
            System.out.print("\nValor minimo no vetor: ");
            minimo = scn.nextInt();
            System.out.print("\nValor maximo no vetor: ");
            maximo = scn.nextInt();
            if (minimo > maximo) {
                System.out.println("ERRO: Valor maximo deve ser maior que o valor minimo");
                continue;
            }

            //*  VALOR INDICATIVO DE POSICAO VAGA
            System.out.print("\nValor indicativo de posicao vaga: ");
            vaga = scn.nextInt();
            if (vaga >= minimo && vaga <= maximo) {
                System.out.println("ERRO: Valor indicativo de posicao vaga nao pode estar no intervalo de valores valido!!");
                continue;
            }

            //*  POSSIBILIDADE OU NAO DE REPETICAO
            System.out.print("\nPode haver repeticao de valores (1) ou nao (2)? ");
            repete = scn.nextInt();
            if (repete < 1 || repete > 2) {
                System.out.println("ERRO: Valor invalido. Informe 1 ou 2.");
                continue;
            }

            break;

        }

        TadVetor vetor = new TadVetor(tamanho, minimo, maximo, vaga, repete);

        while (true) {

            System.out.println("===================================================");
            System.out.println("            Estudo do TAD Vetor");
            System.out.println("===================================================");
            System.out.println("0 - Encerrar");
            System.out.println("1 - Atribuir um valor a determinada posição.");
            System.out.println("2 - Alterar o valor de determinada posição.");
            System.out.println("3 - Remover o valor de determinada posição (atribui o valor indicativo de posição vaga).");
            System.out.println("4 - Ler o conteúdo de uma posicao.");
            System.out.println("5 - Localizar um valor e retornar sua posição (se permitir repetição tem que retornar todos).");
            System.out.println("6 - Inserir na primeira posição vaga (busca no sentido 0 → N).");
            System.out.println("7 - Remover da última posição ocupada (busca no sentido 0 → N).");
            System.out.println("8 - Imprimir o conteúdo do vetor.");

            System.out.print("\nSua opcao: ");
            int opc = scn.nextInt();

            if (opc == 0) {
                break;
            } else if (opc == 1) {
                System.out.println("===   A T R I B U I C A O   ===");
                System.out.print("Valor: ");
                int valor = scn.nextInt();
                System.out.print("Posicao: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.armazenar(valor, posicao));
            } else if (opc == 2) {
                System.out.println("===   A L T E R A C A O   ===");
                System.out.print("Valor: ");
                int valor = scn.nextInt();
                System.out.print("Posicao: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.alterar(valor, posicao));
            } else if (opc == 3) {
                System.out.println("===   R E M O C A O   ===");
                System.out.print("Posicao a remover: ");
                int posicao = scn.nextInt();
                Utils.printRet(vetor.excluir(posicao));
            } else if (opc == 4) {
                System.out.println("===   C O N T E U D O   ===");
                System.out.print("Posicao a ler: ");
                int posicao = scn.nextInt();
                System.out.println("Posicao " + posicao + "contem " + vetor.ler(posicao));
            } else if (opc == 5) {
                System.out.println("===   L O C A L I Z A N D O   U M   V A L O R   ===");
                System.out.print("Valor a localizar: ");
                int valor = scn.nextInt();
                int[] valores = vetor.localizar(valor, 0);
                if (valores[0] == 0) {
                    System.out.println("\n\nVALOR NAO LOCALIZADO.\n\n");
                } else {
                    System.out.println("\n\n===  VALORES ENCONTRADOS NOS INDICES ABAIXO   ===");
                    for (int i = 1; i < valores.length; i++) {
                        if (valores[i] == 0) {
                            break;
                        }
                        System.out.print(valores[i] + " ");
                    }
                    System.out.println("\n" + valores[0] + " valor(es) encontrado(s).");
                }
            } else if (opc == 6) {
                System.out.println("===   INSERINDO NA PRIMEIRA POSICAO VAGA   ===");
                System.out.println("Valor a inserir: ");
                int valor = scn.nextInt();
                Utils.printRet(vetor.armazenar1Vaga(valor));
            } else if (opc == 7) {
                System.out.println("===   REMOVENDO DA ULTIMA POSICAO OCUPADA   ===");
                Utils.printRet(vetor.removerUltima());
            } else if (opc == 8) {
                for (int i = 0; i < tamanho; i++) {
                    System.out.println(i + " = " + vetor.ler(i));
                }
            } else if (opc == 99) {
                vetor.limparVetor();
                Random seed = new Random();
                for (int i = 0; i < tamanho; i++) {
                    vetor.armazenar(minimo + seed.nextInt(maximo + 1 - minimo), i);
                }
            }

        }

        System.out.println("\n\n---   F I M   ---");
        System.out.println("Obrigado e ate' a proxima.\n\n");

    }

    public static class TadVetor {

        public TadVetor(int tamanho, int minimo, int maximo, int vaga, int repete) {
            this.tamanho = tamanho;
            this.minimo = minimo;
            this.maximo = maximo;
            this.vaga = vaga;
            this.repete = repete;

            this.dados = new int[this.tamanho];
            if (this.vaga != 0) {
                for (int i = 0; i < this.tamanho; i++) {
                    this.dados[i] = this.vaga;
                }
            }
        }

        private final int tamanho;
        private final int minimo;
        private final int maximo;
        private final int vaga;
        private final int repete;  // 0 = nao repete; 1 = pode ter repeticao

        private int[] dados;

        /**
         * <br> ================================================================
         * <br> ARMAZENA UM NOVO VALOR EM DETERMINADA POSICAO
         * <br> ================================================================
         * <br> Retorna os seguintes codigos:
         * <br> 0 - armazenamento bem sucedido
         * <br> 1 - valor fora da faixa valida
         * <br> 2 - posicao nao existente no vetor
         * <br> 3 - posicao ocupada (para armazenar e' necessario estar vaga)
         * <br> 4 - caso nao permita repeticao e ja' existe o valor
         *
         * @param valor
         * @param posicao
         * @return
         */
        public int armazenar(int valor, int posicao) {

            if (!this.valorValido(valor)) {  // valor valido?
                return 1;
            }

            if (!this.posicaoValida(posicao)) {  // posicao valida?
                return 2;
            }

            if (this.dados[posicao] != this.vaga) {  // posicao esta' vaga?
                return 3;
            }

            if (!this.podeRepetir()) {  // se nao pode repetir -> ver se ja' existe no vetor
                int[] existe = this.localizar(valor, 0);
                if (existe[0] > 0) {
                    return 4;
                }

            }

            this.dados[posicao] = valor;  // ok ate' aqui -> armazenar

            return 0;

        }

        /**
         * <br> ================================================================
         * <br> ALTERA O VALOR DE DETERMINADA POSICAO
         * <br> ================================================================
         * <br> Retorna os seguintes codigos:
         * <br> 0 - alteracao bem sucedida
         * <br> 1 - valor fora da faixa valida
         * <br> 2 - posicao nao existente no vetor
         * <br> 3 - posicao vaga (para alterar e' necessario estar alocada)
         * <br> 4 - caso nao permita repeticao e ja' existe o valor
         *
         * @param valor
         * @param posicao
         * @return
         */
        public int alterar(int valor, int posicao) {

            if (!this.valorValido(valor)) {  // valor valido?
                return 1;
            }

            if (!this.posicaoValida(posicao)) {  // posicao valida?
                return 2;
            }

            if (this.dados[posicao] == this.vaga) {  // posicao contem um valor valido?
                return 3;
            }

            if (!this.podeRepetir()) {  // se nao pode repetir -> ver se ja' existe no vetor
                int[] existe = this.localizar(valor, 0);
                if (existe[0] > 0) {
                    return 4;
                }

            }

            this.dados[posicao] = valor;  // ok ate' aqui -> armazenar

            return 0;

        }

        /**
         *
         * <br> ================================================================
         * <br> EXCLUI O VALOR DE DETERMINADA POSICAO
         * <br> ================================================================
         * <br> Retorna os seguintes codigos:
         * <br> 0 - exclusao bem sucedida
         * <br> 2 - posicao nao existente no vetor
         * <br> 3 - posicao ja' esta' vaga (para excluir e' necessario estar
         * alocada)
         *
         * @param posicao
         * @return
         */
        public int excluir(int posicao) {

            if (!this.posicaoValida(posicao)) {  // posicao valida?
                return 2;
            }

            if (this.dados[posicao] == this.vaga) {  // posicao contem um valor valido?
                return 3;
            }

            this.dados[posicao] = this.vaga;

            return 0;

        }

        /**
         * <br> ====================================================================
         * <br> L&Ecirc; UMA POSI&Ccedil;&Atilde;O NO VETOR
         * <br> ====================================================================
         *
         * @param posicao
         * @return O valor contido na posicao; se a posicao estiver vaga, retorna o
         * valor que indica posicao vaga.
         */
        public int ler(int posicao) {

            if (!this.posicaoValida(posicao)) {  // posicao valida?
                return 2;
            }

            return this.dados[posicao];

        }

        /**
         * <br> Localiza um determinado valor no vetor.
         *
         * @param valor Valor a ser localizado
         * @param nPrimeiros Contabilizar apenas o "n" primeiros; 0 = contabiliza
         * todos
         * @return Um vetor com:
         * <li> posicao 0 = quantidade de elementos encontrados
         * <li> demais posicoes = indices desses elementos no vetor
         */
        public int[] localizar(int valor, int nPrimeiros) {

            int[] res = new int[this.tamanho];

            if (!this.valorValido(valor)) {
                return res;
            }

            if (this.repete == 0) {
                nPrimeiros = 1;
            }

            for (int i = 0; i < this.tamanho; i++) {
                if (this.dados[i] == valor) {
                    res[0]++;
                    res[res[0]] = i;
                    if (res[0] == nPrimeiros) {
                        break;
                    }
                }
            }

            return res;
        }

        /**
         * <br> ====================================================================
         * <br> Armazenar na primeira posicao vaga no sentido 0->N
         * <br> ====================================================================
         *
         * @param valor
         * @return Vide Utils.printRet(...)
         * @see
         */
        public int armazenar1Vaga(int valor) {

            if (!this.valorValido(valor)) {  // valor valido?
                return 1;
            }

            for (int i = 0; i < this.tamanho; i++) {
                if (this.dados[i] == this.vaga) {
                    int res = this.armazenar(valor, i);
                    return res;
                }
            }

            return -1;

        }

        /**
         * <br> ====================================================================
         * <br> Remover da ultima posicao ocupada no sentido 0->N
         * <br> ====================================================================
         *
         * @return Vide Utils.printRet(...)
         */
        public int removerUltima() {

            for (int i = this.tamanho - 1; i >= 0; i--) {
                if (this.dados[i] != this.vaga) {
                    return this.excluir(i);
                }
            }

            return -1;

        }

        private boolean valorValido(int valor) {
            return valor >= this.minimo && valor <= this.maximo;
        }

        private boolean posicaoValida(int posicao) {
            return posicao >= 0 && posicao < this.tamanho;
        }

        private boolean podeRepetir() {
            return this.repete == 1;
        }

        public void limparVetor(){
            for (int i = 0; i < tamanho; i++){
                this.dados[i] = this.vaga;
            }
        }

    }

    public static class Utils {

        private static final String cab = "\n\nOperacao NAO executada.";
        private static final String rab = "\n\n";

        public static void printRet(int codigo) {

            if (codigo == -1) {
                System.out.println(cab + " Elemento inexistente." + rab);
            } else if (codigo == 0) {
                System.out.println("Operacao BEM sucedida" + rab);
            } else if (codigo == 1) {
                System.out.println(cab + " Valor fora da faixa prevista." + rab);
            } else if (codigo == 2) {
                System.out.println(cab + " Posicao fora das fronteiras do vetor." + rab);
            } else if (codigo == 3) {
                System.out.println(cab + " A posicao ja' contem o previsto pela operacao"
                        + "('ocupada' em caso de inclusao ou 'vaga' em caso de remocao)." + rab);
            } else if (codigo == 4) {
                System.out.println(cab + " Valor ja' existente (repeticao nao permitida)." + rab);
            }

        }

    }


}
