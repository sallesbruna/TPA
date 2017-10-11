package exercicio;

import java.util.List;
import corejava.Console;

public class Principal
{	public static void main (String[] args) 
	{	
		String nome;
		double lanceMinimo;
		String dataCadastro;
		Produto umProduto;

		ProdutoAppService produtoAppService = new ProdutoAppService();
		LanceAppService lanceAppService = new LanceAppService();

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um produto");
			System.out.println("2. Alterar um produto");
			System.out.println("3. Remover um produto");
			System.out.println("4. Listar todos os produtos");
			System.out.println("5. Dar um lance em um produto");
			System.out.println("6. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do produto: ");
					lanceMinimo = Console.readDouble(
						"Informe o valor do lance mínimo: ");
					dataCadastro = Console.readLine(
						"Informe a data de cadastramento do produto: ");
						
					umProduto = new Produto(nome, lanceMinimo, Util.strToDate(dataCadastro));
					
					long numero = produtoAppService.inclui(umProduto);
					
					System.out.println('\n' + "Produto número " + 
					    numero + " incluído com sucesso!");	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do produto que você deseja alterar: ");
										
					try
					{	umProduto = produtoAppService.recuperaUmProduto(resposta);
					}
					catch(ProdutoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umProduto.getId() + 
						"    Nome = " + umProduto.getNome() +
						"    Lance Mínimo = " + umProduto.getLanceMinimo());
					if(umProduto.getUltimoLance() > 0)
					{
						System.out.print("    Ultimo lance = " + umProduto.getUltimoLance());
					}
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. Lance Mínimo");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 2:");
					
					switch (opcaoAlteracao)
					{	case 1:
							String novoNome = Console.
										readLine("Digite o novo nome: ");
							
							umProduto.setNome(novoNome);

							try
							{	produtoAppService.altera(umProduto);

								System.out.println('\n' + 
									"Alteração de nome efetuada com sucesso!");
							}
							catch(ProdutoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						case 2:
							double novoLanceMinimo = Console.
									readDouble("Digite o novo lance mínimo: ");
							
							umProduto.setLanceMinimo(novoLanceMinimo);

							try
							{	produtoAppService.altera(umProduto);

								System.out.println('\n' + 
									"Alteração de descrição efetuada " +
									"com sucesso!");						
							}
							catch(ProdutoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;

						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do produto que você deseja remover: ");
									
					try
					{	umProduto = produtoAppService.
										recuperaUmProduto(resposta);
					}
					catch(ProdutoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umProduto.getId() + 
						"    Nome = " + umProduto.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do produto?");

					if(resp.equals("s"))
					{	try
						{	produtoAppService.exclui (umProduto.getId());
							System.out.println('\n' + 
								"Produto removido com sucesso!");
						}
						catch(ProdutoNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"Produto não removido.");
					}
					
					break;
				}

				case 4:
				{	
					List<Produto> produtos = produtoAppService.recuperaProdutos();

					for (Produto produto : produtos)
					{	
						System.out.println('\n' + 
							"Id = " + produto.getId() +
							"  Nome = " + produto.getNome() +
							"  Lance mínimo = " + produto.getLanceMinimo() +
							"  Data Cadastro = " + produto.getDataCadastroMasc());
						if(produto.getUltimoLance() > 0)
						{
							System.out.print("    Ultimo lance = " + produto.getUltimoLance());
						}
					}
					
					break;
				}
				case 5:
				{
					int resposta = Console.readInt('\n' + 
							"Digite o número do produto que você deseja dar o lance : ");
											
						try
						{	umProduto = produtoAppService.recuperaUmProduto(resposta);
						}
						catch(ProdutoNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
							break;
						}
						
						double ultimoLance = umProduto.getUltimoLance();
						if(ultimoLance < 0)
						{
							System.out.println('\n' + 
									"Número = " + umProduto.getId() + 
								"    Nome = " + umProduto.getNome() +
								"    Lance Mínimo = " + umProduto.getLanceMinimo());
						}
						else
						{
							System.out.println('\n' + 
									"Número = " + umProduto.getId() + 
								"    Nome = " + umProduto.getNome() +
								"    Ultimo lance = " + ultimoLance);
						}
						double valor = Console.readDouble('\n' + "Qual é o valor do lance?");
						
						while(valor < ultimoLance)
						{
							if(ultimoLance > 0)
								System.out.println("O valor deve ser maior que o ultimo lance.");
							else
								System.out.println("O valor deve ser maior que o lance mínimo.");
							
							valor = Console.readDouble('\n' + "Qual é o valor do lance?");
						}
						
						String dataLance = Console.readLine(
								"Informe a data que foi dado o lance: ");
						Lance umLance = new Lance(valor, Util.strToDate(dataLance), umProduto.getId());
						umProduto.setUltimoLance(valor);
						
						try
						{
							produtoAppService.altera(umProduto, umLance);
						}catch(ProdutoNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
						
						System.out.println("Lance com id foi salvo com sucesso.");
						break;
						
				}
				case 6:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
}
