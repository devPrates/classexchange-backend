INSERT INTO campus (id, created_at, updated_at, nome, sigla, email, slug, telefone, endereco) VALUES
('11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Campus Aquidauana', 'AQ', 'aquidauana@ifms.edu.br', 'aquidauana', '67 99999-0001', 'Rua das Palmeiras, 100 - Aquidauana/MS'),
('22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Campus Dourados', 'DOU', 'dourados@ifms.edu.br', 'dourados', '67 99999-0002', 'Av. Brasil, 2000 - Dourados/MS');

INSERT INTO professor (id, created_at, updated_at, nome, email, siape, celular) VALUES
('33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ana Silva', 'ana.silva@ifms.edu.br', 'SIAPE0001', '67 98888-1001'),
('44444444-4444-4444-4444-444444444444', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Carlos Pereira', 'carlos.pereira@ifms.edu.br', 'SIAPE0002', '67 98888-1002'),
('55555555-5555-5555-5555-555555555555', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bruno Souza', 'bruno.souza@ifms.edu.br', 'SIAPE0003', '67 98888-1003');

INSERT INTO diretor_ensino (id, created_at, updated_at, inicio, fim, professor_id, campus_id) VALUES
('66666666-6666-6666-6666-666666666666', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, DATE '2025-01-01', NULL, '33333333-3333-3333-3333-333333333333', '11111111-1111-1111-1111-111111111111');

INSERT INTO curso (id, created_at, updated_at, nome, sigla, slug, campus_id) VALUES
('77777777-7777-7777-7777-777777777777', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Sistemas de Informação', 'SI', 'si-aq', '11111111-1111-1111-1111-111111111111'),
('88888888-8888-8888-8888-888888888888', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Administração', 'ADM', 'adm-aq', '11111111-1111-1111-1111-111111111111'),
('99999999-9999-9999-9999-999999999999', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Matemática', 'MAT', 'mat-dou', '22222222-2222-2222-2222-222222222222');

INSERT INTO coordenador_curso (id, created_at, updated_at, inicio, fim, professor_id, curso_id) VALUES
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, DATE '2025-01-15', NULL, '44444444-4444-4444-4444-444444444444', '77777777-7777-7777-7777-777777777777'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, DATE '2025-01-15', NULL, '55555555-5555-5555-5555-555555555555', '88888888-8888-8888-8888-888888888888');

INSERT INTO disciplina (id, created_at, updated_at, nome, slug, carga_horaria, ementa, curso_id) VALUES
('cccccccc-cccc-cccc-cccc-cccccccccccc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Programação I', 'programacao-i', 80, 'Introdução à lógica e programação em Java.', '77777777-7777-7777-7777-777777777777'),
('dddddddd-dddd-dddd-dddd-dddddddddddd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Banco de Dados', 'banco-de-dados', 80, 'Modelagem de dados, SQL e normalização.', '77777777-7777-7777-7777-777777777777'),
('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gestão de Pessoas', 'gestao-de-pessoas', 60, 'Fundamentos de gestão de equipes e liderança.', '88888888-8888-8888-8888-888888888888'),
('aaaa1111-2222-3333-4444-555566667777', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cálculo I', 'calculo-i', 90, 'Limites, derivadas e aplicações.', '99999999-9999-9999-9999-999999999999');

INSERT INTO turma (id, created_at, updated_at, nome, slug, numero, curso_id) VALUES
('ffffffff-ffff-ffff-ffff-ffffffffffff', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SI-2025-A', 'si-2025-a', 1, '77777777-7777-7777-7777-777777777777'),
('00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SI-2025-B', 'si-2025-b', 2, '77777777-7777-7777-7777-777777777777'),
('12121212-1212-1212-1212-121212121212', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ADM-2025-A', 'adm-2025-a', 1, '88888888-8888-8888-8888-888888888888'),
('32323232-3232-3232-3232-323232323232', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'MAT-2025-A', 'mat-2025-a', 1, '99999999-9999-9999-9999-999999999999');

INSERT INTO periodo (id, created_at, updated_at, nome, slug, tipo_periodo, numero, ano, inicio, fim, disciplina_id, turma_id) VALUES
('13131313-1313-1313-1313-131313131313', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Semestre 1/2025', 'sem-1-2025-si', 'SEMESTRAL', 1, 2025, DATE '2025-02-10', DATE '2025-07-10', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'ffffffff-ffff-ffff-ffff-ffffffffffff');

INSERT INTO carga_horaria (id, created_at, updated_at, nome, duracao, medida_tempo) VALUES
('14141414-1414-1414-1414-141414141414', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Aula Padrão', 60, 'MINUTOS'),
('15151515-1515-1515-1515-151515151515', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Aula Estendida', 2, 'HORAS');

INSERT INTO local (id, created_at, updated_at, nome) VALUES
('16161616-1616-1616-1616-161616161616', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Laboratório 101'),
('17171717-1717-1717-1717-171717171717', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Sala 12');

INSERT INTO classe (id, created_at, updated_at, vagas, numero_aulas, inicio, disciplina_id, turma_id, carga_horaria_id, local_id) VALUES
('18181818-1818-1818-1818-181818181818', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 40, 32, DATE '2025-02-15', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'ffffffff-ffff-ffff-ffff-ffffffffffff', '14141414-1414-1414-1414-141414141414', '16161616-1616-1616-1616-161616161616'),
('19191919-1919-1919-1919-191919191919', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 35, 30, DATE '2025-02-20', 'dddddddd-dddd-dddd-dddd-dddddddddddd', '00000000-0000-0000-0000-000000000000', '15151515-1515-1515-1515-151515151515', '17171717-1717-1717-1717-171717171717');

INSERT INTO horario (id, created_at, updated_at, dia_da_semana, hora_inicio, hora_fim, classe_id) VALUES
('1a1a1a1a-1a1a-1a1a-1a1a-1a1a1a1a1a1a', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, '08:00', '09:40', '18181818-1818-1818-1818-181818181818'),
('1b1b1b1b-1b1b-1b1b-1b1b-1b1b1b1b1b1b', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, '08:00', '09:40', '18181818-1818-1818-1818-181818181818'),
('2a2a2a2a-2a2a-2a2a-2a2a-2a2a2a2a2a2a', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, '10:00', '11:40', '19191919-1919-1919-1919-191919191919');

INSERT INTO estudante (id, created_at, updated_at, nome, email) VALUES
('1c1c1c1c-1c1c-1c1c-1c1c-1c1c1c1c1c1c', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'José Almeida', 'jose.almeida@ifms.edu.br'),
('1d1d1d1d-1d1d-1d1d-1d1d-1d1d1d1d1d1d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Maria Costa', 'maria.costa@ifms.edu.br'),
('1e1e1e1e-1e1e-1e1e-1e1e-1e1e1e1e1e1e', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Paula Nunes', 'paula.nunes@ifms.edu.br'),
('1f1f1f1f-1f1f-1f1f-1f1f-1f1f1f1f1f1f', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'João Pedro', 'joao.pedro@ifms.edu.br'),
('20202020-2020-2020-2020-202020202020', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Lucas Lima', 'lucas.lima@ifms.edu.br');

INSERT INTO estudante_classe (id, created_at, updated_at, matricula, vinculo_curso, situacao, estudante_id, classe_id) VALUES
('21212121-2121-2121-2121-212121212121', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '20250001', DATE '2024-03-01', 'EM_CURSO', '1c1c1c1c-1c1c-1c1c-1c1c-1c1c1c1c1c1c', '18181818-1818-1818-1818-181818181818'),
('22222222-3333-4444-5555-666666666666', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '20250002', DATE '2024-03-01', 'EM_CURSO', '1d1d1d1d-1d1d-1d1d-1d1d-1d1d1d1d1d1d', '18181818-1818-1818-1818-181818181818'),
('2b2b2b2b-2b2b-2b2b-2b2b-2b2b2b2b2b2b', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '20250003', DATE '2024-03-01', 'EM_CURSO', '1e1e1e1e-1e1e-1e1e-1e1e-1e1e1e1e1e1e', '19191919-1919-1919-1919-191919191919');

INSERT INTO professor_classe (id, created_at, updated_at, inicio, fim, professor_id, classe_id) VALUES
('23232323-2323-2323-2323-232323232323', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, DATE '2025-02-15', NULL, '33333333-3333-3333-3333-333333333333', '18181818-1818-1818-1818-181818181818'),
('24242424-2424-2424-2424-242424242424', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, DATE '2025-02-20', NULL, '44444444-4444-4444-4444-444444444444', '19191919-1919-1919-1919-191919191919');