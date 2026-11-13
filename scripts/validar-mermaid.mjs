// Valida la sintaxis de todos los bloques ```mermaid en archivos Markdown.
// Uso: node validar-mermaid.mjs <carpeta-o-archivo> [...]
// Requiere: npm install mermaid jsdom
import { JSDOM } from 'jsdom';
import fs from 'node:fs';
import path from 'node:path';

const dom = new JSDOM('<!doctype html><html><body></body></html>');
globalThis.window = dom.window;
globalThis.document = dom.window.document;
globalThis.DOMParser = dom.window.DOMParser;
globalThis.Element = dom.window.Element;
try { globalThis.navigator = dom.window.navigator; } catch { /* ya definido en Node 21+ */ }

const { default: mermaid } = await import('mermaid');
mermaid.initialize({ startOnLoad: false });

function archivosMarkdown(ruta) {
  if (!fs.existsSync(ruta)) return [];
  const info = fs.statSync(ruta);
  if (info.isFile()) return ruta.endsWith('.md') ? [ruta] : [];
  return fs.readdirSync(ruta).flatMap((nombre) => archivosMarkdown(path.join(ruta, nombre)));
}

const rutas = process.argv.slice(2);
if (rutas.length === 0) {
  console.error('Uso: node validar-mermaid.mjs <carpeta-o-archivo> [...]');
  process.exit(2);
}

let total = 0;
let errores = 0;
for (const archivo of rutas.flatMap(archivosMarkdown)) {
  const texto = fs.readFileSync(archivo, 'utf8');
  const bloques = [...texto.matchAll(/```mermaid\r?\n([\s\S]*?)```/g)].map((m) => m[1]);
  for (const [i, bloque] of bloques.entries()) {
    total++;
    try {
      await mermaid.parse(bloque);
      console.log(`OK     ${archivo} (diagrama ${i + 1})`);
    } catch (e) {
      errores++;
      console.log(`ERROR  ${archivo} (diagrama ${i + 1})`);
      console.log('       ' + String(e.message ?? e).split('\n').join('\n       '));
    }
  }
}

console.log(`\n${total} diagrama(s) revisado(s), ${errores} con errores.`);
process.exit(errores > 0 ? 1 : 0);
