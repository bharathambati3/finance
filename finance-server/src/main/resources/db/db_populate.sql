INSERT INTO mf_company
(id, name) VALUES
  (1, 'SBI'),
  (2, 'L&T'),
  (3, 'HDFC'),
  (4, 'Reliance'),
  (5, 'Aditya Birla'),
  (6, 'Dsp Black Rock'),
  (7, 'ICICI'),
  (8, 'Mirae');

INSERT INTO mf_schemes
(id, company_id, name, fund_type, is_tax_saving, amfi_code) VALUES
  (1, 1, 'SBI Blue Chip Fund', 'LARGE_CAP', FALSE, '119598'),
  (2, 1, 'SBI Magnum Multicap Fund', 'MULTI_CAP', FALSE, '119718'),
  (3, 2, 'L&T Midcap Fund', 'MID_CAP', FALSE, '119807'),
  (4, 2, 'L&T Emerging Businesses Fund', 'SMALL_CAP', FALSE, '129220'),
  (5, 3, 'HDFC Hybrid Equity Fund', 'HYBRID', FALSE, '119062'),
  (6, 3, 'HDFC TaxSaver', 'ELSS', TRUE, '119060'),
  (7, 4, 'RELIANCE TAX SAVER', 'ELSS', TRUE, '118803'),
  (9, 4, 'RELIANCE LARGE CAP FUND', 'LARGE_CAP', FALSE, '118632'),
  (10, 4, 'RELIANCE SMALL CAP FUND', 'SMALL_CAP', FALSE, '118778'),
  (11, 5, 'ABSL Top 100 Fund', 'LARGE_CAP', FALSE, '119564'),
  (12, 6, 'Tax Saver Fund', 'ELSS', TRUE, '119242'),
  (13, 6, 'Equity Opportunities Fund', 'MULTI_CAP', FALSE, '119218'),
  (14, 6, 'Equity Fund', 'MULTI_CAP', FALSE, '119076'),
  (15, 7, 'ICICI Prudential Long Term Equity Fund', 'ELSS', TRUE, '120592'),
  (16, 8, 'Mirae Asset India Equity Fund', 'MULTI_CAP', FALSE, '118825'),
  (17, 8, 'Mirae Asset Emerging Bluechip Fund', 'MID_CAP', FALSE, '118834');
