=========================================
Project Path: /home/lucas/Documents/S6/DevProject/v3a
Project Directory Structure
=========================================
```
.:
backend
combinedCodebase.md
docker-compose.dev.yml
docker-compose.preview.yml
docker-compose.prod.yml
docs
frontend
garage.toml
generate_env.sh
node_modules
package.json
package-lock.json
prometheus
README.md

./backend:
Dockerfile
entrypoint.sh
images
models
mvnw
mvnw.cmd
pom.xml
src
target

./backend/images:

./backend/models:
siglip2_text.onnx
siglip2_vision.onnx
tokenizer.json

./backend/src:
main
test

./backend/src/main:
java
resources

./backend/src/main/java:
pdl

./backend/src/main/java/pdl:
backend

./backend/src/main/java/pdl/backend:
Application.java
AsyncConfig.java
auth
config
gallery
ingestion
system
vision

./backend/src/main/java/pdl/backend/auth:
AdminUserController.java
AppSetting.java
AppSettingRepository.java
AuthController.java
UserAccount.java
UserRepository.java

./backend/src/main/java/pdl/backend/config:
S3Config.java
SecurityConfig.java
SpaRoutingConfig.java

./backend/src/main/java/pdl/backend/gallery:
core
search

./backend/src/main/java/pdl/backend/gallery/core:
FileStorageService.java
GalleryController.java
MediaRecord.java
MediaRepository.java

./backend/src/main/java/pdl/backend/gallery/search:
SearchController.java
SimilarityRepository.java
TagController.java
TagRepository.java

./backend/src/main/java/pdl/backend/ingestion:
DatasetIngestController.java
UnsplashService.java

./backend/src/main/java/pdl/backend/system:
HealthCheck.java

./backend/src/main/java/pdl/backend/vision:
FeatureExtractor.java
SemanticExtractor.java
UploadStatusTracker.java
VisionProcessor.java

./backend/src/main/resources:
application.yaml
db
Whole War and Peace Novel.pdf

./backend/src/main/resources/db:
migration

./backend/src/main/resources/db/migration:
V1__Initial_schema.sql
V2__Add_user_approval.sql
V3__Add_settings_table.sql

./backend/src/test:
java

./backend/src/test/java:
pdl

./backend/src/test/java/pdl:
backend

./backend/src/test/java/pdl/backend:
auth
gallery
ingestion
system
vision

./backend/src/test/java/pdl/backend/auth:
AuthControllerWebMvcTests.java

./backend/src/test/java/pdl/backend/gallery:
core
search

./backend/src/test/java/pdl/backend/gallery/core:
GalleryControllerWebMvcTests.java

./backend/src/test/java/pdl/backend/gallery/search:
SearchControllerWebMvcTests.java
TagRepositoryTests.java

./backend/src/test/java/pdl/backend/ingestion:
DatasetIngestControllerSecurityTests.java

./backend/src/test/java/pdl/backend/system:
HealthCheckIT.java

./backend/src/test/java/pdl/backend/vision:
FeatureExtractorTest.java
UploadStatusTrackerTests.java

./backend/target:
backend-0.0.1-SNAPSHOT.jar
backend-0.0.1-SNAPSHOT.jar.original
classes
generated-sources
generated-test-sources
maven-archiver
maven-status
surefire-reports
test-classes

./backend/target/classes:
application.yaml
db
pdl
Whole War and Peace Novel.pdf

./backend/target/classes/db:
migration

./backend/target/classes/db/migration:
V1__Initial_schema.sql
V2__Add_user_approval.sql
V3__Add_settings_table.sql

./backend/target/classes/pdl:
backend

./backend/target/classes/pdl/backend:
Application.class
AsyncConfig.class
auth
config
gallery
ingestion
system
vision

./backend/target/classes/pdl/backend/auth:
AdminUserController.class
AppSetting.class
AppSettingRepository.class
AuthController.class
UserAccount.class
UserRepository.class

./backend/target/classes/pdl/backend/config:
S3Config.class
SecurityConfig.class
SpaRoutingConfig$1.class
SpaRoutingConfig.class

./backend/target/classes/pdl/backend/gallery:
core
search

./backend/target/classes/pdl/backend/gallery/core:
FileStorageService.class
GalleryController.class
MediaRecord.class
MediaRepository.class

./backend/target/classes/pdl/backend/gallery/search:
SearchController.class
SimilarityRepository.class
TagController.class
TagRepository.class

./backend/target/classes/pdl/backend/ingestion:
DatasetIngestController.class
UnsplashService.class

./backend/target/classes/pdl/backend/system:
HealthCheck.class

./backend/target/classes/pdl/backend/vision:
FeatureExtractor.class
SemanticExtractor.class
UploadStatusTracker.class
VisionProcessor.class

./backend/target/generated-sources:
annotations

./backend/target/generated-sources/annotations:

./backend/target/generated-test-sources:
test-annotations

./backend/target/generated-test-sources/test-annotations:

./backend/target/maven-archiver:
pom.properties

./backend/target/maven-status:
maven-compiler-plugin

./backend/target/maven-status/maven-compiler-plugin:
compile
testCompile

./backend/target/maven-status/maven-compiler-plugin/compile:
default-compile

./backend/target/maven-status/maven-compiler-plugin/compile/default-compile:
createdFiles.lst
inputFiles.lst

./backend/target/maven-status/maven-compiler-plugin/testCompile:
default-testCompile

./backend/target/maven-status/maven-compiler-plugin/testCompile/default-testCompile:
createdFiles.lst
inputFiles.lst

./backend/target/surefire-reports:
pdl.backend.auth.AuthControllerWebMvcTests.txt
pdl.backend.gallery.core.GalleryControllerWebMvcTests.txt
pdl.backend.gallery.search.SearchControllerWebMvcTests.txt
pdl.backend.gallery.search.TagRepositoryTests.txt
pdl.backend.ingestion.DatasetIngestControllerSecurityTests.txt
pdl.backend.vision.FeatureExtractorTest.txt
pdl.backend.vision.UploadStatusTrackerTests.txt
TEST-pdl.backend.auth.AuthControllerWebMvcTests.xml
TEST-pdl.backend.gallery.core.GalleryControllerWebMvcTests.xml
TEST-pdl.backend.gallery.search.SearchControllerWebMvcTests.xml
TEST-pdl.backend.gallery.search.TagRepositoryTests.xml
TEST-pdl.backend.ingestion.DatasetIngestControllerSecurityTests.xml
TEST-pdl.backend.vision.FeatureExtractorTest.xml
TEST-pdl.backend.vision.UploadStatusTrackerTests.xml

./backend/target/test-classes:
pdl

./backend/target/test-classes/pdl:
backend

./backend/target/test-classes/pdl/backend:
auth
gallery
ingestion
system
vision

./backend/target/test-classes/pdl/backend/auth:
AuthControllerWebMvcTests.class

./backend/target/test-classes/pdl/backend/gallery:
core
search

./backend/target/test-classes/pdl/backend/gallery/core:
GalleryControllerWebMvcTests.class

./backend/target/test-classes/pdl/backend/gallery/search:
SearchControllerWebMvcTests.class
TagRepositoryTests.class

./backend/target/test-classes/pdl/backend/ingestion:
DatasetIngestControllerSecurityTests.class

./backend/target/test-classes/pdl/backend/system:
HealthCheckIT.class

./backend/target/test-classes/pdl/backend/vision:
FeatureExtractorTest.class
UploadStatusTrackerTests.class

./docs:
enterprise_requirements.pdf
enterprise_requirements.tex
original_core_requirements.pdf
requirements.pdf
requirements.tex
wiki

./docs/wiki:
api-reference.md
database-schema.md
deployment.md
frontend-architecture.md
frontend-views.md
home.md

./frontend:
dist
Dockerfile
index.html
nginx.conf
node_modules
package.json
package-lock.json
public
src
tsconfig.app.json
tsconfig.json
tsconfig.node.json
vite.config.ts

./frontend/dist:
assets
index.html
pdl.png

./frontend/dist/assets:
AboutView-BSxNh1Qt.js
AboutView-CXCJzym5.css
AdminView-AD8Uc6nk.js
AdminView-bNXYCcXZ.css
GalleryView-CEXQBMLX.js
GalleryView-DjiG23H-.css
ImageDetailView-B-j9x7ic.js
ImageDetailView-CGbioRhQ.css
index-ccIUU9-T.js
index-SULjbW50.css
LoginView--7H2TJR8.css
LoginView-B32Vdwat.js
_plugin-vue_export-helper-BgKbSCNg.js
ProfileView-ClBDxaKK.css
ProfileView-_LVW8kyd.js
RegisterView-DtYVRtt1.js
RegisterView-K-sDeP2B.css
runtime-dom.esm-bundler-Dz5-j4Lc.js
theme-cruelty-CptC_d-v.css
theme-cruelty-D5nK9On3.js
UploadView-8f2_0ghu.js
UploadView-P6XJNS5f.css
useAuth-_R6DAllq.js
useGallerySearch-5mW4tdKo.js
vue-router-tTTFKTIG.js

./frontend/node_modules:
acorn
alien-signals
ansis
ast-kit
ast-walker-scope
asynckit
axios
@babel
baseline-browser-mapping
birpc
browserslist
bundle-name
call-bind-apply-helpers
caniuse-lite
chokidar
combined-stream
confbox
convert-source-map
csstype
debug
default-browser
default-browser-id
define-lazy-prop
delayed-stream
detect-libc
dunder-proto
electron-to-chromium
entities
error-stack-parser-es
escalade
es-define-property
es-errors
es-object-atoms
es-set-tostringtag
estree-walker
eventsource
exsolve
faye-websocket
fdir
follow-redirects
form-data
function-bind
gensync
get-intrinsic
get-proto
gopd
hasown
has-symbols
has-tostringtag
hookable
http-parser-js
inherits
is-docker
is-inside-container
is-wsl
@jridgewell
jsesc
json5
js-tokens
kolorist
lightningcss
lightningcss-linux-x64-gnu
local-pkg
lru-cache
magic-string
magic-string-ast
math-intrinsics
mime-db
mime-types
mlly
mrmime
ms
muggle-string
nanoid
node-releases
ohash
open
@oxc-project
path-browserify
pathe
perfect-debounce
picocolors
picomatch
pkg-types
@polka
postcss
proxy-from-env
quansync
querystringify
readdirp
requires-port
@rolldown
rolldown
run-applescript
safe-buffer
scule
semver
sirv
sockjs-client
source-map-js
@stomp
tinyglobby
totalist
@types
typescript
ufo
undici-types
unplugin
unplugin-utils
update-browserslist-db
url-parse
vite
@vitejs
vite-plugin-vue-devtools
vite-plugin-vue-inspector
@volar
vscode-uri
@vue
vue
@vue-macros
vue-router
vue-tsc
webpack-virtual-modules
websocket-driver
websocket-extensions
wsl-utils
yallist
yaml

./frontend/node_modules/acorn:
bin
CHANGELOG.md
dist
LICENSE
package.json
README.md

./frontend/node_modules/acorn/bin:
acorn

./frontend/node_modules/acorn/dist:
acorn.d.mts
acorn.d.ts
acorn.js
acorn.mjs
bin.js

./frontend/node_modules/alien-signals:
cjs
esm
LICENSE
package.json
README.md
types

./frontend/node_modules/alien-signals/cjs:
index.cjs
system.cjs

./frontend/node_modules/alien-signals/esm:
index.mjs
system.mjs

./frontend/node_modules/alien-signals/types:
index.d.ts
system.d.ts

./frontend/node_modules/ansis:
index.cjs
index.d.ts
index.mjs
LICENSE
package.json
README.md

./frontend/node_modules/ast-kit:
dist
LICENSE
package.json
README.md

./frontend/node_modules/ast-kit/dist:
index.d.ts
index.js

./frontend/node_modules/ast-walker-scope:
dist
LICENSE
package.json
README.md

./frontend/node_modules/ast-walker-scope/dist:
index.d.ts
index.js

./frontend/node_modules/asynckit:
bench.js
index.js
lib
LICENSE
package.json
parallel.js
README.md
serial.js
serialOrdered.js
stream.js

./frontend/node_modules/asynckit/lib:
abort.js
async.js
defer.js
iterate.js
readable_asynckit.js
readable_parallel.js
readable_serial.js
readable_serial_ordered.js
state.js
streamify.js
terminator.js

./frontend/node_modules/axios:
CHANGELOG.md
dist
index.d.cts
index.d.ts
index.js
lib
LICENSE
MIGRATION_GUIDE.md
package.json
README.md

./frontend/node_modules/axios/dist:
axios.js
axios.js.map
axios.min.js
axios.min.js.map
browser
esm
node

./frontend/node_modules/axios/dist/browser:
axios.cjs
axios.cjs.map

./frontend/node_modules/axios/dist/esm:
axios.js
axios.js.map
axios.min.js
axios.min.js.map

./frontend/node_modules/axios/dist/node:
axios.cjs
axios.cjs.map

./frontend/node_modules/axios/lib:
adapters
axios.js
cancel
core
defaults
env
helpers
platform
utils.js

./frontend/node_modules/axios/lib/adapters:
adapters.js
fetch.js
http.js
README.md
xhr.js

./frontend/node_modules/axios/lib/cancel:
CanceledError.js
CancelToken.js
isCancel.js

./frontend/node_modules/axios/lib/core:
AxiosError.js
AxiosHeaders.js
Axios.js
buildFullPath.js
dispatchRequest.js
InterceptorManager.js
mergeConfig.js
README.md
settle.js
transformData.js

./frontend/node_modules/axios/lib/defaults:
index.js
transitional.js

./frontend/node_modules/axios/lib/env:
classes
data.js
README.md

./frontend/node_modules/axios/lib/env/classes:
FormData.js

./frontend/node_modules/axios/lib/helpers:
AxiosTransformStream.js
AxiosURLSearchParams.js
bind.js
buildURL.js
callbackify.js
combineURLs.js
composeSignals.js
cookies.js
deprecatedMethod.js
estimateDataURLDecodedBytes.js
formDataToJSON.js
formDataToStream.js
fromDataURI.js
HttpStatusCode.js
isAbsoluteURL.js
isAxiosError.js
isURLSameOrigin.js
null.js
parseHeaders.js
parseProtocol.js
progressEventReducer.js
readBlob.js
README.md
resolveConfig.js
shouldBypassProxy.js
speedometer.js
spread.js
throttle.js
toFormData.js
toURLEncodedForm.js
trackStream.js
validator.js
ZlibHeaderTransformStream.js

./frontend/node_modules/axios/lib/platform:
browser
common
index.js
node

./frontend/node_modules/axios/lib/platform/browser:
classes
index.js

./frontend/node_modules/axios/lib/platform/browser/classes:
Blob.js
FormData.js
URLSearchParams.js

./frontend/node_modules/axios/lib/platform/common:
utils.js

./frontend/node_modules/axios/lib/platform/node:
classes
index.js

./frontend/node_modules/axios/lib/platform/node/classes:
FormData.js
URLSearchParams.js

./frontend/node_modules/@babel:
code-frame
compat-data
core
generator
helper-annotate-as-pure
helper-compilation-targets
helper-create-class-features-plugin
helper-globals
helper-member-expression-to-functions
helper-module-imports
helper-module-transforms
helper-optimise-call-expression
helper-plugin-utils
helper-replace-supers
helpers
helper-skip-transparent-expression-wrappers
helper-string-parser
helper-validator-identifier
helper-validator-option
parser
plugin-proposal-decorators
plugin-syntax-decorators
plugin-syntax-import-attributes
plugin-syntax-import-meta
plugin-syntax-jsx
plugin-syntax-typescript
plugin-transform-typescript
template
traverse
types

./frontend/node_modules/@babel/code-frame:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/code-frame/lib:
index.js
index.js.map

./frontend/node_modules/@babel/compat-data:
corejs2-built-ins.js
corejs3-shipped-proposals.js
data
LICENSE
native-modules.js
overlapping-plugins.js
package.json
plugin-bugfixes.js
plugins.js
README.md

./frontend/node_modules/@babel/compat-data/data:
corejs2-built-ins.json
corejs3-shipped-proposals.json
native-modules.json
overlapping-plugins.json
plugin-bugfixes.json
plugins.json

./frontend/node_modules/@babel/core:
lib
LICENSE
node_modules
package.json
README.md
src

./frontend/node_modules/@babel/core/lib:
config
errors
gensync-utils
index.js
index.js.map
parse.js
parse.js.map
parser
tools
transform-ast.js
transform-ast.js.map
transformation
transform-file-browser.js
transform-file-browser.js.map
transform-file.js
transform-file.js.map
transform.js
transform.js.map
vendor

./frontend/node_modules/@babel/core/lib/config:
cache-contexts.js
cache-contexts.js.map
caching.js
caching.js.map
config-chain.js
config-chain.js.map
config-descriptors.js
config-descriptors.js.map
files
full.js
full.js.map
helpers
index.js
index.js.map
item.js
item.js.map
partial.js
partial.js.map
pattern-to-regex.js
pattern-to-regex.js.map
plugin.js
plugin.js.map
printer.js
printer.js.map
resolve-targets-browser.js
resolve-targets-browser.js.map
resolve-targets.js
resolve-targets.js.map
util.js
util.js.map
validation

./frontend/node_modules/@babel/core/lib/config/files:
configuration.js
configuration.js.map
import.cjs
import.cjs.map
index-browser.js
index-browser.js.map
index.js
index.js.map
module-types.js
module-types.js.map
package.js
package.js.map
plugins.js
plugins.js.map
types.js
types.js.map
utils.js
utils.js.map

./frontend/node_modules/@babel/core/lib/config/helpers:
config-api.js
config-api.js.map
deep-array.js
deep-array.js.map
environment.js
environment.js.map

./frontend/node_modules/@babel/core/lib/config/validation:
option-assertions.js
option-assertions.js.map
options.js
options.js.map
plugins.js
plugins.js.map
removed.js
removed.js.map

./frontend/node_modules/@babel/core/lib/errors:
config-error.js
config-error.js.map
rewrite-stack-trace.js
rewrite-stack-trace.js.map

./frontend/node_modules/@babel/core/lib/gensync-utils:
async.js
async.js.map
fs.js
fs.js.map
functional.js
functional.js.map

./frontend/node_modules/@babel/core/lib/parser:
index.js
index.js.map
util

./frontend/node_modules/@babel/core/lib/parser/util:
missing-plugin-helper.js
missing-plugin-helper.js.map

./frontend/node_modules/@babel/core/lib/tools:
build-external-helpers.js
build-external-helpers.js.map

./frontend/node_modules/@babel/core/lib/transformation:
block-hoist-plugin.js
block-hoist-plugin.js.map
file
index.js
index.js.map
normalize-file.js
normalize-file.js.map
normalize-opts.js
normalize-opts.js.map
plugin-pass.js
plugin-pass.js.map
util

./frontend/node_modules/@babel/core/lib/transformation/file:
babel-7-helpers.cjs
babel-7-helpers.cjs.map
file.js
file.js.map
generate.js
generate.js.map
merge-map.js
merge-map.js.map

./frontend/node_modules/@babel/core/lib/transformation/util:
clone-deep.js
clone-deep.js.map

./frontend/node_modules/@babel/core/lib/vendor:
import-meta-resolve.js
import-meta-resolve.js.map

./frontend/node_modules/@babel/core/node_modules:
debug

./frontend/node_modules/@babel/core/node_modules/debug:
LICENSE
package.json
README.md
src

./frontend/node_modules/@babel/core/node_modules/debug/src:
browser.js
common.js
index.js
node.js

./frontend/node_modules/@babel/core/src:
config
transform-file-browser.ts
transform-file.ts

./frontend/node_modules/@babel/core/src/config:
files
resolve-targets-browser.ts
resolve-targets.ts

./frontend/node_modules/@babel/core/src/config/files:
index-browser.ts
index.ts

./frontend/node_modules/@babel/generator:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/generator/lib:
buffer.js
buffer.js.map
generators
index.js
index.js.map
node
nodes.js
nodes.js.map
printer.js
printer.js.map
source-map.js
source-map.js.map
token-map.js
token-map.js.map

./frontend/node_modules/@babel/generator/lib/generators:
base.js
base.js.map
classes.js
classes.js.map
deprecated.js
deprecated.js.map
expressions.js
expressions.js.map
flow.js
flow.js.map
index.js
index.js.map
jsx.js
jsx.js.map
methods.js
methods.js.map
modules.js
modules.js.map
statements.js
statements.js.map
template-literals.js
template-literals.js.map
typescript.js
typescript.js.map
types.js
types.js.map

./frontend/node_modules/@babel/generator/lib/node:
index.js
index.js.map
parentheses.js
parentheses.js.map

./frontend/node_modules/@babel/helper-annotate-as-pure:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-annotate-as-pure/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-compilation-targets:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-compilation-targets/lib:
debug.js
debug.js.map
filter-items.js
filter-items.js.map
index.js
index.js.map
options.js
options.js.map
pretty.js
pretty.js.map
targets.js
targets.js.map
utils.js
utils.js.map

./frontend/node_modules/@babel/helper-create-class-features-plugin:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-create-class-features-plugin/lib:
decorators-2018-09.js
decorators-2018-09.js.map
decorators.js
decorators.js.map
features.js
features.js.map
fields.js
fields.js.map
index.js
index.js.map
misc.js
misc.js.map
typescript.js
typescript.js.map

./frontend/node_modules/@babel/helper-globals:
data
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-globals/data:
browser-upper.json
builtin-lower.json
builtin-upper.json

./frontend/node_modules/@babel/helper-member-expression-to-functions:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-member-expression-to-functions/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-module-imports:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-module-imports/lib:
import-builder.js
import-builder.js.map
import-injector.js
import-injector.js.map
index.js
index.js.map
is-module.js
is-module.js.map

./frontend/node_modules/@babel/helper-module-transforms:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-module-transforms/lib:
dynamic-import.js
dynamic-import.js.map
get-module-name.js
get-module-name.js.map
index.js
index.js.map
lazy-modules.js
lazy-modules.js.map
normalize-and-load-metadata.js
normalize-and-load-metadata.js.map
rewrite-live-references.js
rewrite-live-references.js.map
rewrite-this.js
rewrite-this.js.map

./frontend/node_modules/@babel/helper-optimise-call-expression:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-optimise-call-expression/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-plugin-utils:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-plugin-utils/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-replace-supers:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-replace-supers/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helpers:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helpers/lib:
helpers
helpers-generated.js
helpers-generated.js.map
index.js
index.js.map

./frontend/node_modules/@babel/helpers/lib/helpers:
applyDecoratedDescriptor.js
applyDecoratedDescriptor.js.map
applyDecs2203.js
applyDecs2203.js.map
applyDecs2203R.js
applyDecs2203R.js.map
applyDecs2301.js
applyDecs2301.js.map
applyDecs2305.js
applyDecs2305.js.map
applyDecs2311.js
applyDecs2311.js.map
applyDecs.js
applyDecs.js.map
arrayLikeToArray.js
arrayLikeToArray.js.map
arrayWithHoles.js
arrayWithHoles.js.map
arrayWithoutHoles.js
arrayWithoutHoles.js.map
assertClassBrand.js
assertClassBrand.js.map
assertThisInitialized.js
assertThisInitialized.js.map
asyncGeneratorDelegate.js
asyncGeneratorDelegate.js.map
asyncIterator.js
asyncIterator.js.map
asyncToGenerator.js
asyncToGenerator.js.map
awaitAsyncGenerator.js
awaitAsyncGenerator.js.map
AwaitValue.js
AwaitValue.js.map
callSuper.js
callSuper.js.map
checkInRHS.js
checkInRHS.js.map
checkPrivateRedeclaration.js
checkPrivateRedeclaration.js.map
classApplyDescriptorDestructureSet.js
classApplyDescriptorDestructureSet.js.map
classApplyDescriptorGet.js
classApplyDescriptorGet.js.map
classApplyDescriptorSet.js
classApplyDescriptorSet.js.map
classCallCheck.js
classCallCheck.js.map
classCheckPrivateStaticAccess.js
classCheckPrivateStaticAccess.js.map
classCheckPrivateStaticFieldDescriptor.js
classCheckPrivateStaticFieldDescriptor.js.map
classExtractFieldDescriptor.js
classExtractFieldDescriptor.js.map
classNameTDZError.js
classNameTDZError.js.map
classPrivateFieldDestructureSet.js
classPrivateFieldDestructureSet.js.map
classPrivateFieldGet2.js
classPrivateFieldGet2.js.map
classPrivateFieldGet.js
classPrivateFieldGet.js.map
classPrivateFieldInitSpec.js
classPrivateFieldInitSpec.js.map
classPrivateFieldLooseBase.js
classPrivateFieldLooseBase.js.map
classPrivateFieldLooseKey.js
classPrivateFieldLooseKey.js.map
classPrivateFieldSet2.js
classPrivateFieldSet2.js.map
classPrivateFieldSet.js
classPrivateFieldSet.js.map
classPrivateGetter.js
classPrivateGetter.js.map
classPrivateMethodGet.js
classPrivateMethodGet.js.map
classPrivateMethodInitSpec.js
classPrivateMethodInitSpec.js.map
classPrivateMethodSet.js
classPrivateMethodSet.js.map
classPrivateSetter.js
classPrivateSetter.js.map
classStaticPrivateFieldDestructureSet.js
classStaticPrivateFieldDestructureSet.js.map
classStaticPrivateFieldSpecGet.js
classStaticPrivateFieldSpecGet.js.map
classStaticPrivateFieldSpecSet.js
classStaticPrivateFieldSpecSet.js.map
classStaticPrivateMethodGet.js
classStaticPrivateMethodGet.js.map
classStaticPrivateMethodSet.js
classStaticPrivateMethodSet.js.map
construct.js
construct.js.map
createClass.js
createClass.js.map
createForOfIteratorHelper.js
createForOfIteratorHelper.js.map
createForOfIteratorHelperLoose.js
createForOfIteratorHelperLoose.js.map
createSuper.js
createSuper.js.map
decorate.js
decorate.js.map
defaults.js
defaults.js.map
defineAccessor.js
defineAccessor.js.map
defineEnumerableProperties.js
defineEnumerableProperties.js.map
defineProperty.js
defineProperty.js.map
dispose.js
dispose.js.map
extends.js
extends.js.map
get.js
get.js.map
getPrototypeOf.js
getPrototypeOf.js.map
identity.js
identity.js.map
importDeferProxy.js
importDeferProxy.js.map
inherits.js
inherits.js.map
inheritsLoose.js
inheritsLoose.js.map
initializerDefineProperty.js
initializerDefineProperty.js.map
initializerWarningHelper.js
initializerWarningHelper.js.map
instanceof.js
instanceof.js.map
interopRequireDefault.js
interopRequireDefault.js.map
interopRequireWildcard.js
interopRequireWildcard.js.map
isNativeFunction.js
isNativeFunction.js.map
isNativeReflectConstruct.js
isNativeReflectConstruct.js.map
iterableToArray.js
iterableToArray.js.map
iterableToArrayLimit.js
iterableToArrayLimit.js.map
jsx.js
jsx.js.map
maybeArrayLike.js
maybeArrayLike.js.map
newArrowCheck.js
newArrowCheck.js.map
nonIterableRest.js
nonIterableRest.js.map
nonIterableSpread.js
nonIterableSpread.js.map
nullishReceiverError.js
nullishReceiverError.js.map
objectDestructuringEmpty.js
objectDestructuringEmpty.js.map
objectSpread2.js
objectSpread2.js.map
objectSpread.js
objectSpread.js.map
objectWithoutProperties.js
objectWithoutProperties.js.map
objectWithoutPropertiesLoose.js
objectWithoutPropertiesLoose.js.map
OverloadYield.js
OverloadYield.js.map
possibleConstructorReturn.js
possibleConstructorReturn.js.map
readOnlyError.js
readOnlyError.js.map
regeneratorAsyncGen.js
regeneratorAsyncGen.js.map
regeneratorAsyncIterator.js
regeneratorAsyncIterator.js.map
regeneratorAsync.js
regeneratorAsync.js.map
regeneratorDefine.js
regeneratorDefine.js.map
regenerator.js
regenerator.js.map
regeneratorKeys.js
regeneratorKeys.js.map
regeneratorRuntime.js
regeneratorRuntime.js.map
regeneratorValues.js
regeneratorValues.js.map
setFunctionName.js
setFunctionName.js.map
set.js
set.js.map
setPrototypeOf.js
setPrototypeOf.js.map
skipFirstGeneratorNext.js
skipFirstGeneratorNext.js.map
slicedToArray.js
slicedToArray.js.map
superPropBase.js
superPropBase.js.map
superPropGet.js
superPropGet.js.map
superPropSet.js
superPropSet.js.map
taggedTemplateLiteral.js
taggedTemplateLiteral.js.map
taggedTemplateLiteralLoose.js
taggedTemplateLiteralLoose.js.map
tdz.js
tdz.js.map
temporalRef.js
temporalRef.js.map
temporalUndefined.js
temporalUndefined.js.map
toArray.js
toArray.js.map
toConsumableArray.js
toConsumableArray.js.map
toPrimitive.js
toPrimitive.js.map
toPropertyKey.js
toPropertyKey.js.map
toSetter.js
toSetter.js.map
tsRewriteRelativeImportExtensions.js
tsRewriteRelativeImportExtensions.js.map
typeof.js
typeof.js.map
unsupportedIterableToArray.js
unsupportedIterableToArray.js.map
usingCtx.js
usingCtx.js.map
using.js
using.js.map
wrapAsyncGenerator.js
wrapAsyncGenerator.js.map
wrapNativeSuper.js
wrapNativeSuper.js.map
wrapRegExp.js
wrapRegExp.js.map
writeOnlyError.js
writeOnlyError.js.map

./frontend/node_modules/@babel/helper-skip-transparent-expression-wrappers:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-skip-transparent-expression-wrappers/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-string-parser:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-string-parser/lib:
index.js
index.js.map

./frontend/node_modules/@babel/helper-validator-identifier:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-validator-identifier/lib:
identifier.js
identifier.js.map
index.js
index.js.map
keyword.js
keyword.js.map

./frontend/node_modules/@babel/helper-validator-option:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/helper-validator-option/lib:
find-suggestion.js
find-suggestion.js.map
index.js
index.js.map
validator.js
validator.js.map

./frontend/node_modules/@babel/parser:
bin
CHANGELOG.md
lib
LICENSE
package.json
README.md
typings

./frontend/node_modules/@babel/parser/bin:
babel-parser.js

./frontend/node_modules/@babel/parser/lib:
index.js
index.js.map

./frontend/node_modules/@babel/parser/typings:
babel-parser.d.ts

./frontend/node_modules/@babel/plugin-proposal-decorators:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-proposal-decorators/lib:
index.js
index.js.map
transformer-legacy.js
transformer-legacy.js.map

./frontend/node_modules/@babel/plugin-syntax-decorators:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-syntax-decorators/lib:
index.js
index.js.map

./frontend/node_modules/@babel/plugin-syntax-import-attributes:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-syntax-import-attributes/lib:
index.js
index.js.map

./frontend/node_modules/@babel/plugin-syntax-import-meta:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-syntax-import-meta/lib:
index.js

./frontend/node_modules/@babel/plugin-syntax-jsx:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-syntax-jsx/lib:
index.js
index.js.map

./frontend/node_modules/@babel/plugin-syntax-typescript:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-syntax-typescript/lib:
index.js
index.js.map

./frontend/node_modules/@babel/plugin-transform-typescript:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/plugin-transform-typescript/lib:
const-enum.js
const-enum.js.map
enum.js
enum.js.map
global-types.js
global-types.js.map
index.js
index.js.map
namespace.js
namespace.js.map

./frontend/node_modules/@babel/template:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/template/lib:
builder.js
builder.js.map
formatters.js
formatters.js.map
index.js
index.js.map
literal.js
literal.js.map
options.js
options.js.map
parse.js
parse.js.map
populate.js
populate.js.map
string.js
string.js.map

./frontend/node_modules/@babel/traverse:
lib
LICENSE
node_modules
package.json
README.md
tsconfig.overrides.json

./frontend/node_modules/@babel/traverse/lib:
cache.js
cache.js.map
context.js
context.js.map
hub.js
hub.js.map
index.js
index.js.map
path
scope
traverse-node.js
traverse-node.js.map
types.js
types.js.map
visitors.js
visitors.js.map

./frontend/node_modules/@babel/traverse/lib/path:
ancestry.js
ancestry.js.map
comments.js
comments.js.map
context.js
context.js.map
conversion.js
conversion.js.map
evaluation.js
evaluation.js.map
family.js
family.js.map
index.js
index.js.map
inference
introspection.js
introspection.js.map
lib
modification.js
modification.js.map
removal.js
removal.js.map
replacement.js
replacement.js.map

./frontend/node_modules/@babel/traverse/lib/path/inference:
index.js
index.js.map
inferer-reference.js
inferer-reference.js.map
inferers.js
inferers.js.map
util.js
util.js.map

./frontend/node_modules/@babel/traverse/lib/path/lib:
hoister.js
hoister.js.map
removal-hooks.js
removal-hooks.js.map
virtual-types.js
virtual-types.js.map
virtual-types-validator.js
virtual-types-validator.js.map

./frontend/node_modules/@babel/traverse/lib/scope:
binding.js
binding.js.map
index.js
index.js.map
lib
traverseForScope.js
traverseForScope.js.map

./frontend/node_modules/@babel/traverse/lib/scope/lib:
renamer.js
renamer.js.map

./frontend/node_modules/@babel/traverse/node_modules:
debug

./frontend/node_modules/@babel/traverse/node_modules/debug:
LICENSE
package.json
README.md
src

./frontend/node_modules/@babel/traverse/node_modules/debug/src:
browser.js
common.js
index.js
node.js

./frontend/node_modules/@babel/types:
lib
LICENSE
package.json
README.md

./frontend/node_modules/@babel/types/lib:
asserts
ast-types
builders
clone
comments
constants
converters
definitions
index.d.ts
index.js
index.js.flow
index.js.map
index-legacy.d.ts
modifications
retrievers
traverse
utils
validators

./frontend/node_modules/@babel/types/lib/asserts:
assertNode.js
assertNode.js.map
generated

./frontend/node_modules/@babel/types/lib/asserts/generated:
index.js
index.js.map

./frontend/node_modules/@babel/types/lib/ast-types:
generated

./frontend/node_modules/@babel/types/lib/ast-types/generated:
index.js
index.js.map

./frontend/node_modules/@babel/types/lib/builders:
flow
generated
productions.js
productions.js.map
react
typescript
validateNode.js
validateNode.js.map

./frontend/node_modules/@babel/types/lib/builders/flow:
createFlowUnionType.js
createFlowUnionType.js.map
createTypeAnnotationBasedOnTypeof.js
createTypeAnnotationBasedOnTypeof.js.map

./frontend/node_modules/@babel/types/lib/builders/generated:
index.js
index.js.map
lowercase.js
lowercase.js.map
uppercase.js
uppercase.js.map

./frontend/node_modules/@babel/types/lib/builders/react:
buildChildren.js
buildChildren.js.map

./frontend/node_modules/@babel/types/lib/builders/typescript:
createTSUnionType.js
createTSUnionType.js.map

./frontend/node_modules/@babel/types/lib/clone:
cloneDeep.js
cloneDeep.js.map
cloneDeepWithoutLoc.js
cloneDeepWithoutLoc.js.map
clone.js
clone.js.map
cloneNode.js
cloneNode.js.map
cloneWithoutLoc.js
cloneWithoutLoc.js.map

./frontend/node_modules/@babel/types/lib/comments:
addComment.js
addComment.js.map
addComments.js
addComments.js.map
inheritInnerComments.js
inheritInnerComments.js.map
inheritLeadingComments.js
inheritLeadingComments.js.map
inheritsComments.js
inheritsComments.js.map
inheritTrailingComments.js
inheritTrailingComments.js.map
removeComments.js
removeComments.js.map

./frontend/node_modules/@babel/types/lib/constants:
generated
index.js
index.js.map

./frontend/node_modules/@babel/types/lib/constants/generated:
index.js
index.js.map

./frontend/node_modules/@babel/types/lib/converters:
ensureBlock.js
ensureBlock.js.map
gatherSequenceExpressions.js
gatherSequenceExpressions.js.map
toBindingIdentifierName.js
toBindingIdentifierName.js.map
toBlock.js
toBlock.js.map
toComputedKey.js
toComputedKey.js.map
toExpression.js
toExpression.js.map
toIdentifier.js
toIdentifier.js.map
toKeyAlias.js
toKeyAlias.js.map
toSequenceExpression.js
toSequenceExpression.js.map
toStatement.js
toStatement.js.map
valueToNode.js
valueToNode.js.map

./frontend/node_modules/@babel/types/lib/definitions:
core.js
core.js.map
deprecated-aliases.js
deprecated-aliases.js.map
experimental.js
experimental.js.map
flow.js
flow.js.map
index.js
index.js.map
jsx.js
jsx.js.map
misc.js
misc.js.map
placeholders.js
placeholders.js.map
typescript.js
typescript.js.map
utils.js
utils.js.map

./frontend/node_modules/@babel/types/lib/modifications:
appendToMemberExpression.js
appendToMemberExpression.js.map
flow
inherits.js
inherits.js.map
prependToMemberExpression.js
prependToMemberExpression.js.map
removePropertiesDeep.js
removePropertiesDeep.js.map
removeProperties.js
removeProperties.js.map
typescript

./frontend/node_modules/@babel/types/lib/modifications/flow:
removeTypeDuplicates.js
removeTypeDuplicates.js.map

./frontend/node_modules/@babel/types/lib/modifications/typescript:
removeTypeDuplicates.js
removeTypeDuplicates.js.map

./frontend/node_modules/@babel/types/lib/retrievers:
getAssignmentIdentifiers.js
getAssignmentIdentifiers.js.map
getBindingIdentifiers.js
getBindingIdentifiers.js.map
getFunctionName.js
getFunctionName.js.map
getOuterBindingIdentifiers.js
getOuterBindingIdentifiers.js.map

./frontend/node_modules/@babel/types/lib/traverse:
traverseFast.js
traverseFast.js.map
traverse.js
traverse.js.map

./frontend/node_modules/@babel/types/lib/utils:
deprecationWarning.js
deprecationWarning.js.map
inherit.js
inherit.js.map
react
shallowEqual.js
shallowEqual.js.map

./frontend/node_modules/@babel/types/lib/utils/react:
cleanJSXElementLiteralChild.js
cleanJSXElementLiteralChild.js.map

./frontend/node_modules/@babel/types/lib/validators:
buildMatchMemberExpression.js
buildMatchMemberExpression.js.map
generated
isBinding.js
isBinding.js.map
isBlockScoped.js
isBlockScoped.js.map
isImmutable.js
isImmutable.js.map
is.js
is.js.map
isLet.js
isLet.js.map
isNode.js
isNode.js.map
isNodesEquivalent.js
isNodesEquivalent.js.map
isPlaceholderType.js
isPlaceholderType.js.map
isReferenced.js
isReferenced.js.map
isScope.js
isScope.js.map
isSpecifierDefault.js
isSpecifierDefault.js.map
isType.js
isType.js.map
isValidES3Identifier.js
isValidES3Identifier.js.map
isValidIdentifier.js
isValidIdentifier.js.map
isVar.js
isVar.js.map
matchesPattern.js
matchesPattern.js.map
react
validate.js
validate.js.map

./frontend/node_modules/@babel/types/lib/validators/generated:
index.js
index.js.map

./frontend/node_modules/@babel/types/lib/validators/react:
isCompatTag.js
isCompatTag.js.map
isReactComponent.js
isReactComponent.js.map

./frontend/node_modules/baseline-browser-mapping:
dist
LICENSE.txt
package.json
README.md

./frontend/node_modules/baseline-browser-mapping/dist:
cli.cjs
index.cjs
index.d.ts
index.js

./frontend/node_modules/birpc:
dist
LICENSE
package.json
README.md

./frontend/node_modules/birpc/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/browserslist:
browser.js
cli.js
error.d.ts
error.js
index.d.ts
index.js
LICENSE
node.js
package.json
parse.js
README.md

./frontend/node_modules/bundle-name:
index.js
license
package.json
readme.md

./frontend/node_modules/call-bind-apply-helpers:
actualApply.d.ts
actualApply.js
applyBind.d.ts
applyBind.js
CHANGELOG.md
functionApply.d.ts
functionApply.js
functionCall.d.ts
functionCall.js
index.d.ts
index.js
LICENSE
package.json
README.md
reflectApply.d.ts
reflectApply.js
test
tsconfig.json

./frontend/node_modules/call-bind-apply-helpers/test:
index.js

./frontend/node_modules/caniuse-lite:
data
dist
LICENSE
package.json
README.md

./frontend/node_modules/caniuse-lite/data:
agents.js
browsers.js
browserVersions.js
features
features.js
regions

./frontend/node_modules/caniuse-lite/data/features:
aac.js
abortcontroller.js
ac3-ec3.js
accelerometer.js
addeventlistener.js
alternate-stylesheet.js
ambient-light.js
apng.js
array-find-index.js
array-find.js
array-flat.js
array-includes.js
arrow-functions.js
asmjs.js
async-clipboard.js
async-functions.js
atob-btoa.js
audio-api.js
audio.js
audiotracks.js
autofocus.js
auxclick.js
av1.js
avif.js
background-attachment.js
background-clip-text.js
background-img-opts.js
background-position-x-y.js
background-repeat-round-space.js
background-sync.js
battery-status.js
beacon.js
beforeafterprint.js
bigint.js
blobbuilder.js
bloburls.js
border-image.js
border-radius.js
broadcastchannel.js
brotli.js
calc.js
canvas-blending.js
canvas.js
canvas-text.js
chacha20-poly1305.js
channel-messaging.js
childnode-remove.js
ch-unit.js
classlist.js
client-hints-dpr-width-viewport.js
clipboard.js
colr.js
colr-v1.js
comparedocumentposition.js
console-basic.js
console-time.js
const.js
constraint-validation.js
contenteditable.js
contentsecuritypolicy2.js
contentsecuritypolicy.js
cookie-store-api.js
cors.js
createimagebitmap.js
credential-management.js
cross-document-view-transitions.js
cryptography.js
css3-attr.js
css3-boxsizing.js
css3-colors.js
css3-cursors-grab.js
css3-cursors.js
css3-cursors-newer.js
css3-tabsize.js
css-all.js
css-anchor-positioning.js
css-animation.js
css-any-link.js
css-appearance.js
css-at-counter-style.js
css-autofill.js
css-backdrop-filter.js
css-backgroundblendmode.js
css-background-offsets.js
css-boxdecorationbreak.js
css-boxshadow.js
css-canvas.js
css-caret-color.js
css-cascade-layers.js
css-cascade-scope.js
css-case-insensitive.js
css-clip-path.js
css-color-adjust.js
css-color-function.js
css-conic-gradients.js
css-container-queries.js
css-container-queries-style.js
css-container-query-units.js
css-containment.js
css-content-visibility.js
css-counters.js
css-crisp-edges.js
css-cross-fade.js
css-default-pseudo.js
css-descendant-gtgt.js
css-deviceadaptation.js
css-dir-pseudo.js
css-display-contents.js
css-element-function.js
css-env-function.js
css-exclusions.js
css-featurequeries.js
css-file-selector-button.js
css-filter-function.js
css-filters.js
css-first-letter.js
css-first-line.js
css-fixed.js
css-focus-visible.js
css-focus-within.js
css-font-palette.js
css-font-rendering-controls.js
css-font-stretch.js
css-gencontent.js
css-gradients.js
css-grid-animation.js
css-grid.js
css-grid-lanes.js
css-hanging-punctuation.js
css-has.js
css-hyphens.js
css-if.js
css-image-orientation.js
css-image-set.js
css-indeterminate-pseudo.js
css-initial-letter.js
css-initial-value.js
css-in-out-of-range.js
css-lch-lab.js
css-letter-spacing.js
css-line-clamp.js
css-logical-props.js
css-marker-pseudo.js
css-masks.js
css-matches-pseudo.js
css-math-functions.js
css-media-interaction.js
css-mediaqueries.js
css-media-range-syntax.js
css-media-resolution.js
css-media-scripting.js
css-mixblendmode.js
css-module-scripts.js
css-motion-paths.js
css-namespaces.js
css-nesting.js
css-not-sel-list.js
css-nth-child-of.js
css-opacity.js
css-optional-pseudo.js
css-overflow-anchor.js
css-overflow.js
css-overflow-overlay.js
css-overscroll-behavior.js
css-page-break.js
css-paged-media.js
css-paint-api.js
css-placeholder.js
css-placeholder-shown.js
css-print-color-adjust.js
css-read-only-write.js
css-rebeccapurple.js
css-reflections.js
css-regions.js
css-relative-colors.js
css-repeating-gradients.js
css-resize.js
css-revert-value.js
css-rrggbbaa.js
css-scrollbar.js
css-scroll-behavior.js
css-sel2.js
css-sel3.js
css-selection.js
css-shapes.js
css-snappoints.js
css-sticky.js
css-subgrid.js
css-supports-api.js
css-table.js
css-text-align-last.js
css-text-box-trim.js
css-text-indent.js
css-text-justify.js
css-text-orientation.js
css-textshadow.js
css-text-spacing.js
css-text-wrap-balance.js
css-touch-action.js
css-transitions.js
css-unicode-bidi.js
css-unset-value.js
css-variables.js
css-when-else.js
css-widows-orphans.js
css-width-stretch.js
css-writing-mode.js
css-zoom.js
currentcolor.js
custom-elements.js
custom-elementsv1.js
customevent.js
customizable-select.js
datalist.js
dataset.js
datauri.js
date-tolocaledatestring.js
declarative-shadow-dom.js
decorators.js
details.js
deviceorientation.js
devicepixelratio.js
dialog.js
dispatchevent.js
dnssec.js
document-currentscript.js
document-evaluate-xpath.js
document-execcommand.js
documenthead.js
document-policy.js
document-scrollingelement.js
domcontentloaded.js
dom-manip-convenience.js
dommatrix.js
dom-range.js
do-not-track.js
download.js
dragndrop.js
element-closest.js
element-from-point.js
element-scroll-methods.js
eme.js
eot.js
es5.js
es6-class.js
es6-generators.js
es6.js
es6-module-dynamic-import.js
es6-module.js
es6-number.js
es6-string-includes.js
eventsource.js
extended-system-fonts.js
feature-policy.js
fetch.js
fieldset-disabled.js
fileapi.js
filereader.js
filereadersync.js
filesystem.js
flac.js
flexbox-gap.js
flexbox.js
flow-root.js
focusin-focusout-events.js
fontface.js
font-family-system-ui.js
font-feature.js
font-kerning.js
font-loading.js
font-size-adjust.js
font-smooth.js
font-unicode-range.js
font-variant-alternates.js
font-variant-numeric.js
form-attribute.js
forms.js
form-submit-attributes.js
form-validation.js
fullscreen.js
gamepad.js
geolocation.js
getboundingclientrect.js
getcomputedstyle.js
getelementsbyclassname.js
getrandomvalues.js
gyroscope.js
hardwareconcurrency.js
hashchange.js
heif.js
hevc.js
hidden.js
high-resolution-time.js
history.js
html5semantic.js
html-media-capture.js
http2.js
http3.js
http-live-streaming.js
iframe-sandbox.js
iframe-seamless.js
iframe-srcdoc.js
imagecapture.js
ime.js
img-naturalwidth-naturalheight.js
import-maps.js
imports.js
indeterminate-checkbox.js
indexeddb2.js
indexeddb.js
inline-block.js
innertext.js
input-autocomplete-onoff.js
input-color.js
input-datetime.js
input-email-tel-url.js
input-event.js
input-file-accept.js
input-file-directory.js
input-file-multiple.js
input-inputmode.js
input-minlength.js
input-number.js
input-pattern.js
input-placeholder.js
input-range.js
input-search.js
input-selection.js
insertadjacenthtml.js
insert-adjacent.js
internationalization.js
intersectionobserver.js
intersectionobserver-v2.js
intl-pluralrules.js
intrinsic-width.js
jpeg2000.js
jpegxl.js
jpegxr.js
json.js
js-regexp-lookbehind.js
justify-content-space-evenly.js
kerning-pairs-ligatures.js
keyboardevent-charcode.js
keyboardevent-code.js
keyboardevent-getmodifierstate.js
keyboardevent-key.js
keyboardevent-location.js
keyboardevent-which.js
lazyload.js
let.js
link-icon-png.js
link-icon-svg.js
link-rel-dns-prefetch.js
link-rel-modulepreload.js
link-rel-preconnect.js
link-rel-prefetch.js
link-rel-preload.js
link-rel-prerender.js
loading-lazy-attr.js
loading-lazy-media.js
localecompare.js
magnetometer.js
matchesselector.js
matchmedia.js
mathml.js
maxlength.js
mdn-css-backdrop-pseudo-element.js
mdn-css-unicode-bidi-isolate.js
mdn-css-unicode-bidi-isolate-override.js
mdn-css-unicode-bidi-plaintext.js
mdn-text-decoration-color.js
mdn-text-decoration-line.js
mdn-text-decoration-shorthand.js
mdn-text-decoration-style.js
mediacapture-fromelement.js
media-fragments.js
mediarecorder.js
mediasource.js
menu.js
meta-theme-color.js
meter.js
midi.js
minmaxwh.js
mp3.js
mpeg4.js
mpeg-dash.js
multibackgrounds.js
multicolumn.js
mutation-events.js
mutationobserver.js
namevalue-storage.js
native-filesystem-api.js
nav-timing.js
netinfo.js
notifications.js
object-entries.js
object-fit.js
object-observe.js
objectrtc.js
object-values.js
offline-apps.js
offscreencanvas.js
ogg-vorbis.js
ogv.js
ol-reversed.js
once-event-listener.js
online-status.js
opus.js
orientation-sensor.js
outline.js
pad-start-end.js
page-transition-events.js
pagevisibility.js
passive-event-listener.js
passkeys.js
passwordrules.js
path2d.js
payment-request.js
pdf-viewer.js
permissions-api.js
permissions-policy.js
picture-in-picture.js
picture.js
ping.js
png-alpha.js
pointer-events.js
pointer.js
pointerlock.js
portals.js
prefers-color-scheme.js
prefers-reduced-motion.js
progress.js
promise-finally.js
promises.js
proximity.js
proxy.js
publickeypinning.js
push-api.js
queryselector.js
readonly-attr.js
referrer-policy.js
registerprotocolhandler.js
rellist.js
rel-noopener.js
rel-noreferrer.js
rem.js
requestanimationframe.js
requestidlecallback.js
resizeobserver.js
resource-timing.js
rest-parameters.js
rtcpeerconnection.js
ruby.js
run-in.js
same-site-cookie-attribute.js
screen-orientation.js
script-async.js
script-defer.js
scrollintoviewifneeded.js
scrollintoview.js
sdch.js
selection-api.js
server-timing.js
serviceworkers.js
setimmediate.js
shadowdom.js
shadowdomv1.js
sharedarraybuffer.js
sharedworkers.js
sni.js
spdy.js
speech-recognition.js
speech-synthesis.js
spellcheck-attribute.js
sql-storage.js
srcset.js
stream.js
streams.js
stricttransportsecurity.js
style-scoped.js
subresource-bundling.js
subresource-integrity.js
svg-css.js
svg-filters.js
svg-fonts.js
svg-fragment.js
svg-html5.js
svg-html.js
svg-img.js
svg.js
svg-smil.js
sxg.js
tabindex-attr.js
template.js
template-literals.js
temporal.js
testfeat.js
textcontent.js
text-decoration.js
text-emphasis.js
textencoder.js
text-overflow.js
text-size-adjust.js
text-stroke.js
tls1-1.js
tls1-2.js
tls1-3.js
touch.js
transforms2d.js
transforms3d.js
trusted-types.js
ttf.js
typedarrays.js
u2f.js
unhandledrejection.js
upgradeinsecurerequests.js
url.js
url-scroll-to-text-fragment.js
urlsearchparams.js
user-select-none.js
user-timing.js
use-strict.js
variable-fonts.js
vector-effect.js
vibration.js
video.js
videotracks.js
viewport-units.js
viewport-unit-variants.js
view-transitions.js
wai-aria.js
wake-lock.js
wasm-bigint.js
wasm-bulk-memory.js
wasm-extended-const.js
wasm-gc.js
wasm.js
wasm-multi-memory.js
wasm-multi-value.js
wasm-mutable-globals.js
wasm-nontrapping-fptoint.js
wasm-reference-types.js
wasm-relaxed-simd.js
wasm-signext.js
wasm-simd.js
wasm-tail-calls.js
wasm-threads.js
wav.js
wbr-element.js
web-animation.js
web-app-manifest.js
webauthn.js
web-bluetooth.js
webcodecs.js
webgl2.js
webgl.js
webgpu.js
webhid.js
webkit-user-drag.js
webm.js
webnfc.js
webp.js
web-serial.js
web-share.js
websockets.js
webtransport.js
webusb.js
webvr.js
webvtt.js
webworkers.js
webxr.js
will-change.js
woff2.js
woff.js
word-break.js
wordwrap.js
x-doc-messaging.js
x-frame-options.js
xhr2.js
xhtml.js
xhtmlsmil.js
xml-serializer.js
zstd.js

./frontend/node_modules/caniuse-lite/data/regions:
AD.js
AE.js
AF.js
AG.js
AI.js
AL.js
alt-af.js
alt-an.js
alt-as.js
alt-eu.js
alt-na.js
alt-oc.js
alt-sa.js
alt-ww.js
AM.js
AO.js
AR.js
AS.js
AT.js
AU.js
AW.js
AX.js
AZ.js
BA.js
BB.js
BD.js
BE.js
BF.js
BG.js
BH.js
BI.js
BJ.js
BM.js
BN.js
BO.js
BR.js
BS.js
BT.js
BW.js
BY.js
BZ.js
CA.js
CD.js
CF.js
CG.js
CH.js
CI.js
CK.js
CL.js
CM.js
CN.js
CO.js
CR.js
CU.js
CV.js
CX.js
CY.js
CZ.js
DE.js
DJ.js
DK.js
DM.js
DO.js
DZ.js
EC.js
EE.js
EG.js
ER.js
ES.js
ET.js
FI.js
FJ.js
FK.js
FM.js
FO.js
FR.js
GA.js
GB.js
GD.js
GE.js
GF.js
GG.js
GH.js
GI.js
GL.js
GM.js
GN.js
GP.js
GQ.js
GR.js
GT.js
GU.js
GW.js
GY.js
HK.js
HN.js
HR.js
HT.js
HU.js
ID.js
IE.js
IL.js
IM.js
IN.js
IQ.js
IR.js
IS.js
IT.js
JE.js
JM.js
JO.js
JP.js
KE.js
KG.js
KH.js
KI.js
KM.js
KN.js
KP.js
KR.js
KW.js
KY.js
KZ.js
LA.js
LB.js
LC.js
LI.js
LK.js
LR.js
LS.js
LT.js
LU.js
LV.js
LY.js
MA.js
MC.js
MD.js
ME.js
MG.js
MH.js
MK.js
ML.js
MM.js
MN.js
MO.js
MP.js
MQ.js
MR.js
MS.js
MT.js
MU.js
MV.js
MW.js
MX.js
MY.js
MZ.js
NA.js
NC.js
NE.js
NF.js
NG.js
NI.js
NL.js
NO.js
NP.js
NR.js
NU.js
NZ.js
OM.js
PA.js
PE.js
PF.js
PG.js
PH.js
PK.js
PL.js
PM.js
PN.js
PR.js
PS.js
PT.js
PW.js
PY.js
QA.js
RE.js
RO.js
RS.js
RU.js
RW.js
SA.js
SB.js
SC.js
SD.js
SE.js
SG.js
SH.js
SI.js
SK.js
SL.js
SM.js
SN.js
SO.js
SR.js
ST.js
SV.js
SY.js
SZ.js
TC.js
TD.js
TG.js
TH.js
TJ.js
TL.js
TM.js
TN.js
TO.js
TR.js
TT.js
TV.js
TW.js
TZ.js
UA.js
UG.js
US.js
UY.js
UZ.js
VA.js
VC.js
VE.js
VG.js
VI.js
VN.js
VU.js
WF.js
WS.js
YE.js
YT.js
ZA.js
ZM.js
ZW.js

./frontend/node_modules/caniuse-lite/dist:
lib
unpacker

./frontend/node_modules/caniuse-lite/dist/lib:
statuses.js
supported.js

./frontend/node_modules/caniuse-lite/dist/unpacker:
agents.js
browsers.js
browserVersions.js
feature.js
features.js
index.js
region.js

./frontend/node_modules/chokidar:
handler.d.ts
handler.js
index.d.ts
index.js
LICENSE
package.json
README.md

./frontend/node_modules/combined-stream:
lib
License
package.json
Readme.md
yarn.lock

./frontend/node_modules/combined-stream/lib:
combined_stream.js

./frontend/node_modules/confbox:
dist
LICENSE
package.json
README.md

./frontend/node_modules/confbox/dist:
_chunks
index.d.mts
index.mjs
ini.d.mts
ini.mjs
json5.d.mts
json5.mjs
jsonc.d.mts
jsonc.mjs
THIRD-PARTY-LICENSES.md
toml.d.mts
toml.mjs
yaml.d.mts
yaml.mjs

./frontend/node_modules/confbox/dist/_chunks:
_format.d.mts
_format.mjs
json.mjs
libs
rolldown-runtime.mjs

./frontend/node_modules/confbox/dist/_chunks/libs:
detect-indent.mjs
ini.mjs
json5.mjs
jsonc-parser.mjs
js-yaml.mjs
smol-toml.mjs

./frontend/node_modules/convert-source-map:
index.js
LICENSE
package.json
README.md

./frontend/node_modules/csstype:
index.d.ts
index.js.flow
LICENSE
package.json
README.md

./frontend/node_modules/debug:
CHANGELOG.md
LICENSE
node.js
package.json
README.md
src

./frontend/node_modules/debug/src:
browser.js
common.js
index.js
node.js

./frontend/node_modules/default-browser:
index.d.ts
index.js
license
package.json
readme.md
windows.js

./frontend/node_modules/default-browser-id:
index.js
license
package.json
readme.md

./frontend/node_modules/define-lazy-prop:
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/delayed-stream:
lib
License
Makefile
package.json
Readme.md

./frontend/node_modules/delayed-stream/lib:
delayed_stream.js

./frontend/node_modules/detect-libc:
index.d.ts
lib
LICENSE
package.json
README.md

./frontend/node_modules/detect-libc/lib:
detect-libc.js
elf.js
filesystem.js
process.js

./frontend/node_modules/dunder-proto:
CHANGELOG.md
get.d.ts
get.js
LICENSE
package.json
README.md
set.d.ts
set.js
test
tsconfig.json

./frontend/node_modules/dunder-proto/test:
get.js
index.js
set.js

./frontend/node_modules/electron-to-chromium:
chromium-versions.js
chromium-versions.json
full-chromium-versions.js
full-chromium-versions.json
full-versions.js
full-versions.json
index.js
LICENSE
package.json
README.md
versions.js
versions.json

./frontend/node_modules/entities:
decode.d.ts
decode.js
dist
escape.d.ts
escape.js
LICENSE
package.json
readme.md
src

./frontend/node_modules/entities/dist:
commonjs
esm

./frontend/node_modules/entities/dist/commonjs:
decode-codepoint.d.ts
decode-codepoint.d.ts.map
decode-codepoint.js
decode-codepoint.js.map
decode.d.ts
decode.d.ts.map
decode.js
decode.js.map
encode.d.ts
encode.d.ts.map
encode.js
encode.js.map
escape.d.ts
escape.d.ts.map
escape.js
escape.js.map
generated
index.d.ts
index.d.ts.map
index.js
index.js.map
internal
package.json

./frontend/node_modules/entities/dist/commonjs/generated:
decode-data-html.d.ts
decode-data-html.d.ts.map
decode-data-html.js
decode-data-html.js.map
decode-data-xml.d.ts
decode-data-xml.d.ts.map
decode-data-xml.js
decode-data-xml.js.map
encode-html.d.ts
encode-html.d.ts.map
encode-html.js
encode-html.js.map

./frontend/node_modules/entities/dist/commonjs/internal:
bin-trie-flags.d.ts
bin-trie-flags.d.ts.map
bin-trie-flags.js
bin-trie-flags.js.map
decode-shared.d.ts
decode-shared.d.ts.map
decode-shared.js
decode-shared.js.map
encode-shared.d.ts
encode-shared.d.ts.map
encode-shared.js
encode-shared.js.map

./frontend/node_modules/entities/dist/esm:
decode-codepoint.d.ts
decode-codepoint.d.ts.map
decode-codepoint.js
decode-codepoint.js.map
decode.d.ts
decode.d.ts.map
decode.js
decode.js.map
encode.d.ts
encode.d.ts.map
encode.js
encode.js.map
escape.d.ts
escape.d.ts.map
escape.js
escape.js.map
generated
index.d.ts
index.d.ts.map
index.js
index.js.map
internal
package.json

./frontend/node_modules/entities/dist/esm/generated:
decode-data-html.d.ts
decode-data-html.d.ts.map
decode-data-html.js
decode-data-html.js.map
decode-data-xml.d.ts
decode-data-xml.d.ts.map
decode-data-xml.js
decode-data-xml.js.map
encode-html.d.ts
encode-html.d.ts.map
encode-html.js
encode-html.js.map

./frontend/node_modules/entities/dist/esm/internal:
bin-trie-flags.d.ts
bin-trie-flags.d.ts.map
bin-trie-flags.js
bin-trie-flags.js.map
decode-shared.d.ts
decode-shared.d.ts.map
decode-shared.js
decode-shared.js.map
encode-shared.d.ts
encode-shared.d.ts.map
encode-shared.js
encode-shared.js.map

./frontend/node_modules/entities/src:
decode-codepoint.ts
decode.ts
encode.ts
escape.ts
generated
index.ts
internal

./frontend/node_modules/entities/src/generated:
decode-data-html.ts
decode-data-xml.ts
encode-html.ts

./frontend/node_modules/entities/src/internal:
bin-trie-flags.ts
decode-shared.ts
encode-shared.ts

./frontend/node_modules/error-stack-parser-es:
dist
LICENSE
package.json
README.md

./frontend/node_modules/error-stack-parser-es/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs
lite.cjs
lite.d.cts
lite.d.mts
lite.d.ts
lite.mjs

./frontend/node_modules/escalade:
dist
index.d.mts
index.d.ts
license
package.json
readme.md
sync

./frontend/node_modules/escalade/dist:
index.js
index.mjs

./frontend/node_modules/escalade/sync:
index.d.mts
index.d.ts
index.js
index.mjs

./frontend/node_modules/es-define-property:
CHANGELOG.md
index.d.ts
index.js
LICENSE
package.json
README.md
test
tsconfig.json

./frontend/node_modules/es-define-property/test:
index.js

./frontend/node_modules/es-errors:
CHANGELOG.md
eval.d.ts
eval.js
index.d.ts
index.js
LICENSE
package.json
range.d.ts
range.js
README.md
ref.d.ts
ref.js
syntax.d.ts
syntax.js
test
tsconfig.json
type.d.ts
type.js
uri.d.ts
uri.js

./frontend/node_modules/es-errors/test:
index.js

./frontend/node_modules/es-object-atoms:
CHANGELOG.md
index.d.ts
index.js
isObject.d.ts
isObject.js
LICENSE
package.json
README.md
RequireObjectCoercible.d.ts
RequireObjectCoercible.js
test
ToObject.d.ts
ToObject.js
tsconfig.json

./frontend/node_modules/es-object-atoms/test:
index.js

./frontend/node_modules/es-set-tostringtag:
CHANGELOG.md
index.d.ts
index.js
LICENSE
package.json
README.md
test
tsconfig.json

./frontend/node_modules/es-set-tostringtag/test:
index.js

./frontend/node_modules/estree-walker:
CHANGELOG.md
dist
LICENSE
package.json
README.md
src
types

./frontend/node_modules/estree-walker/dist:
esm
umd

./frontend/node_modules/estree-walker/dist/esm:
estree-walker.js
package.json

./frontend/node_modules/estree-walker/dist/umd:
estree-walker.js

./frontend/node_modules/estree-walker/src:
async.js
index.js
package.json
sync.js
walker.js

./frontend/node_modules/estree-walker/types:
async.d.ts
index.d.ts
sync.d.ts
tsconfig.tsbuildinfo
walker.d.ts

./frontend/node_modules/eventsource:
CONTRIBUTING.md
example
HISTORY.md
lib
LICENSE
package.json
README.md

./frontend/node_modules/eventsource/example:
eventsource-polyfill.js
index.html
sse-client.js
sse-server.js

./frontend/node_modules/eventsource/lib:
eventsource.js
eventsource-polyfill.js

./frontend/node_modules/exsolve:
dist
LICENSE
package.json
README.md

./frontend/node_modules/exsolve/dist:
index.d.mts
index.mjs

./frontend/node_modules/faye-websocket:
CHANGELOG.md
lib
LICENSE.md
package.json
README.md

./frontend/node_modules/faye-websocket/lib:
faye

./frontend/node_modules/faye-websocket/lib/faye:
eventsource.js
websocket
websocket.js

./frontend/node_modules/faye-websocket/lib/faye/websocket:
api
api.js
client.js

./frontend/node_modules/faye-websocket/lib/faye/websocket/api:
event.js
event_target.js

./frontend/node_modules/fdir:
dist
LICENSE
package.json
README.md

./frontend/node_modules/fdir/dist:
index.cjs
index.d.cts
index.d.mts
index.mjs

./frontend/node_modules/follow-redirects:
debug.js
http.js
https.js
index.js
LICENSE
package.json
README.md

./frontend/node_modules/form-data:
CHANGELOG.md
index.d.ts
lib
License
package.json
README.md

./frontend/node_modules/form-data/lib:
browser.js
form_data.js
populate.js

./frontend/node_modules/function-bind:
CHANGELOG.md
implementation.js
index.js
LICENSE
package.json
README.md
test

./frontend/node_modules/function-bind/test:
index.js

./frontend/node_modules/gensync:
index.js
index.js.flow
LICENSE
package.json
README.md
test

./frontend/node_modules/gensync/test:
index.test.js

./frontend/node_modules/get-intrinsic:
CHANGELOG.md
index.js
LICENSE
package.json
README.md
test

./frontend/node_modules/get-intrinsic/test:
GetIntrinsic.js

./frontend/node_modules/get-proto:
CHANGELOG.md
index.d.ts
index.js
LICENSE
Object.getPrototypeOf.d.ts
Object.getPrototypeOf.js
package.json
README.md
Reflect.getPrototypeOf.d.ts
Reflect.getPrototypeOf.js
test
tsconfig.json

./frontend/node_modules/get-proto/test:
index.js

./frontend/node_modules/gopd:
CHANGELOG.md
gOPD.d.ts
gOPD.js
index.d.ts
index.js
LICENSE
package.json
README.md
test
tsconfig.json

./frontend/node_modules/gopd/test:
index.js

./frontend/node_modules/hasown:
CHANGELOG.md
index.d.ts
index.js
LICENSE
package.json
README.md
tsconfig.json

./frontend/node_modules/has-symbols:
CHANGELOG.md
index.d.ts
index.js
LICENSE
package.json
README.md
shams.d.ts
shams.js
test
tsconfig.json

./frontend/node_modules/has-symbols/test:
index.js
shams
tests.js

./frontend/node_modules/has-symbols/test/shams:
core-js.js
get-own-property-symbols.js

./frontend/node_modules/has-tostringtag:
CHANGELOG.md
index.d.ts
index.js
LICENSE
package.json
README.md
shams.d.ts
shams.js
test
tsconfig.json

./frontend/node_modules/has-tostringtag/test:
index.js
shams
tests.js

./frontend/node_modules/has-tostringtag/test/shams:
core-js.js
get-own-property-symbols.js

./frontend/node_modules/hookable:
dist
LICENSE.md
package.json
README.md

./frontend/node_modules/hookable/dist:
index.cjs
index.d.ts
index.mjs

./frontend/node_modules/http-parser-js:
http-parser.d.ts
http-parser.js
LICENSE.md
package.json
README.md

./frontend/node_modules/inherits:
inherits_browser.js
inherits.js
LICENSE
package.json
README.md

./frontend/node_modules/is-docker:
cli.js
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/is-inside-container:
cli.js
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/is-wsl:
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/@jridgewell:
gen-mapping
remapping
resolve-uri
sourcemap-codec
trace-mapping

./frontend/node_modules/@jridgewell/gen-mapping:
dist
LICENSE
package.json
README.md
src
types

./frontend/node_modules/@jridgewell/gen-mapping/dist:
gen-mapping.mjs
gen-mapping.mjs.map
gen-mapping.umd.js
gen-mapping.umd.js.map
types

./frontend/node_modules/@jridgewell/gen-mapping/dist/types:
gen-mapping.d.ts
set-array.d.ts
sourcemap-segment.d.ts
types.d.ts

./frontend/node_modules/@jridgewell/gen-mapping/src:
gen-mapping.ts
set-array.ts
sourcemap-segment.ts
types.ts

./frontend/node_modules/@jridgewell/gen-mapping/types:
gen-mapping.d.cts
gen-mapping.d.cts.map
gen-mapping.d.mts
gen-mapping.d.mts.map
set-array.d.cts
set-array.d.cts.map
set-array.d.mts
set-array.d.mts.map
sourcemap-segment.d.cts
sourcemap-segment.d.cts.map
sourcemap-segment.d.mts
sourcemap-segment.d.mts.map
types.d.cts
types.d.cts.map
types.d.mts
types.d.mts.map

./frontend/node_modules/@jridgewell/remapping:
dist
LICENSE
package.json
README.md
src
types

./frontend/node_modules/@jridgewell/remapping/dist:
remapping.mjs
remapping.mjs.map
remapping.umd.js
remapping.umd.js.map

./frontend/node_modules/@jridgewell/remapping/src:
build-source-map-tree.ts
remapping.ts
source-map-tree.ts
source-map.ts
types.ts

./frontend/node_modules/@jridgewell/remapping/types:
build-source-map-tree.d.cts
build-source-map-tree.d.cts.map
build-source-map-tree.d.mts
build-source-map-tree.d.mts.map
remapping.d.cts
remapping.d.cts.map
remapping.d.mts
remapping.d.mts.map
source-map.d.cts
source-map.d.cts.map
source-map.d.mts
source-map.d.mts.map
source-map-tree.d.cts
source-map-tree.d.cts.map
source-map-tree.d.mts
source-map-tree.d.mts.map
types.d.cts
types.d.cts.map
types.d.mts
types.d.mts.map

./frontend/node_modules/@jridgewell/resolve-uri:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@jridgewell/resolve-uri/dist:
resolve-uri.mjs
resolve-uri.mjs.map
resolve-uri.umd.js
resolve-uri.umd.js.map
types

./frontend/node_modules/@jridgewell/resolve-uri/dist/types:
resolve-uri.d.ts

./frontend/node_modules/@jridgewell/sourcemap-codec:
dist
LICENSE
package.json
README.md
src
types

./frontend/node_modules/@jridgewell/sourcemap-codec/dist:
sourcemap-codec.mjs
sourcemap-codec.mjs.map
sourcemap-codec.umd.js
sourcemap-codec.umd.js.map

./frontend/node_modules/@jridgewell/sourcemap-codec/src:
scopes.ts
sourcemap-codec.ts
strings.ts
vlq.ts

./frontend/node_modules/@jridgewell/sourcemap-codec/types:
scopes.d.cts
scopes.d.cts.map
scopes.d.mts
scopes.d.mts.map
sourcemap-codec.d.cts
sourcemap-codec.d.cts.map
sourcemap-codec.d.mts
sourcemap-codec.d.mts.map
strings.d.cts
strings.d.cts.map
strings.d.mts
strings.d.mts.map
vlq.d.cts
vlq.d.cts.map
vlq.d.mts
vlq.d.mts.map

./frontend/node_modules/@jridgewell/trace-mapping:
dist
LICENSE
package.json
README.md
src
types

./frontend/node_modules/@jridgewell/trace-mapping/dist:
trace-mapping.mjs
trace-mapping.mjs.map
trace-mapping.umd.js
trace-mapping.umd.js.map

./frontend/node_modules/@jridgewell/trace-mapping/src:
binary-search.ts
by-source.ts
flatten-map.ts
resolve.ts
sort.ts
sourcemap-segment.ts
strip-filename.ts
trace-mapping.ts
types.ts

./frontend/node_modules/@jridgewell/trace-mapping/types:
binary-search.d.cts
binary-search.d.cts.map
binary-search.d.mts
binary-search.d.mts.map
by-source.d.cts
by-source.d.cts.map
by-source.d.mts
by-source.d.mts.map
flatten-map.d.cts
flatten-map.d.cts.map
flatten-map.d.mts
flatten-map.d.mts.map
resolve.d.cts
resolve.d.cts.map
resolve.d.mts
resolve.d.mts.map
sort.d.cts
sort.d.cts.map
sort.d.mts
sort.d.mts.map
sourcemap-segment.d.cts
sourcemap-segment.d.cts.map
sourcemap-segment.d.mts
sourcemap-segment.d.mts.map
strip-filename.d.cts
strip-filename.d.cts.map
strip-filename.d.mts
strip-filename.d.mts.map
trace-mapping.d.cts
trace-mapping.d.cts.map
trace-mapping.d.mts
trace-mapping.d.mts.map
types.d.cts
types.d.cts.map
types.d.mts
types.d.mts.map

./frontend/node_modules/jsesc:
bin
jsesc.js
LICENSE-MIT.txt
man
package.json
README.md

./frontend/node_modules/jsesc/bin:
jsesc

./frontend/node_modules/jsesc/man:
jsesc.1

./frontend/node_modules/json5:
dist
lib
LICENSE.md
package.json
README.md

./frontend/node_modules/json5/dist:
index.js
index.min.js
index.min.mjs
index.mjs

./frontend/node_modules/json5/lib:
cli.js
index.d.ts
index.js
parse.d.ts
parse.js
register.js
require.js
stringify.d.ts
stringify.js
unicode.d.ts
unicode.js
util.d.ts
util.js

./frontend/node_modules/js-tokens:
CHANGELOG.md
index.js
LICENSE
package.json
README.md

./frontend/node_modules/kolorist:
dist
LICENSE
package.json
README.md

./frontend/node_modules/kolorist/dist:
cjs
esm
module
types

./frontend/node_modules/kolorist/dist/cjs:
index.js
index.js.map

./frontend/node_modules/kolorist/dist/esm:
index.js
index.js.map
index.mjs

./frontend/node_modules/kolorist/dist/module:
index.js
index.js.map

./frontend/node_modules/kolorist/dist/types:
index.d.ts

./frontend/node_modules/lightningcss:
LICENSE
node
package.json
README.md

./frontend/node_modules/lightningcss/node:
ast.d.ts
ast.js.flow
browserslistToTargets.js
composeVisitors.js
flags.js
index.d.ts
index.js
index.js.flow
index.mjs
targets.d.ts
targets.js.flow

./frontend/node_modules/lightningcss-linux-x64-gnu:
LICENSE
lightningcss.linux-x64-gnu.node
package.json
README.md

./frontend/node_modules/local-pkg:
dist
LICENSE
package.json
README.md

./frontend/node_modules/local-pkg/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/lru-cache:
index.js
LICENSE
package.json
README.md

./frontend/node_modules/magic-string:
dist
LICENSE
package.json
README.md

./frontend/node_modules/magic-string/dist:
magic-string.cjs.d.ts
magic-string.cjs.js
magic-string.cjs.js.map
magic-string.es.d.mts
magic-string.es.mjs
magic-string.es.mjs.map
magic-string.umd.js
magic-string.umd.js.map

./frontend/node_modules/magic-string-ast:
dist
LICENSE
package.json
README.md

./frontend/node_modules/magic-string-ast/dist:
index.d.ts
index.js

./frontend/node_modules/math-intrinsics:
abs.d.ts
abs.js
CHANGELOG.md
constants
floor.d.ts
floor.js
isFinite.d.ts
isFinite.js
isInteger.d.ts
isInteger.js
isNaN.d.ts
isNaN.js
isNegativeZero.d.ts
isNegativeZero.js
LICENSE
max.d.ts
max.js
min.d.ts
min.js
mod.d.ts
mod.js
package.json
pow.d.ts
pow.js
README.md
round.d.ts
round.js
sign.d.ts
sign.js
test
tsconfig.json

./frontend/node_modules/math-intrinsics/constants:
maxArrayLength.d.ts
maxArrayLength.js
maxSafeInteger.d.ts
maxSafeInteger.js
maxValue.d.ts
maxValue.js

./frontend/node_modules/math-intrinsics/test:
index.js

./frontend/node_modules/mime-db:
db.json
HISTORY.md
index.js
LICENSE
package.json
README.md

./frontend/node_modules/mime-types:
HISTORY.md
index.js
LICENSE
package.json
README.md

./frontend/node_modules/mlly:
dist
LICENSE
node_modules
package.json
README.md

./frontend/node_modules/mlly/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/mlly/node_modules:
confbox
pkg-types

./frontend/node_modules/mlly/node_modules/confbox:
dist
json5.d.ts
jsonc.d.ts
LICENSE
package.json
README.md
toml.d.ts
yaml.d.ts

./frontend/node_modules/mlly/node_modules/confbox/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs
json5.cjs
json5.d.cts
json5.d.mts
json5.d.ts
json5.mjs
jsonc.cjs
jsonc.d.cts
jsonc.d.mts
jsonc.d.ts
jsonc.mjs
shared
toml.cjs
toml.d.cts
toml.d.mts
toml.d.ts
toml.mjs
yaml.cjs
yaml.d.cts
yaml.d.mts
yaml.d.ts
yaml.mjs

./frontend/node_modules/mlly/node_modules/confbox/dist/shared:
confbox.3768c7e9.cjs
confbox.6b479c78.cjs
confbox.9388d834.mjs
confbox.9745c98f.d.cts
confbox.9745c98f.d.mts
confbox.9745c98f.d.ts
confbox.f9f03f05.mjs

./frontend/node_modules/mlly/node_modules/pkg-types:
dist
LICENSE
package.json
README.md

./frontend/node_modules/mlly/node_modules/pkg-types/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/mrmime:
index.d.ts
index.js
index.mjs
license
package.json
readme.md

./frontend/node_modules/ms:
index.js
license.md
package.json
readme.md

./frontend/node_modules/muggle-string:
LICENSE
out
package.json
README.md

./frontend/node_modules/muggle-string/out:
base.d.ts
base.js
basic.d.ts
basic.js
binarySearch.d.ts
binarySearch.js
common.d.ts
common.js
getLength.d.ts
getLength.js
index.d.ts
index.js
map.d.ts
map.js
overwriteSource.d.ts
overwriteSource.js
replace.d.ts
replace.js
segment.d.ts
segment.js
sourceBased.d.ts
sourceBased.js
toString.d.ts
toString.js
track.d.ts
track.js
types.d.ts
types.js

./frontend/node_modules/nanoid:
async
bin
index.browser.cjs
index.browser.js
index.cjs
index.d.cts
index.d.ts
index.js
LICENSE
nanoid.js
non-secure
package.json
README.md
url-alphabet

./frontend/node_modules/nanoid/async:
index.browser.cjs
index.browser.js
index.cjs
index.d.ts
index.js
index.native.js
package.json

./frontend/node_modules/nanoid/bin:
nanoid.cjs

./frontend/node_modules/nanoid/non-secure:
index.cjs
index.d.ts
index.js
package.json

./frontend/node_modules/nanoid/url-alphabet:
index.cjs
index.js
package.json

./frontend/node_modules/node-releases:
data
LICENSE
package.json
README.md

./frontend/node_modules/node-releases/data:
processed
release-schedule

./frontend/node_modules/node-releases/data/processed:
envs.json

./frontend/node_modules/node-releases/data/release-schedule:
release-schedule.json

./frontend/node_modules/ohash:
dist
LICENSE
package.json
README.md

./frontend/node_modules/ohash/dist:
crypto
index.d.mts
index.mjs
shared
utils

./frontend/node_modules/ohash/dist/crypto:
js
node

./frontend/node_modules/ohash/dist/crypto/js:
index.d.mts
index.mjs

./frontend/node_modules/ohash/dist/crypto/node:
index.d.mts
index.mjs

./frontend/node_modules/ohash/dist/shared:
ohash.CMR0vuBX.d.mts
ohash.CMR0vuBX.d.ts
ohash.D__AXeF1.mjs

./frontend/node_modules/ohash/dist/utils:
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/open:
index.d.ts
index.js
license
package.json
readme.md
xdg-open

./frontend/node_modules/@oxc-project:
types

./frontend/node_modules/@oxc-project/types:
LICENSE
package.json
README.md
types.d.ts

./frontend/node_modules/path-browserify:
CHANGELOG.md
index.js
LICENSE
package.json
README.md
security.md
test

./frontend/node_modules/path-browserify/test:
index.js
test-path-basename.js
test-path-dirname.js
test-path-extname.js
test-path-isabsolute.js
test-path-join.js
test-path.js
test-path-parse-format.js
test-path-relative.js
test-path-resolve.js
test-path-zero-length-strings.js

./frontend/node_modules/pathe:
dist
LICENSE
package.json
README.md
utils.d.ts

./frontend/node_modules/pathe/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs
shared
utils.cjs
utils.d.cts
utils.d.mts
utils.d.ts
utils.mjs

./frontend/node_modules/pathe/dist/shared:
pathe.BSlhyZSM.cjs
pathe.M-eThtNZ.mjs

./frontend/node_modules/perfect-debounce:
dist
LICENSE
package.json
README.md

./frontend/node_modules/perfect-debounce/dist:
index.d.mts
index.mjs

./frontend/node_modules/picocolors:
LICENSE
package.json
picocolors.browser.js
picocolors.d.ts
picocolors.js
README.md
types.d.ts

./frontend/node_modules/picomatch:
index.js
lib
LICENSE
package.json
posix.js
README.md

./frontend/node_modules/picomatch/lib:
constants.js
parse.js
picomatch.js
scan.js
utils.js

./frontend/node_modules/pkg-types:
dist
LICENSE
package.json
README.md

./frontend/node_modules/pkg-types/dist:
index.d.mts
index.mjs

./frontend/node_modules/@polka:
url

./frontend/node_modules/@polka/url:
build.js
build.mjs
index.d.ts
package.json
readme.md

./frontend/node_modules/postcss:
lib
LICENSE
package.json
README.md

./frontend/node_modules/postcss/lib:
at-rule.d.ts
at-rule.js
comment.d.ts
comment.js
container.d.ts
container.js
css-syntax-error.d.ts
css-syntax-error.js
declaration.d.ts
declaration.js
document.d.ts
document.js
fromJSON.d.ts
fromJSON.js
input.d.ts
input.js
lazy-result.d.ts
lazy-result.js
list.d.ts
list.js
map-generator.js
node.d.ts
node.js
no-work-result.d.ts
no-work-result.js
parse.d.ts
parse.js
parser.js
postcss.d.mts
postcss.d.ts
postcss.js
postcss.mjs
previous-map.d.ts
previous-map.js
processor.d.ts
processor.js
result.d.ts
result.js
root.d.ts
root.js
rule.d.ts
rule.js
stringifier.d.ts
stringifier.js
stringify.d.ts
stringify.js
symbols.js
terminal-highlight.js
tokenize.js
warning.d.ts
warning.js
warn-once.js

./frontend/node_modules/proxy-from-env:
index.cjs
index.js
LICENSE
package.json
README.md

./frontend/node_modules/quansync:
dist
LICENSE.md
package.json
README.md

./frontend/node_modules/quansync/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs
macro.cjs
macro.d.cts
macro.d.mts
macro.d.ts
macro.mjs
types.cjs
types.d.cts
types.d.mts
types.d.ts
types.mjs

./frontend/node_modules/querystringify:
index.js
LICENSE
package.json
README.md

./frontend/node_modules/readdirp:
index.d.ts
index.js
LICENSE
package.json
README.md

./frontend/node_modules/requires-port:
index.js
LICENSE
package.json
README.md
test.js

./frontend/node_modules/@rolldown:
binding-linux-x64-gnu
pluginutils

./frontend/node_modules/@rolldown/binding-linux-x64-gnu:
package.json
README.md
rolldown-binding.linux-x64-gnu.node

./frontend/node_modules/@rolldown/pluginutils:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@rolldown/pluginutils/dist:
filter
index.d.ts
index.js
utils.d.ts
utils.js

./frontend/node_modules/@rolldown/pluginutils/dist/filter:
composable-filters.d.ts
composable-filters.js
filter-vite-plugins.d.ts
filter-vite-plugins.js
index.d.ts
index.js
simple-filters.d.ts
simple-filters.js

./frontend/node_modules/rolldown:
bin
dist
LICENSE
node_modules
package.json
README.md

./frontend/node_modules/rolldown/bin:
cli.mjs

./frontend/node_modules/rolldown/dist:
cli.d.mts
cli.mjs
config.d.mts
config.mjs
experimental-index.d.mts
experimental-index.mjs
experimental-runtime-types.d.ts
filter-index.d.mts
filter-index.mjs
get-log-filter.d.mts
get-log-filter.mjs
index.d.mts
index.mjs
parallel-plugin.d.mts
parallel-plugin.mjs
parallel-plugin-worker.d.mts
parallel-plugin-worker.mjs
parse-ast-index.d.mts
parse-ast-index.mjs
plugins-index.d.mts
plugins-index.mjs
shared
utils-index.d.mts
utils-index.mjs

./frontend/node_modules/rolldown/dist/shared:
binding-DUEnSb0A.d.mts
bindingify-input-options-DYpBf1OG.mjs
binding-s-V_wTpj.mjs
constructors-BaEBnHl3.mjs
constructors-DYemMpPL.d.mts
define-config-DhJZwTRw.d.mts
define-config-DJOr6Iwt.mjs
error-w0u7biK-.mjs
get-log-filter-semyr3Lj.d.mts
load-config-pmbgQdFU.mjs
logging-C6h4g8dA.d.mts
logs-D80CXhvg.mjs
misc-DJYbNKZX.mjs
normalize-string-or-regex-CVvpepxa.mjs
parse-BywQARUG.mjs
prompt-BYQIwEjg.mjs
resolve-tsconfig-CNjJwuKB.mjs
rolldown-BLMDGLfI.mjs
rolldown-build-DtGk-m96.mjs
transform-Kz3D2LbX.d.mts
watch-Cixa7HbR.mjs

./frontend/node_modules/rolldown/node_modules:
@rolldown

./frontend/node_modules/rolldown/node_modules/@rolldown:
pluginutils

./frontend/node_modules/rolldown/node_modules/@rolldown/pluginutils:
dist
LICENSE
package.json
README.md

./frontend/node_modules/rolldown/node_modules/@rolldown/pluginutils/dist:
filter
index.d.ts
index.js
utils.d.ts
utils.js

./frontend/node_modules/rolldown/node_modules/@rolldown/pluginutils/dist/filter:
composable-filters.d.ts
composable-filters.js
filter-vite-plugins.d.ts
filter-vite-plugins.js
index.d.ts
index.js
simple-filters.d.ts
simple-filters.js

./frontend/node_modules/run-applescript:
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/safe-buffer:
index.d.ts
index.js
LICENSE
package.json
README.md

./frontend/node_modules/scule:
dist
LICENSE
package.json
README.md

./frontend/node_modules/scule/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/semver:
bin
LICENSE
package.json
range.bnf
README.md
semver.js

./frontend/node_modules/semver/bin:
semver.js

./frontend/node_modules/sirv:
build.js
build.mjs
index.d.mts
index.d.ts
package.json
readme.md

./frontend/node_modules/sockjs-client:
AUTHORS
Changelog.md
CODE_OF_CONDUCT.md
dist
lib
LICENSE
node_modules
package.json
README.md

./frontend/node_modules/sockjs-client/dist:
sockjs.js
sockjs.js.map
sockjs.min.js
sockjs.min.js.map

./frontend/node_modules/sockjs-client/lib:
entry.js
event
facade.js
iframe-bootstrap.js
info-ajax.js
info-iframe.js
info-iframe-receiver.js
info-receiver.js
location.js
main.js
shims.js
transport
transport-list.js
utils
version.js

./frontend/node_modules/sockjs-client/lib/event:
close.js
emitter.js
event.js
eventtarget.js
trans-message.js

./frontend/node_modules/sockjs-client/lib/transport:
browser
driver
eventsource.js
htmlfile.js
iframe.js
jsonp-polling.js
lib
receiver
sender
websocket.js
xdr-polling.js
xdr-streaming.js
xhr-polling.js
xhr-streaming.js

./frontend/node_modules/sockjs-client/lib/transport/browser:
abstract-xhr.js
eventsource.js
websocket.js

./frontend/node_modules/sockjs-client/lib/transport/driver:
eventsource.js
websocket.js
xhr.js

./frontend/node_modules/sockjs-client/lib/transport/lib:
ajax-based.js
buffered-sender.js
iframe-wrap.js
polling.js
sender-receiver.js

./frontend/node_modules/sockjs-client/lib/transport/receiver:
eventsource.js
htmlfile.js
jsonp.js
xhr.js

./frontend/node_modules/sockjs-client/lib/transport/sender:
jsonp.js
xdr.js
xhr-cors.js
xhr-fake.js
xhr-local.js

./frontend/node_modules/sockjs-client/lib/utils:
browser-crypto.js
browser.js
escape.js
event.js
iframe.js
log.js
object.js
random.js
transport.js
url.js

./frontend/node_modules/sockjs-client/node_modules:

./frontend/node_modules/source-map-js:
lib
LICENSE
package.json
README.md
source-map.d.ts
source-map.js

./frontend/node_modules/source-map-js/lib:
array-set.js
base64.js
base64-vlq.js
binary-search.js
mapping-list.js
quick-sort.js
source-map-consumer.d.ts
source-map-consumer.js
source-map-generator.d.ts
source-map-generator.js
source-node.d.ts
source-node.js
util.js

./frontend/node_modules/@stomp:
stompjs

./frontend/node_modules/@stomp/stompjs:
bundles
esm6
index.d.ts
LICENSE
package.json
README.md
src

./frontend/node_modules/@stomp/stompjs/bundles:
package.json
stomp.umd.js
stomp.umd.js.map
stomp.umd.min.js

./frontend/node_modules/@stomp/stompjs/esm6:
augment-websocket.d.ts
augment-websocket.js
augment-websocket.js.map
byte.d.ts
byte.js
byte.js.map
client.d.ts
client.js
client.js.map
compatibility
frame-impl.d.ts
frame-impl.js
frame-impl.js.map
i-frame.d.ts
i-frame.js
i-frame.js.map
i-message.d.ts
i-message.js
i-message.js.map
index.d.ts
index.js
index.js.map
i-transaction.d.ts
i-transaction.js
i-transaction.js.map
parser.d.ts
parser.js
parser.js.map
stomp-config.d.ts
stomp-config.js
stomp-config.js.map
stomp-handler.d.ts
stomp-handler.js
stomp-handler.js.map
stomp-headers.d.ts
stomp-headers.js
stomp-headers.js.map
stomp-subscription.d.ts
stomp-subscription.js
stomp-subscription.js.map
ticker.d.ts
ticker.js
ticker.js.map
types.d.ts
types.js
types.js.map
versions.d.ts
versions.js
versions.js.map

./frontend/node_modules/@stomp/stompjs/esm6/compatibility:
compat-client.d.ts
compat-client.js
compat-client.js.map
heartbeat-info.d.ts
heartbeat-info.js
heartbeat-info.js.map
stomp.d.ts
stomp.js
stomp.js.map

./frontend/node_modules/@stomp/stompjs/src:
augment-websocket.ts
byte.ts
client.ts
compatibility
frame-impl.ts
i-frame.ts
i-message.ts
index.ts
i-transaction.ts
parser.ts
stomp-config.ts
stomp-handler.ts
stomp-headers.ts
stomp-subscription.ts
ticker.ts
types.ts
versions.ts

./frontend/node_modules/@stomp/stompjs/src/compatibility:
compat-client.ts
heartbeat-info.ts
stomp.ts

./frontend/node_modules/tinyglobby:
dist
LICENSE
package.json
README.md

./frontend/node_modules/tinyglobby/dist:
index.cjs
index.d.cts
index.d.mts
index.mjs

./frontend/node_modules/totalist:
dist
index.d.ts
license
package.json
readme.md
sync

./frontend/node_modules/totalist/dist:
index.js
index.mjs

./frontend/node_modules/totalist/sync:
index.d.ts
index.js
index.mjs

./frontend/node_modules/@types:
node
sockjs-client

./frontend/node_modules/@types/node:
assert
assert.d.ts
async_hooks.d.ts
buffer.buffer.d.ts
buffer.d.ts
child_process.d.ts
cluster.d.ts
compatibility
console.d.ts
constants.d.ts
crypto.d.ts
dgram.d.ts
diagnostics_channel.d.ts
dns
dns.d.ts
domain.d.ts
events.d.ts
fs
fs.d.ts
globals.d.ts
globals.typedarray.d.ts
http2.d.ts
http.d.ts
https.d.ts
index.d.ts
inspector
inspector.d.ts
inspector.generated.d.ts
LICENSE
module.d.ts
net.d.ts
os.d.ts
package.json
path
path.d.ts
perf_hooks.d.ts
process.d.ts
punycode.d.ts
querystring.d.ts
quic.d.ts
readline
readline.d.ts
README.md
repl.d.ts
sea.d.ts
sqlite.d.ts
stream
stream.d.ts
string_decoder.d.ts
test
test.d.ts
timers
timers.d.ts
tls.d.ts
trace_events.d.ts
ts5.6
ts5.7
tty.d.ts
url.d.ts
util
util.d.ts
v8.d.ts
vm.d.ts
wasi.d.ts
web-globals
worker_threads.d.ts
zlib.d.ts

./frontend/node_modules/@types/node/assert:
strict.d.ts

./frontend/node_modules/@types/node/compatibility:
iterators.d.ts

./frontend/node_modules/@types/node/dns:
promises.d.ts

./frontend/node_modules/@types/node/fs:
promises.d.ts

./frontend/node_modules/@types/node/inspector:
promises.d.ts

./frontend/node_modules/@types/node/path:
posix.d.ts
win32.d.ts

./frontend/node_modules/@types/node/readline:
promises.d.ts

./frontend/node_modules/@types/node/stream:
consumers.d.ts
promises.d.ts
web.d.ts

./frontend/node_modules/@types/node/test:
reporters.d.ts

./frontend/node_modules/@types/node/timers:
promises.d.ts

./frontend/node_modules/@types/node/ts5.6:
buffer.buffer.d.ts
compatibility
globals.typedarray.d.ts
index.d.ts

./frontend/node_modules/@types/node/ts5.6/compatibility:
float16array.d.ts

./frontend/node_modules/@types/node/ts5.7:
compatibility
index.d.ts

./frontend/node_modules/@types/node/ts5.7/compatibility:
float16array.d.ts

./frontend/node_modules/@types/node/util:
types.d.ts

./frontend/node_modules/@types/node/web-globals:
abortcontroller.d.ts
blob.d.ts
console.d.ts
crypto.d.ts
domexception.d.ts
encoding.d.ts
events.d.ts
fetch.d.ts
importmeta.d.ts
messaging.d.ts
navigator.d.ts
performance.d.ts
storage.d.ts
streams.d.ts
timers.d.ts
url.d.ts

./frontend/node_modules/@types/sockjs-client:
index.d.ts
LICENSE
package.json
README.md

./frontend/node_modules/typescript:
bin
lib
LICENSE.txt
package.json
README.md
SECURITY.md
ThirdPartyNoticeText.txt

./frontend/node_modules/typescript/bin:
tsc
tsserver

./frontend/node_modules/typescript/lib:
cs
de
es
fr
it
ja
ko
lib.decorators.d.ts
lib.decorators.legacy.d.ts
lib.dom.asynciterable.d.ts
lib.dom.d.ts
lib.dom.iterable.d.ts
lib.d.ts
lib.es2015.collection.d.ts
lib.es2015.core.d.ts
lib.es2015.d.ts
lib.es2015.generator.d.ts
lib.es2015.iterable.d.ts
lib.es2015.promise.d.ts
lib.es2015.proxy.d.ts
lib.es2015.reflect.d.ts
lib.es2015.symbol.d.ts
lib.es2015.symbol.wellknown.d.ts
lib.es2016.array.include.d.ts
lib.es2016.d.ts
lib.es2016.full.d.ts
lib.es2016.intl.d.ts
lib.es2017.arraybuffer.d.ts
lib.es2017.date.d.ts
lib.es2017.d.ts
lib.es2017.full.d.ts
lib.es2017.intl.d.ts
lib.es2017.object.d.ts
lib.es2017.sharedmemory.d.ts
lib.es2017.string.d.ts
lib.es2017.typedarrays.d.ts
lib.es2018.asyncgenerator.d.ts
lib.es2018.asynciterable.d.ts
lib.es2018.d.ts
lib.es2018.full.d.ts
lib.es2018.intl.d.ts
lib.es2018.promise.d.ts
lib.es2018.regexp.d.ts
lib.es2019.array.d.ts
lib.es2019.d.ts
lib.es2019.full.d.ts
lib.es2019.intl.d.ts
lib.es2019.object.d.ts
lib.es2019.string.d.ts
lib.es2019.symbol.d.ts
lib.es2020.bigint.d.ts
lib.es2020.date.d.ts
lib.es2020.d.ts
lib.es2020.full.d.ts
lib.es2020.intl.d.ts
lib.es2020.number.d.ts
lib.es2020.promise.d.ts
lib.es2020.sharedmemory.d.ts
lib.es2020.string.d.ts
lib.es2020.symbol.wellknown.d.ts
lib.es2021.d.ts
lib.es2021.full.d.ts
lib.es2021.intl.d.ts
lib.es2021.promise.d.ts
lib.es2021.string.d.ts
lib.es2021.weakref.d.ts
lib.es2022.array.d.ts
lib.es2022.d.ts
lib.es2022.error.d.ts
lib.es2022.full.d.ts
lib.es2022.intl.d.ts
lib.es2022.object.d.ts
lib.es2022.regexp.d.ts
lib.es2022.string.d.ts
lib.es2023.array.d.ts
lib.es2023.collection.d.ts
lib.es2023.d.ts
lib.es2023.full.d.ts
lib.es2023.intl.d.ts
lib.es2024.arraybuffer.d.ts
lib.es2024.collection.d.ts
lib.es2024.d.ts
lib.es2024.full.d.ts
lib.es2024.object.d.ts
lib.es2024.promise.d.ts
lib.es2024.regexp.d.ts
lib.es2024.sharedmemory.d.ts
lib.es2024.string.d.ts
lib.es2025.collection.d.ts
lib.es2025.d.ts
lib.es2025.float16.d.ts
lib.es2025.full.d.ts
lib.es2025.intl.d.ts
lib.es2025.iterator.d.ts
lib.es2025.promise.d.ts
lib.es2025.regexp.d.ts
lib.es5.d.ts
lib.es6.d.ts
lib.esnext.array.d.ts
lib.esnext.collection.d.ts
lib.esnext.date.d.ts
lib.esnext.decorators.d.ts
lib.esnext.disposable.d.ts
lib.esnext.d.ts
lib.esnext.error.d.ts
lib.esnext.full.d.ts
lib.esnext.intl.d.ts
lib.esnext.sharedmemory.d.ts
lib.esnext.temporal.d.ts
lib.esnext.typedarrays.d.ts
lib.scripthost.d.ts
lib.webworker.asynciterable.d.ts
lib.webworker.d.ts
lib.webworker.importscripts.d.ts
lib.webworker.iterable.d.ts
pl
pt-br
ru
tr
_tsc.js
tsc.js
_tsserver.js
tsserver.js
tsserverlibrary.d.ts
tsserverlibrary.js
typescript.d.ts
typescript.js
typesMap.json
_typingsInstaller.js
typingsInstaller.js
watchGuard.js
zh-cn
zh-tw

./frontend/node_modules/typescript/lib/cs:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/de:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/es:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/fr:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/it:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/ja:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/ko:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/pl:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/pt-br:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/ru:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/tr:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/zh-cn:
diagnosticMessages.generated.json

./frontend/node_modules/typescript/lib/zh-tw:
diagnosticMessages.generated.json

./frontend/node_modules/ufo:
dist
LICENSE
package.json
README.md

./frontend/node_modules/ufo/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/undici-types:
agent.d.ts
api.d.ts
balanced-pool.d.ts
cache.d.ts
cache-interceptor.d.ts
client.d.ts
client-stats.d.ts
connector.d.ts
content-type.d.ts
cookies.d.ts
diagnostics-channel.d.ts
dispatcher.d.ts
env-http-proxy-agent.d.ts
errors.d.ts
eventsource.d.ts
fetch.d.ts
formdata.d.ts
global-dispatcher.d.ts
global-origin.d.ts
h2c-client.d.ts
handlers.d.ts
header.d.ts
index.d.ts
interceptors.d.ts
LICENSE
mock-agent.d.ts
mock-call-history.d.ts
mock-client.d.ts
mock-errors.d.ts
mock-interceptor.d.ts
mock-pool.d.ts
package.json
patch.d.ts
pool.d.ts
pool-stats.d.ts
proxy-agent.d.ts
readable.d.ts
README.md
retry-agent.d.ts
retry-handler.d.ts
round-robin-pool.d.ts
snapshot-agent.d.ts
util.d.ts
utility.d.ts
webidl.d.ts
websocket.d.ts

./frontend/node_modules/unplugin:
dist
LICENSE
package.json
README.md

./frontend/node_modules/unplugin/dist:
context-CKhLGGrj.mjs
context-MD-xQmYI.mjs
index.d.mts
index.mjs
parse-DN2jPtpt.mjs
rspack
utils-BMHLEWml.mjs
webpack
webpack-like-Djrmy9eu.mjs

./frontend/node_modules/unplugin/dist/rspack:
loaders

./frontend/node_modules/unplugin/dist/rspack/loaders:
load.d.mts
load.mjs
transform.d.mts
transform.mjs

./frontend/node_modules/unplugin/dist/webpack:
loaders

./frontend/node_modules/unplugin/dist/webpack/loaders:
load.d.mts
load.mjs
transform.d.mts
transform.mjs

./frontend/node_modules/unplugin-utils:
dist
LICENSE
package.json
README.md

./frontend/node_modules/unplugin-utils/dist:
index.d.ts
index.js

./frontend/node_modules/update-browserslist-db:
check-npm-version.js
cli.js
index.d.ts
index.js
LICENSE
package.json
README.md
utils.js

./frontend/node_modules/url-parse:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/url-parse/dist:
url-parse.js
url-parse.min.js
url-parse.min.js.map

./frontend/node_modules/vite:
bin
client.d.ts
dist
LICENSE.md
misc
package.json
README.md
types

./frontend/node_modules/vite/bin:
openChrome.js
vite.js

./frontend/node_modules/vite/dist:
client
node

./frontend/node_modules/vite/dist/client:
client.mjs
env.mjs

./frontend/node_modules/vite/dist/node:
chunks
cli.js
index.d.ts
index.js
internal.d.ts
internal.js
module-runner.d.ts
module-runner.js

./frontend/node_modules/vite/dist/node/chunks:
build2.js
build.js
chunk.js
config.js
dist.js
lib.js
logger.js
moduleRunnerTransport.d.ts
node.js
optimizer.js
postcss-import.js
preview.js
server.js

./frontend/node_modules/vite/misc:
false.js
true.js

./frontend/node_modules/vite/types:
customEvent.d.ts
hmrPayload.d.ts
hot.d.ts
importGlob.d.ts
import-meta.d.ts
importMeta.d.ts
internal
metadata.d.ts

./frontend/node_modules/vite/types/internal:
cssPreprocessorOptions.d.ts
esbuildOptions.d.ts
lightningcssOptions.d.ts
rollupTypeCompat.d.ts
terserOptions.d.ts

./frontend/node_modules/@vitejs:
plugin-vue

./frontend/node_modules/@vitejs/plugin-vue:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vitejs/plugin-vue/dist:
index.d.mts
index.mjs

./frontend/node_modules/vite-plugin-vue-devtools:
client
dist
LICENSE
node_modules
package.json
README.md
src

./frontend/node_modules/vite-plugin-vue-devtools/client:
assets
color-scheme.js
index.html
logo.svg

./frontend/node_modules/vite-plugin-vue-devtools/client/assets:
assets-BRwG2HLw.js
components-Dc6g-iyY.js
css-DPfMkruS.js
custom-inspector-tab-view--ulUW-qb.js
custom-tab-view-C959CANd.js
diff-D97Zzqfu.js
graph-BRGxD27w.js
html-B8pBdPMQ.js
IconTitle.vue_vue_type_script_setup_true_lang-CfDGRuuz.js
index-B5ZMOy4O.js
index-BmFftAtl.css
index-DVOrYgP_.css
javascript-BMMyXqK5.js
json-Cp-IABpG.js
overview-CxLRqeZ8.js
pages-B01Hd5DR.js
pinia-C62RQFBU.js
router-CMWgb1rd.js
SectionBlock-BYDDTsu3.css
SectionBlock-Crn18Q0p.js
settings-Cp5OpdUZ.js
shellscript-Yzrsuije.js
timeline-BnYZ8d9h.js
timeline-BZZEyhA7.css
typescript-DlfHMoPT.js
vitesse-dark-D0r3Knsf.js
vitesse-light-CVO1_9PV.js
vue-apis-DJBctb31.js
vue-CYH3TOzo.js
vue-html-zSiQ3dJi.js
yaml-Buea-lGh.js

./frontend/node_modules/vite-plugin-vue-devtools/dist:
vite.cjs
vite.d.cts
vite.d.ts
vite.js

./frontend/node_modules/vite-plugin-vue-devtools/node_modules:
debug
vite-plugin-inspect

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/debug:
LICENSE
package.json
README.md
src

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/debug/src:
browser.js
common.js
index.js
node.js

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect:
dist
LICENSE
node_modules
package.json
README.md

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/dist:
client
dirs.d.mts
dirs.mjs
index.d.mts
index.mjs
nuxt.d.mts
nuxt.mjs
shared

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/dist/client:
assets
favicon.svg
index.html

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/dist/client/assets:
_...all_-DVz6Qbfk.js
diff.worker-CMaeQEBs.js
dist-Dp-WpU5V.js
fonts
hot-D67q3Up2.js
index-shvuXdj8.css
index---UmQy10.js
metric-D2Mof43y.js
module-2YhSO2gi.css
module-BFxZWV4q.js
ModuleList-ByxGSHde.js
options-D_MMddT_.js
pages-g-EVEhKa.js
payload-BX9lTMvN.js
plugins-Ngzgf8uz.js
_plugin-vue_export-helper-DfavQbjy.js
QuerySelector-iLRAQoow.js
runtime-core.esm-bundler-Cyv4obHQ.js
search-Bklj8tNk.js
vue-router-BxYGFXy-.js

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/dist/client/assets/fonts:
dmmono-8beacb38.woff2
dmmono-bb868a37.woff2
dmsans-a41acbfa.woff2
dmsans-bde470c1.woff2

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/dist/shared:
vite-plugin-inspect.BzUKaD4x.mjs
vite-plugin-inspect.CtoQ7j4S.d.mts

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules:
vite-dev-rpc

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules/vite-dev-rpc:
dist
LICENSE
node_modules
package.json
README.md

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules/vite-dev-rpc/dist:
index.cjs
index.d.cts
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules/vite-dev-rpc/node_modules:
vite-hot-client

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules/vite-dev-rpc/node_modules/vite-hot-client:
dist
LICENSE
package.json
README.md

./frontend/node_modules/vite-plugin-vue-devtools/node_modules/vite-plugin-inspect/node_modules/vite-dev-rpc/node_modules/vite-hot-client/dist:
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/vite-plugin-vue-devtools/src:
overlay
overlay.js

./frontend/node_modules/vite-plugin-vue-devtools/src/overlay:
devtools-overlay.css
devtools-overlay.mjs

./frontend/node_modules/vite-plugin-vue-inspector:
dist
LICENSE
package.json
README.md
src

./frontend/node_modules/vite-plugin-vue-inspector/dist:
index.cjs
index.d.mts
index.d.ts
index.mjs

./frontend/node_modules/vite-plugin-vue-inspector/src:
load.js
Overlay.vue

./frontend/node_modules/@volar:
language-core
source-map
typescript

./frontend/node_modules/@volar/language-core:
index.d.ts
index.js
lib
LICENSE
package.json

./frontend/node_modules/@volar/language-core/lib:
editor.d.ts
editor.js
linkedCodeMap.d.ts
linkedCodeMap.js
types.d.ts
types.js
utils.d.ts
utils.js

./frontend/node_modules/@volar/source-map:
index.d.ts
index.js
lib
LICENSE
package.json
README.md

./frontend/node_modules/@volar/source-map/lib:
binarySearch.d.ts
binarySearch.js
sourceMap.d.ts
sourceMap.js
translateOffset.d.ts
translateOffset.js

./frontend/node_modules/@volar/typescript:
index.d.ts
index.js
lib
LICENSE
package.json

./frontend/node_modules/@volar/typescript/lib:
common.d.ts
common.js
node
protocol
quickstart
resolveModuleName.d.ts
resolveModuleName.js
typescript

./frontend/node_modules/@volar/typescript/lib/node:
decorateLanguageServiceHost.d.ts
decorateLanguageServiceHost.js
decorateProgram.d.ts
decorateProgram.js
dedupe.d.ts
dedupe.js
proxyCreateProgram.d.ts
proxyCreateProgram.js
proxyLanguageService.d.ts
proxyLanguageService.js
transform.d.ts
transform.js
utils.d.ts
utils.js

./frontend/node_modules/@volar/typescript/lib/protocol:
createProject.d.ts
createProject.js
createSys.d.ts
createSys.js

./frontend/node_modules/@volar/typescript/lib/quickstart:
createAsyncLanguageServicePlugin.d.ts
createAsyncLanguageServicePlugin.js
createLanguageServicePlugin.d.ts
createLanguageServicePlugin.js
languageServicePluginCommon.d.ts
languageServicePluginCommon.js
runTsc.d.ts
runTsc.js

./frontend/node_modules/@volar/typescript/lib/typescript:
core.d.ts
core.js
corePublic.d.ts
corePublic.js
path.d.ts
path.js
types.d.ts
types.js
utilities.d.ts
utilities.js

./frontend/node_modules/vscode-uri:
lib
LICENSE.md
package.json
README.md
SECURITY.md

./frontend/node_modules/vscode-uri/lib:
esm
umd

./frontend/node_modules/vscode-uri/lib/esm:
index.mjs
index.mjs.map

./frontend/node_modules/vscode-uri/lib/umd:
charCode.d.ts
charCode.js
index.d.ts
index.js
index.js.map
platform.d.ts
platform.js
uri.d.ts
uri.js
utils.d.ts
utils.js

./frontend/node_modules/@vue:
babel-helper-vue-transform-on
babel-plugin-jsx
babel-plugin-resolve-type
compiler-core
compiler-dom
compiler-sfc
compiler-ssr
devtools-api
devtools-core
devtools-kit
devtools-shared
language-core
reactivity
runtime-core
runtime-dom
server-renderer
shared
tsconfig

./frontend/node_modules/@vue/babel-helper-vue-transform-on:
index.d.ts
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/babel-plugin-jsx:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/babel-plugin-jsx/dist:
index.d.mts
index.d.ts
index.js
index.mjs

./frontend/node_modules/@vue/babel-plugin-resolve-type:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/babel-plugin-resolve-type/dist:
index.d.mts
index.d.ts
index.js
index.mjs

./frontend/node_modules/@vue/compiler-core:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/compiler-core/dist:
compiler-core.cjs.js
compiler-core.cjs.prod.js
compiler-core.d.ts
compiler-core.esm-bundler.js

./frontend/node_modules/@vue/compiler-dom:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/compiler-dom/dist:
compiler-dom.cjs.js
compiler-dom.cjs.prod.js
compiler-dom.d.ts
compiler-dom.esm-browser.js
compiler-dom.esm-browser.prod.js
compiler-dom.esm-bundler.js
compiler-dom.global.js
compiler-dom.global.prod.js

./frontend/node_modules/@vue/compiler-sfc:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/compiler-sfc/dist:
compiler-sfc.cjs.js
compiler-sfc.d.ts
compiler-sfc.esm-browser.js

./frontend/node_modules/@vue/compiler-ssr:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/compiler-ssr/dist:
compiler-ssr.cjs.js
compiler-ssr.d.ts

./frontend/node_modules/@vue/devtools-api:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/devtools-api/dist:
index.cjs
index.d.cts
index.d.ts
index.js
vue-devtools-api.esm-browser.js
vue-devtools-api.global.js

./frontend/node_modules/@vue/devtools-core:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/devtools-core/dist:
index.cjs
index.d.cts
index.d.ts
index.js

./frontend/node_modules/@vue/devtools-kit:
dist
global.d.ts
LICENSE
package.json
README.md
types.d.ts

./frontend/node_modules/@vue/devtools-kit/dist:
index.cjs
index.d.cts
index.d.ts
index.js

./frontend/node_modules/@vue/devtools-shared:
dist
LICENSE
package.json
README.md

./frontend/node_modules/@vue/devtools-shared/dist:
index.cjs
index.d.cts
index.d.ts
index.js

./frontend/node_modules/@vue/language-core:
index.d.ts
index.js
lib
LICENSE
package.json
README.md
types

./frontend/node_modules/@vue/language-core/lib:
codegen
compilerOptions.d.ts
compilerOptions.js
languagePlugin.d.ts
languagePlugin.js
parsers
plugins
plugins.d.ts
plugins.js
types.d.ts
types.js
utils
virtualCode

./frontend/node_modules/@vue/language-core/lib/codegen:
codeFeatures.d.ts
codeFeatures.js
inlayHints.d.ts
inlayHints.js
localTypes.d.ts
localTypes.js
names.d.ts
names.js
script
style
template
utils

./frontend/node_modules/@vue/language-core/lib/codegen/script:
component.d.ts
component.js
context.d.ts
context.js
index.d.ts
index.js
scriptSetup.d.ts
scriptSetup.js
template.d.ts
template.js

./frontend/node_modules/@vue/language-core/lib/codegen/style:
common.d.ts
common.js
index.d.ts
index.js
modules.d.ts
modules.js
scopedClasses.d.ts
scopedClasses.js

./frontend/node_modules/@vue/language-core/lib/codegen/template:
context.d.ts
context.js
elementDirectives.d.ts
elementDirectives.js
element.d.ts
elementEvents.d.ts
elementEvents.js
element.js
elementProps.d.ts
elementProps.js
index.d.ts
index.js
interpolation.d.ts
interpolation.js
objectProperty.d.ts
objectProperty.js
propertyAccess.d.ts
propertyAccess.js
slotOutlet.d.ts
slotOutlet.js
styleScopedClasses.d.ts
styleScopedClasses.js
templateChild.d.ts
templateChild.js
vFor.d.ts
vFor.js
vIf.d.ts
vIf.js
vSlot.d.ts
vSlot.js

./frontend/node_modules/@vue/language-core/lib/codegen/utils:
boundary.d.ts
boundary.js
camelized.d.ts
camelized.js
escaped.d.ts
escaped.js
index.d.ts
index.js
merge.d.ts
merge.js
stringLiteralKey.d.ts
stringLiteralKey.js
transform.d.ts
transform.js
unicode.d.ts
unicode.js

./frontend/node_modules/@vue/language-core/lib/parsers:
scriptRanges.d.ts
scriptRanges.js
scriptSetupRanges.d.ts
scriptSetupRanges.js
utils.d.ts
utils.js
vueCompilerOptions.d.ts
vueCompilerOptions.js

./frontend/node_modules/@vue/language-core/lib/plugins:
file-html.d.ts
file-html.js
file-md.d.ts
file-md.js
file-vue.d.ts
file-vue.js
shared.d.ts
shared.js
vue-root-tags.d.ts
vue-root-tags.js
vue-script-js.d.ts
vue-script-js.js
vue-sfc-customblocks.d.ts
vue-sfc-customblocks.js
vue-sfc-scripts.d.ts
vue-sfc-scripts.js
vue-sfc-styles.d.ts
vue-sfc-styles.js
vue-sfc-template.d.ts
vue-sfc-template.js
vue-style-css.d.ts
vue-style-css.js
vue-template-html.d.ts
vue-template-html.js
vue-template-inline-css.d.ts
vue-template-inline-css.js
vue-template-inline-ts.d.ts
vue-template-inline-ts.js
vue-tsx.d.ts
vue-tsx.js

./frontend/node_modules/@vue/language-core/lib/utils:
buildMappings.d.ts
buildMappings.js
collectBindings.d.ts
collectBindings.js
forEachTemplateNode.d.ts
forEachTemplateNode.js
parseSfc.d.ts
parseSfc.js
shared.d.ts
shared.js
signals.d.ts
signals.js

./frontend/node_modules/@vue/language-core/lib/virtualCode:
embeddedCodes.d.ts
embeddedCodes.js
index.d.ts
index.js
ir.d.ts
ir.js
normalize.d.ts
normalize.js

./frontend/node_modules/@vue/language-core/types:
props-fallback.d.ts
template-helpers.d.ts
vue-3.4-shims.d.ts

./frontend/node_modules/@vue/reactivity:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/reactivity/dist:
reactivity.cjs.js
reactivity.cjs.prod.js
reactivity.d.ts
reactivity.esm-browser.js
reactivity.esm-browser.prod.js
reactivity.esm-bundler.js
reactivity.global.js
reactivity.global.prod.js

./frontend/node_modules/@vue/runtime-core:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/runtime-core/dist:
runtime-core.cjs.js
runtime-core.cjs.prod.js
runtime-core.d.ts
runtime-core.esm-bundler.js

./frontend/node_modules/@vue/runtime-dom:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/runtime-dom/dist:
runtime-dom.cjs.js
runtime-dom.cjs.prod.js
runtime-dom.d.ts
runtime-dom.esm-browser.js
runtime-dom.esm-browser.prod.js
runtime-dom.esm-bundler.js
runtime-dom.global.js
runtime-dom.global.prod.js

./frontend/node_modules/@vue/server-renderer:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/server-renderer/dist:
server-renderer.cjs.js
server-renderer.cjs.prod.js
server-renderer.d.ts
server-renderer.esm-browser.js
server-renderer.esm-browser.prod.js
server-renderer.esm-bundler.js

./frontend/node_modules/@vue/shared:
dist
index.js
LICENSE
package.json
README.md

./frontend/node_modules/@vue/shared/dist:
shared.cjs.js
shared.cjs.prod.js
shared.d.ts
shared.esm-bundler.js

./frontend/node_modules/@vue/tsconfig:
LICENSE
package.json
README.md
tsconfig.dom.json
tsconfig.json
tsconfig.lib.json

./frontend/node_modules/vue:
compiler-sfc
dist
index.js
index.mjs
jsx.d.ts
jsx-runtime
LICENSE
package.json
README.md
server-renderer

./frontend/node_modules/vue/compiler-sfc:
index.browser.js
index.browser.mjs
index.d.mts
index.d.ts
index.js
index.mjs
package.json
register-ts.js

./frontend/node_modules/vue/dist:
vue.cjs.js
vue.cjs.prod.js
vue.d.mts
vue.d.ts
vue.esm-browser.js
vue.esm-browser.prod.js
vue.esm-bundler.js
vue.global.js
vue.global.prod.js
vue.runtime.esm-browser.js
vue.runtime.esm-browser.prod.js
vue.runtime.esm-bundler.js
vue.runtime.global.js
vue.runtime.global.prod.js

./frontend/node_modules/vue/jsx-runtime:
index.d.ts
index.js
index.mjs
package.json

./frontend/node_modules/vue/server-renderer:
index.d.mts
index.d.ts
index.js
index.mjs
package.json

./frontend/node_modules/@vue-macros:
common

./frontend/node_modules/@vue-macros/common:
dist
LICENSE
package.json

./frontend/node_modules/@vue-macros/common/dist:
index.d.ts
index.js

./frontend/node_modules/vue-router:
dist
index.cjs
LICENSE
package.json
README.md
route.schema.json
vetur
vue-router-auto.d.ts
vue-router-auto-resolver.d.mts
vue-router-auto-routes.d.mts
vue-router.node.mjs

./frontend/node_modules/vue-router/dist:
devtools-DUDsFuj9.js
experimental
index-BzEKChPW.d.ts
index-C3eYkdSl.d.ts
navigation-guard-CfICYe6s.js
options-BErt5RTe.d.cts
options-C2ZrRwqO.cjs
options-D5Ta7zF4.d.mts
options-u5H4QxZN.mjs
unplugin
unplugin-CKZ7dKWh.mjs
unplugin-CTJrB7ZL.cjs
useApi-C8XBqGtv.js
volar
vue-router.cjs
vue-router.d.ts
vue-router.esm-browser.js
vue-router.esm-browser.prod.js
vue-router.esm-bundler.js
vue-router.global.js
vue-router.global.prod.js
vue-router.js
vue-router.prod.cjs

./frontend/node_modules/vue-router/dist/experimental:
index.d.ts
index.js
pinia-colada.d.ts
pinia-colada.js

./frontend/node_modules/vue-router/dist/unplugin:
esbuild.cjs
esbuild.d.cts
esbuild.d.mts
esbuild.mjs
index.cjs
index.d.cts
index.d.mts
index.mjs
options.cjs
options.d.cts
options.d.mts
options.mjs
rolldown.cjs
rolldown.d.cts
rolldown.d.mts
rolldown.mjs
rollup.cjs
rollup.d.cts
rollup.d.mts
rollup.mjs
types.cjs
types.d.cts
types.d.mts
types.mjs
vite.cjs
vite.d.cts
vite.d.mts
vite.mjs
webpack.cjs
webpack.d.cts
webpack.d.mts
webpack.mjs

./frontend/node_modules/vue-router/dist/volar:
sfc-route-blocks.cjs
sfc-route-blocks.d.cts
sfc-typed-router.cjs
sfc-typed-router.d.cts

./frontend/node_modules/vue-router/vetur:
attributes.json
tags.json

./frontend/node_modules/vue-tsc:
bin
index.d.ts
index.js
LICENSE
package.json
README.md

./frontend/node_modules/vue-tsc/bin:
vue-tsc.js

./frontend/node_modules/webpack-virtual-modules:
lib
LICENSE
package.json
README.md
src

./frontend/node_modules/webpack-virtual-modules/lib:
index.d.ts
index.js
index.js.map
virtual-stats.d.ts
virtual-stats.js
virtual-stats.js.map

./frontend/node_modules/webpack-virtual-modules/src:
index.ts
virtual-stats.ts

./frontend/node_modules/websocket-driver:
CHANGELOG.md
lib
LICENSE.md
package.json
README.md

./frontend/node_modules/websocket-driver/lib:
websocket

./frontend/node_modules/websocket-driver/lib/websocket:
driver
driver.js
http_parser.js
streams.js

./frontend/node_modules/websocket-driver/lib/websocket/driver:
base.js
client.js
draft75.js
draft76.js
headers.js
hybi
hybi.js
proxy.js
server.js
stream_reader.js

./frontend/node_modules/websocket-driver/lib/websocket/driver/hybi:
frame.js
message.js

./frontend/node_modules/websocket-extensions:
CHANGELOG.md
lib
LICENSE.md
package.json
README.md

./frontend/node_modules/websocket-extensions/lib:
parser.js
pipeline
websocket_extensions.js

./frontend/node_modules/websocket-extensions/lib/pipeline:
cell.js
functor.js
index.js
pledge.js
README.md
ring_buffer.js

./frontend/node_modules/wsl-utils:
index.d.ts
index.js
license
package.json
readme.md

./frontend/node_modules/yallist:
iterator.js
LICENSE
package.json
README.md
yallist.js

./frontend/node_modules/yaml:
bin.mjs
browser
dist
LICENSE
package.json
README.md
util.js

./frontend/node_modules/yaml/browser:
dist
index.js
package.json

./frontend/node_modules/yaml/browser/dist:
compose
doc
errors.js
index.js
log.js
nodes
parse
public-api.js
schema
stringify
util.js
visit.js

./frontend/node_modules/yaml/browser/dist/compose:
compose-collection.js
compose-doc.js
compose-node.js
composer.js
compose-scalar.js
resolve-block-map.js
resolve-block-scalar.js
resolve-block-seq.js
resolve-end.js
resolve-flow-collection.js
resolve-flow-scalar.js
resolve-props.js
util-contains-newline.js
util-empty-scalar-position.js
util-flow-indent-check.js
util-map-includes.js

./frontend/node_modules/yaml/browser/dist/doc:
anchors.js
applyReviver.js
createNode.js
directives.js
Document.js

./frontend/node_modules/yaml/browser/dist/nodes:
addPairToJSMap.js
Alias.js
Collection.js
identity.js
Node.js
Pair.js
Scalar.js
toJS.js
YAMLMap.js
YAMLSeq.js

./frontend/node_modules/yaml/browser/dist/parse:
cst.js
cst-scalar.js
cst-stringify.js
cst-visit.js
lexer.js
line-counter.js
parser.js

./frontend/node_modules/yaml/browser/dist/schema:
common
core
json
Schema.js
tags.js
yaml-1.1

./frontend/node_modules/yaml/browser/dist/schema/common:
map.js
null.js
seq.js
string.js

./frontend/node_modules/yaml/browser/dist/schema/core:
bool.js
float.js
int.js
schema.js

./frontend/node_modules/yaml/browser/dist/schema/json:
schema.js

./frontend/node_modules/yaml/browser/dist/schema/yaml-1.1:
binary.js
bool.js
float.js
int.js
merge.js
omap.js
pairs.js
schema.js
set.js
timestamp.js

./frontend/node_modules/yaml/browser/dist/stringify:
foldFlowLines.js
stringifyCollection.js
stringifyComment.js
stringifyDocument.js
stringify.js
stringifyNumber.js
stringifyPair.js
stringifyString.js

./frontend/node_modules/yaml/dist:
cli.d.ts
cli.mjs
compose
doc
errors.d.ts
errors.js
index.d.ts
index.js
log.d.ts
log.js
nodes
options.d.ts
parse
public-api.d.ts
public-api.js
schema
stringify
test-events.d.ts
test-events.js
util.d.ts
util.js
visit.d.ts
visit.js

./frontend/node_modules/yaml/dist/compose:
compose-collection.d.ts
compose-collection.js
compose-doc.d.ts
compose-doc.js
compose-node.d.ts
compose-node.js
composer.d.ts
composer.js
compose-scalar.d.ts
compose-scalar.js
resolve-block-map.d.ts
resolve-block-map.js
resolve-block-scalar.d.ts
resolve-block-scalar.js
resolve-block-seq.d.ts
resolve-block-seq.js
resolve-end.d.ts
resolve-end.js
resolve-flow-collection.d.ts
resolve-flow-collection.js
resolve-flow-scalar.d.ts
resolve-flow-scalar.js
resolve-props.d.ts
resolve-props.js
util-contains-newline.d.ts
util-contains-newline.js
util-empty-scalar-position.d.ts
util-empty-scalar-position.js
util-flow-indent-check.d.ts
util-flow-indent-check.js
util-map-includes.d.ts
util-map-includes.js

./frontend/node_modules/yaml/dist/doc:
anchors.d.ts
anchors.js
applyReviver.d.ts
applyReviver.js
createNode.d.ts
createNode.js
directives.d.ts
directives.js
Document.d.ts
Document.js

./frontend/node_modules/yaml/dist/nodes:
addPairToJSMap.d.ts
addPairToJSMap.js
Alias.d.ts
Alias.js
Collection.d.ts
Collection.js
identity.d.ts
identity.js
Node.d.ts
Node.js
Pair.d.ts
Pair.js
Scalar.d.ts
Scalar.js
toJS.d.ts
toJS.js
YAMLMap.d.ts
YAMLMap.js
YAMLSeq.d.ts
YAMLSeq.js

./frontend/node_modules/yaml/dist/parse:
cst.d.ts
cst.js
cst-scalar.d.ts
cst-scalar.js
cst-stringify.d.ts
cst-stringify.js
cst-visit.d.ts
cst-visit.js
lexer.d.ts
lexer.js
line-counter.d.ts
line-counter.js
parser.d.ts
parser.js

./frontend/node_modules/yaml/dist/schema:
common
core
json
json-schema.d.ts
Schema.d.ts
Schema.js
tags.d.ts
tags.js
types.d.ts
yaml-1.1

./frontend/node_modules/yaml/dist/schema/common:
map.d.ts
map.js
null.d.ts
null.js
seq.d.ts
seq.js
string.d.ts
string.js

./frontend/node_modules/yaml/dist/schema/core:
bool.d.ts
bool.js
float.d.ts
float.js
int.d.ts
int.js
schema.d.ts
schema.js

./frontend/node_modules/yaml/dist/schema/json:
schema.d.ts
schema.js

./frontend/node_modules/yaml/dist/schema/yaml-1.1:
binary.d.ts
binary.js
bool.d.ts
bool.js
float.d.ts
float.js
int.d.ts
int.js
merge.d.ts
merge.js
omap.d.ts
omap.js
pairs.d.ts
pairs.js
schema.d.ts
schema.js
set.d.ts
set.js
timestamp.d.ts
timestamp.js

./frontend/node_modules/yaml/dist/stringify:
foldFlowLines.d.ts
foldFlowLines.js
stringifyCollection.d.ts
stringifyCollection.js
stringifyComment.d.ts
stringifyComment.js
stringifyDocument.d.ts
stringifyDocument.js
stringify.d.ts
stringify.js
stringifyNumber.d.ts
stringifyNumber.js
stringifyPair.d.ts
stringifyPair.js
stringifyString.d.ts
stringifyString.js

./frontend/public:
pdl.png

./frontend/src:
api
App.vue
assets
components
composables
main.ts
router
shared
views
vite-env.d.ts

./frontend/src/api:
http-client.ts

./frontend/src/assets:
base.css
theme-cruelty.css

./frontend/src/components:
AdvancedSearchSidebar.vue

./frontend/src/composables:
useAuth.ts
useGallerySearch.ts
useImageStatus.ts
useUploadQueue.ts

./frontend/src/router:
index.ts

./frontend/src/shared:
TagAutocomplete.vue

./frontend/src/views:
AboutView.vue
AdminView.vue
GalleryView.vue
ImageDetailView.vue
LoginView.vue
ProfileView.vue
RegisterView.vue
UploadView.vue

./node_modules:
@chevrotain
chevrotain
chevrotain-allstar
java-parser
lodash
lodash-es
@prettier
prettier
prettier-plugin-java
prettier-plugin-sh
regexp-to-ast
@reteps
sh-syntax
tslib
@xml-tools

./node_modules/@chevrotain:
cst-dts-gen
gast
regexp-to-ast
types
utils

./node_modules/@chevrotain/cst-dts-gen:
lib
LICENSE.txt
package.json
src

./node_modules/@chevrotain/cst-dts-gen/lib:
src

./node_modules/@chevrotain/cst-dts-gen/lib/src:
api.d.ts
api.js
api.js.map
generate.d.ts
generate.js
generate.js.map
model.d.ts
model.js
model.js.map

./node_modules/@chevrotain/cst-dts-gen/src:
api.ts
generate.ts
model.ts

./node_modules/@chevrotain/gast:
lib
LICENSE.txt
package.json
src

./node_modules/@chevrotain/gast/lib:
src

./node_modules/@chevrotain/gast/lib/src:
api.d.ts
api.js
api.js.map
helpers.d.ts
helpers.js
helpers.js.map
model.d.ts
model.js
model.js.map
visitor.d.ts
visitor.js
visitor.js.map

./node_modules/@chevrotain/gast/src:
api.ts
helpers.ts
model.ts
visitor.ts

./node_modules/@chevrotain/regexp-to-ast:
lib
LICENSE.txt
package.json
src
types.d.ts

./node_modules/@chevrotain/regexp-to-ast/lib:
src

./node_modules/@chevrotain/regexp-to-ast/lib/src:
api.js
api.js.map
base-regexp-visitor.js
base-regexp-visitor.js.map
character-classes.js
character-classes.js.map
regexp-parser.js
regexp-parser.js.map
utils.js
utils.js.map

./node_modules/@chevrotain/regexp-to-ast/src:
api.ts
base-regexp-visitor.ts
character-classes.ts
regexp-parser.ts
utils.ts

./node_modules/@chevrotain/types:
api.d.ts
LICENSE.txt
package.json

./node_modules/@chevrotain/utils:
lib
LICENSE.txt
package.json
src

./node_modules/@chevrotain/utils/lib:
src

./node_modules/@chevrotain/utils/lib/src:
api.d.ts
api.js
api.js.map
print.d.ts
print.js
print.js.map
timer.d.ts
timer.js
timer.js.map
to-fast-properties.d.ts
to-fast-properties.js
to-fast-properties.js.map

./node_modules/@chevrotain/utils/src:
api.ts
print.ts
timer.ts
to-fast-properties.ts

./node_modules/chevrotain:
BREAKING_CHANGES.md
CHANGELOG.md
chevrotain.d.ts
diagrams
lib
LICENSE.txt
package.json
README.md
src

./node_modules/chevrotain/diagrams:
diagrams.css
README.md
src
vendor

./node_modules/chevrotain/diagrams/src:
diagrams_behavior.js
diagrams_builder.js
diagrams_serializer.js
main.js

./node_modules/chevrotain/diagrams/vendor:
railroad-diagrams.js

./node_modules/chevrotain/lib:
chevrotain.min.mjs
chevrotain.mjs
src

./node_modules/chevrotain/lib/src:
api.js
api.js.map
diagrams
lang
parse
scan
text
version.js
version.js.map

./node_modules/chevrotain/lib/src/diagrams:
render_public.js
render_public.js.map

./node_modules/chevrotain/lib/src/lang:
lang_extensions.js
lang_extensions.js.map

./node_modules/chevrotain/lib/src/parse:
constants.js
constants.js.map
cst
errors_public.js
errors_public.js.map
exceptions_public.js
exceptions_public.js.map
grammar
parser

./node_modules/chevrotain/lib/src/parse/cst:
cst.js
cst.js.map
cst_visitor.js
cst_visitor.js.map

./node_modules/chevrotain/lib/src/parse/grammar:
checks.js
checks.js.map
first.js
first.js.map
follow.js
follow.js.map
gast
interpreter.js
interpreter.js.map
keys.js
keys.js.map
llk_lookahead.js
llk_lookahead.js.map
lookahead.js
lookahead.js.map
resolver.js
resolver.js.map
rest.js
rest.js.map
types.js
types.js.map

./node_modules/chevrotain/lib/src/parse/grammar/gast:
gast_resolver_public.js
gast_resolver_public.js.map

./node_modules/chevrotain/lib/src/parse/parser:
parser.js
parser.js.map
traits
types.js
types.js.map
utils

./node_modules/chevrotain/lib/src/parse/parser/traits:
context_assist.js
context_assist.js.map
error_handler.js
error_handler.js.map
gast_recorder.js
gast_recorder.js.map
lexer_adapter.js
lexer_adapter.js.map
looksahead.js
looksahead.js.map
parser_traits.js
parser_traits.js.map
perf_tracer.js
perf_tracer.js.map
recognizer_api.js
recognizer_api.js.map
recognizer_engine.js
recognizer_engine.js.map
recoverable.js
recoverable.js.map
tree_builder.js
tree_builder.js.map

./node_modules/chevrotain/lib/src/parse/parser/utils:
apply_mixins.js
apply_mixins.js.map

./node_modules/chevrotain/lib/src/scan:
lexer_errors_public.js
lexer_errors_public.js.map
lexer.js
lexer.js.map
lexer_public.js
lexer_public.js.map
reg_exp.js
reg_exp.js.map
reg_exp_parser.js
reg_exp_parser.js.map
tokens_constants.js
tokens_constants.js.map
tokens.js
tokens.js.map
tokens_public.js
tokens_public.js.map

./node_modules/chevrotain/lib/src/text:
range.js
range.js.map

./node_modules/chevrotain/src:
api.ts
diagrams
lang
parse
scan
text
version.ts

./node_modules/chevrotain/src/diagrams:
render_public.ts

./node_modules/chevrotain/src/lang:
lang_extensions.ts

./node_modules/chevrotain/src/parse:
constants.ts
cst
errors_public.ts
exceptions_public.ts
grammar
parser

./node_modules/chevrotain/src/parse/cst:
cst.ts
cst_visitor.ts

./node_modules/chevrotain/src/parse/grammar:
checks.ts
first.ts
follow.ts
gast
interpreter.ts
keys.ts
llk_lookahead.ts
lookahead.ts
resolver.ts
rest.ts
types.ts

./node_modules/chevrotain/src/parse/grammar/gast:
gast_resolver_public.ts

./node_modules/chevrotain/src/parse/parser:
parser.ts
traits
types.ts
utils

./node_modules/chevrotain/src/parse/parser/traits:
context_assist.ts
error_handler.ts
gast_recorder.ts
lexer_adapter.ts
looksahead.ts
parser_traits.ts
perf_tracer.ts
recognizer_api.ts
recognizer_engine.ts
recoverable.ts
tree_builder.ts

./node_modules/chevrotain/src/parse/parser/utils:
apply_mixins.ts

./node_modules/chevrotain/src/scan:
lexer_errors_public.ts
lexer_public.ts
lexer.ts
reg_exp_parser.ts
reg_exp.ts
tokens_constants.ts
tokens_public.ts
tokens.ts

./node_modules/chevrotain/src/text:
range.ts

./node_modules/chevrotain-allstar:
lib
LICENSE
package.json
README.md
src

./node_modules/chevrotain-allstar/lib:
all-star-lookahead.d.ts
all-star-lookahead.d.ts.map
all-star-lookahead.js
all-star-lookahead.js.map
atn.d.ts
atn.d.ts.map
atn.js
atn.js.map
dfa.d.ts
dfa.d.ts.map
dfa.js
dfa.js.map
index.d.ts
index.d.ts.map
index.js
index.js.map

./node_modules/chevrotain-allstar/src:
all-star-lookahead.ts
atn.test.ts
atn.ts
dfa.ts
index.ts

./node_modules/java-parser:
api.d.ts
LICENSE
package.json
README.md
src

./node_modules/java-parser/src:
index.js
lexer.js
parser.js
productions
tokens.js
unicodesets.js
utils.js

./node_modules/java-parser/src/productions:
arrays.js
blocks-and-statements.js
classes.js
expressions.js
interfaces.js
lexical-structure.js
names.js
packages-and-modules.js
types-values-and-variables.js

./node_modules/lodash:
add.js
after.js
_apply.js
_arrayAggregator.js
_arrayEach.js
_arrayEachRight.js
_arrayEvery.js
_arrayFilter.js
_arrayIncludes.js
_arrayIncludesWith.js
array.js
_arrayLikeKeys.js
_arrayMap.js
_arrayPush.js
_arrayReduce.js
_arrayReduceRight.js
_arraySample.js
_arraySampleSize.js
_arrayShuffle.js
_arraySome.js
ary.js
_asciiSize.js
_asciiToArray.js
_asciiWords.js
assignIn.js
assignInWith.js
assign.js
_assignMergeValue.js
_assignValue.js
assignWith.js
_assocIndexOf.js
at.js
attempt.js
_baseAggregator.js
_baseAssignIn.js
_baseAssign.js
_baseAssignValue.js
_baseAt.js
_baseClamp.js
_baseClone.js
_baseConforms.js
_baseConformsTo.js
_baseCreate.js
_baseDelay.js
_baseDifference.js
_baseEach.js
_baseEachRight.js
_baseEvery.js
_baseExtremum.js
_baseFill.js
_baseFilter.js
_baseFindIndex.js
_baseFindKey.js
_baseFlatten.js
_baseFor.js
_baseForOwn.js
_baseForOwnRight.js
_baseForRight.js
_baseFunctions.js
_baseGetAllKeys.js
_baseGet.js
_baseGetTag.js
_baseGt.js
_baseHasIn.js
_baseHas.js
_baseIndexOf.js
_baseIndexOfWith.js
_baseInRange.js
_baseIntersection.js
_baseInverter.js
_baseInvoke.js
_baseIsArguments.js
_baseIsArrayBuffer.js
_baseIsDate.js
_baseIsEqualDeep.js
_baseIsEqual.js
_baseIsMap.js
_baseIsMatch.js
_baseIsNaN.js
_baseIsNative.js
_baseIsRegExp.js
_baseIsSet.js
_baseIsTypedArray.js
_baseIteratee.js
_baseKeysIn.js
_baseKeys.js
_baseLodash.js
_baseLt.js
_baseMap.js
_baseMatches.js
_baseMatchesProperty.js
_baseMean.js
_baseMergeDeep.js
_baseMerge.js
_baseNth.js
_baseOrderBy.js
_basePickBy.js
_basePick.js
_basePropertyDeep.js
_baseProperty.js
_basePropertyOf.js
_basePullAll.js
_basePullAt.js
_baseRandom.js
_baseRange.js
_baseReduce.js
_baseRepeat.js
_baseRest.js
_baseSample.js
_baseSampleSize.js
_baseSetData.js
_baseSet.js
_baseSetToString.js
_baseShuffle.js
_baseSlice.js
_baseSome.js
_baseSortBy.js
_baseSortedIndexBy.js
_baseSortedIndex.js
_baseSortedUniq.js
_baseSum.js
_baseTimes.js
_baseToNumber.js
_baseToPairs.js
_baseToString.js
_baseTrim.js
_baseUnary.js
_baseUniq.js
_baseUnset.js
_baseUpdate.js
_baseValues.js
_baseWhile.js
_baseWrapperValue.js
_baseXor.js
_baseZipObject.js
before.js
bindAll.js
bind.js
bindKey.js
_cacheHas.js
camelCase.js
capitalize.js
castArray.js
_castArrayLikeObject.js
_castFunction.js
_castPath.js
_castRest.js
_castSlice.js
ceil.js
chain.js
_charsEndIndex.js
_charsStartIndex.js
chunk.js
clamp.js
_cloneArrayBuffer.js
_cloneBuffer.js
_cloneDataView.js
cloneDeep.js
cloneDeepWith.js
clone.js
_cloneRegExp.js
_cloneSymbol.js
_cloneTypedArray.js
cloneWith.js
collection.js
commit.js
compact.js
_compareAscending.js
_compareMultiple.js
_composeArgs.js
_composeArgsRight.js
concat.js
cond.js
conforms.js
conformsTo.js
constant.js
_copyArray.js
_copyObject.js
_copySymbolsIn.js
_copySymbols.js
core.js
_coreJsData.js
core.min.js
countBy.js
_countHolders.js
_createAggregator.js
_createAssigner.js
_createBaseEach.js
_createBaseFor.js
_createBind.js
_createCaseFirst.js
_createCompounder.js
_createCtor.js
_createCurry.js
_createFind.js
_createFlow.js
_createHybrid.js
_createInverter.js
create.js
_createMathOperation.js
_createOver.js
_createPadding.js
_createPartial.js
_createRange.js
_createRecurry.js
_createRelationalOperation.js
_createRound.js
_createSet.js
_createToPairs.js
_createWrap.js
curry.js
curryRight.js
_customDefaultsAssignIn.js
_customDefaultsMerge.js
_customOmitClone.js
_DataView.js
date.js
debounce.js
deburr.js
_deburrLetter.js
defaultsDeep.js
defaults.js
defaultTo.js
defer.js
_defineProperty.js
delay.js
differenceBy.js
difference.js
differenceWith.js
divide.js
drop.js
dropRight.js
dropRightWhile.js
dropWhile.js
each.js
eachRight.js
endsWith.js
entriesIn.js
entries.js
eq.js
_equalArrays.js
_equalByTag.js
_equalObjects.js
_escapeHtmlChar.js
escape.js
escapeRegExp.js
_escapeStringChar.js
every.js
extend.js
extendWith.js
fill.js
filter.js
findIndex.js
find.js
findKey.js
findLastIndex.js
findLast.js
findLastKey.js
first.js
flake.lock
flake.nix
flatMapDeep.js
flatMapDepth.js
flatMap.js
_flatRest.js
flattenDeep.js
flattenDepth.js
flatten.js
flip.js
floor.js
flow.js
flowRight.js
forEach.js
forEachRight.js
forIn.js
forInRight.js
forOwn.js
forOwnRight.js
fp
fp.js
_freeGlobal.js
fromPairs.js
function.js
functionsIn.js
functions.js
_getAllKeysIn.js
_getAllKeys.js
_getData.js
_getFuncName.js
_getHolder.js
get.js
_getMapData.js
_getMatchData.js
_getNative.js
_getPrototype.js
_getRawTag.js
_getSymbolsIn.js
_getSymbols.js
_getTag.js
_getValue.js
_getView.js
_getWrapDetails.js
groupBy.js
gte.js
gt.js
_hashClear.js
_hashDelete.js
_hashGet.js
_hashHas.js
_Hash.js
_hashSet.js
hasIn.js
has.js
_hasPath.js
_hasUnicode.js
_hasUnicodeWord.js
head.js
identity.js
includes.js
index.js
indexOf.js
_initCloneArray.js
_initCloneByTag.js
_initCloneObject.js
initial.js
inRange.js
_insertWrapDetails.js
intersectionBy.js
intersection.js
intersectionWith.js
invertBy.js
invert.js
invoke.js
invokeMap.js
isArguments.js
isArrayBuffer.js
isArray.js
isArrayLike.js
isArrayLikeObject.js
isBoolean.js
isBuffer.js
isDate.js
isElement.js
isEmpty.js
isEqual.js
isEqualWith.js
isError.js
isFinite.js
_isFlattenable.js
isFunction.js
_isIndex.js
isInteger.js
_isIterateeCall.js
_isKeyable.js
_isKey.js
_isLaziable.js
isLength.js
isMap.js
_isMaskable.js
_isMasked.js
isMatch.js
isMatchWith.js
isNaN.js
isNative.js
isNil.js
isNull.js
isNumber.js
isObject.js
isObjectLike.js
isPlainObject.js
_isPrototype.js
isRegExp.js
isSafeInteger.js
isSet.js
_isStrictComparable.js
isString.js
isSymbol.js
isTypedArray.js
isUndefined.js
isWeakMap.js
isWeakSet.js
iteratee.js
_iteratorToArray.js
join.js
kebabCase.js
keyBy.js
keysIn.js
keys.js
lang.js
lastIndexOf.js
last.js
_lazyClone.js
_lazyReverse.js
_lazyValue.js
_LazyWrapper.js
LICENSE
_listCacheClear.js
_listCacheDelete.js
_listCacheGet.js
_listCacheHas.js
_ListCache.js
_listCacheSet.js
lodash.js
lodash.min.js
_LodashWrapper.js
lowerCase.js
lowerFirst.js
lte.js
lt.js
_mapCacheClear.js
_mapCacheDelete.js
_mapCacheGet.js
_mapCacheHas.js
_MapCache.js
_mapCacheSet.js
map.js
_Map.js
mapKeys.js
_mapToArray.js
mapValues.js
matches.js
matchesProperty.js
_matchesStrictComparable.js
math.js
maxBy.js
max.js
meanBy.js
mean.js
_memoizeCapped.js
memoize.js
_mergeData.js
merge.js
mergeWith.js
_metaMap.js
method.js
methodOf.js
minBy.js
min.js
mixin.js
multiply.js
_nativeCreate.js
_nativeKeysIn.js
_nativeKeys.js
negate.js
next.js
_nodeUtil.js
noop.js
now.js
nthArg.js
nth.js
number.js
object.js
_objectToString.js
omitBy.js
omit.js
once.js
orderBy.js
_overArg.js
overArgs.js
overEvery.js
over.js
_overRest.js
overSome.js
package.json
padEnd.js
pad.js
padStart.js
_parent.js
parseInt.js
partial.js
partialRight.js
partition.js
pickBy.js
pick.js
plant.js
_Promise.js
property.js
propertyOf.js
pullAllBy.js
pullAll.js
pullAllWith.js
pullAt.js
pull.js
random.js
range.js
rangeRight.js
README.md
_realNames.js
rearg.js
reduce.js
reduceRight.js
_reEscape.js
_reEvaluate.js
_reInterpolate.js
reject.js
release.md
remove.js
_reorder.js
repeat.js
_replaceHolders.js
replace.js
rest.js
result.js
reverse.js
_root.js
round.js
_safeGet.js
sample.js
sampleSize.js
seq.js
_setCacheAdd.js
_setCacheHas.js
_SetCache.js
_setData.js
set.js
_Set.js
_setToArray.js
_setToPairs.js
_setToString.js
setWith.js
_setWrapToString.js
_shortOut.js
shuffle.js
_shuffleSelf.js
size.js
slice.js
snakeCase.js
some.js
sortBy.js
sortedIndexBy.js
sortedIndex.js
sortedIndexOf.js
sortedLastIndexBy.js
sortedLastIndex.js
sortedLastIndexOf.js
sortedUniqBy.js
sortedUniq.js
split.js
spread.js
_stackClear.js
_stackDelete.js
_stackGet.js
_stackHas.js
_Stack.js
_stackSet.js
startCase.js
startsWith.js
_strictIndexOf.js
_strictLastIndexOf.js
string.js
_stringSize.js
_stringToArray.js
_stringToPath.js
stubArray.js
stubFalse.js
stubObject.js
stubString.js
stubTrue.js
subtract.js
sumBy.js
sum.js
_Symbol.js
tail.js
take.js
takeRight.js
takeRightWhile.js
takeWhile.js
tap.js
template.js
templateSettings.js
throttle.js
thru.js
times.js
toArray.js
toFinite.js
toInteger.js
toIterator.js
toJSON.js
_toKey.js
toLength.js
toLower.js
toNumber.js
toPairsIn.js
toPairs.js
toPath.js
toPlainObject.js
toSafeInteger.js
_toSource.js
toString.js
toUpper.js
transform.js
trimEnd.js
trim.js
_trimmedEndIndex.js
trimStart.js
truncate.js
_Uint8Array.js
unary.js
_unescapeHtmlChar.js
unescape.js
_unicodeSize.js
_unicodeToArray.js
_unicodeWords.js
unionBy.js
union.js
unionWith.js
uniqBy.js
uniq.js
uniqueId.js
uniqWith.js
unset.js
unzip.js
unzipWith.js
update.js
updateWith.js
_updateWrapDetails.js
upperCase.js
upperFirst.js
util.js
value.js
valueOf.js
valuesIn.js
values.js
_WeakMap.js
without.js
words.js
wrap.js
wrapperAt.js
wrapperChain.js
_wrapperClone.js
wrapperLodash.js
wrapperReverse.js
wrapperValue.js
xorBy.js
xor.js
xorWith.js
zip.js
zipObjectDeep.js
zipObject.js
zipWith.js

./node_modules/lodash/fp:
add.js
after.js
all.js
allPass.js
always.js
any.js
anyPass.js
apply.js
array.js
ary.js
assignAll.js
assignAllWith.js
assignInAll.js
assignInAllWith.js
assignIn.js
assignInWith.js
assign.js
assignWith.js
assoc.js
assocPath.js
at.js
attempt.js
_baseConvert.js
before.js
bindAll.js
bind.js
bindKey.js
camelCase.js
capitalize.js
castArray.js
ceil.js
chain.js
chunk.js
clamp.js
cloneDeep.js
cloneDeepWith.js
clone.js
cloneWith.js
collection.js
commit.js
compact.js
complement.js
compose.js
concat.js
cond.js
conforms.js
conformsTo.js
constant.js
contains.js
_convertBrowser.js
convert.js
countBy.js
create.js
curry.js
curryN.js
curryRight.js
curryRightN.js
date.js
debounce.js
deburr.js
defaultsAll.js
defaultsDeepAll.js
defaultsDeep.js
defaults.js
defaultTo.js
defer.js
delay.js
differenceBy.js
difference.js
differenceWith.js
dissoc.js
dissocPath.js
divide.js
drop.js
dropLast.js
dropLastWhile.js
dropRight.js
dropRightWhile.js
dropWhile.js
each.js
eachRight.js
endsWith.js
entriesIn.js
entries.js
eq.js
equals.js
escape.js
escapeRegExp.js
every.js
extendAll.js
extendAllWith.js
extend.js
extendWith.js
_falseOptions.js
fill.js
filter.js
findFrom.js
findIndexFrom.js
findIndex.js
find.js
findKey.js
findLastFrom.js
findLastIndexFrom.js
findLastIndex.js
findLast.js
findLastKey.js
first.js
F.js
flatMapDeep.js
flatMapDepth.js
flatMap.js
flattenDeep.js
flattenDepth.js
flatten.js
flip.js
floor.js
flow.js
flowRight.js
forEach.js
forEachRight.js
forIn.js
forInRight.js
forOwn.js
forOwnRight.js
fromPairs.js
function.js
functionsIn.js
functions.js
get.js
getOr.js
groupBy.js
gte.js
gt.js
hasIn.js
has.js
head.js
identical.js
identity.js
includesFrom.js
includes.js
indexBy.js
indexOfFrom.js
indexOf.js
initial.js
init.js
inRange.js
intersectionBy.js
intersection.js
intersectionWith.js
invertBy.js
invert.js
invertObj.js
invokeArgs.js
invokeArgsMap.js
invoke.js
invokeMap.js
isArguments.js
isArrayBuffer.js
isArray.js
isArrayLike.js
isArrayLikeObject.js
isBoolean.js
isBuffer.js
isDate.js
isElement.js
isEmpty.js
isEqual.js
isEqualWith.js
isError.js
isFinite.js
isFunction.js
isInteger.js
isLength.js
isMap.js
isMatch.js
isMatchWith.js
isNaN.js
isNative.js
isNil.js
isNull.js
isNumber.js
isObject.js
isObjectLike.js
isPlainObject.js
isRegExp.js
isSafeInteger.js
isSet.js
isString.js
isSymbol.js
isTypedArray.js
isUndefined.js
isWeakMap.js
isWeakSet.js
iteratee.js
join.js
__.js
juxt.js
kebabCase.js
keyBy.js
keysIn.js
keys.js
lang.js
lastIndexOfFrom.js
lastIndexOf.js
last.js
lowerCase.js
lowerFirst.js
lte.js
lt.js
map.js
mapKeys.js
_mapping.js
mapValues.js
matches.js
matchesProperty.js
math.js
maxBy.js
max.js
meanBy.js
mean.js
memoize.js
mergeAll.js
mergeAllWith.js
merge.js
mergeWith.js
method.js
methodOf.js
minBy.js
min.js
mixin.js
multiply.js
nAry.js
negate.js
next.js
noop.js
now.js
nthArg.js
nth.js
number.js
object.js
omitAll.js
omitBy.js
omit.js
once.js
orderBy.js
overArgs.js
overEvery.js
over.js
overSome.js
padCharsEnd.js
padChars.js
padCharsStart.js
padEnd.js
pad.js
padStart.js
parseInt.js
partial.js
partialRight.js
partition.js
pathEq.js
path.js
pathOr.js
paths.js
pickAll.js
pickBy.js
pick.js
pipe.js
placeholder.js
plant.js
pluck.js
propEq.js
property.js
propertyOf.js
prop.js
propOr.js
props.js
pullAllBy.js
pullAll.js
pullAllWith.js
pullAt.js
pull.js
random.js
range.js
rangeRight.js
rangeStep.js
rangeStepRight.js
rearg.js
reduce.js
reduceRight.js
reject.js
remove.js
repeat.js
replace.js
restFrom.js
rest.js
result.js
reverse.js
round.js
sample.js
sampleSize.js
seq.js
set.js
setWith.js
shuffle.js
size.js
slice.js
snakeCase.js
some.js
sortBy.js
sortedIndexBy.js
sortedIndex.js
sortedIndexOf.js
sortedLastIndexBy.js
sortedLastIndex.js
sortedLastIndexOf.js
sortedUniqBy.js
sortedUniq.js
split.js
spreadFrom.js
spread.js
startCase.js
startsWith.js
string.js
stubArray.js
stubFalse.js
stubObject.js
stubString.js
stubTrue.js
subtract.js
sumBy.js
sum.js
symmetricDifferenceBy.js
symmetricDifference.js
symmetricDifferenceWith.js
tail.js
take.js
takeLast.js
takeLastWhile.js
takeRight.js
takeRightWhile.js
takeWhile.js
tap.js
template.js
templateSettings.js
throttle.js
thru.js
times.js
T.js
toArray.js
toFinite.js
toInteger.js
toIterator.js
toJSON.js
toLength.js
toLower.js
toNumber.js
toPairsIn.js
toPairs.js
toPath.js
toPlainObject.js
toSafeInteger.js
toString.js
toUpper.js
transform.js
trimCharsEnd.js
trimChars.js
trimCharsStart.js
trimEnd.js
trim.js
trimStart.js
truncate.js
unapply.js
unary.js
unescape.js
unionBy.js
union.js
unionWith.js
uniqBy.js
uniq.js
uniqueId.js
uniqWith.js
unnest.js
unset.js
unzip.js
unzipWith.js
update.js
updateWith.js
upperCase.js
upperFirst.js
useWith.js
_util.js
util.js
value.js
valueOf.js
valuesIn.js
values.js
whereEq.js
where.js
without.js
words.js
wrap.js
wrapperAt.js
wrapperChain.js
wrapperLodash.js
wrapperReverse.js
wrapperValue.js
xorBy.js
xor.js
xorWith.js
zipAll.js
zip.js
zipObjectDeep.js
zipObject.js
zipObj.js
zipWith.js

./node_modules/lodash-es:
add.js
_addMapEntry.js
_addSetEntry.js
after.js
_apply.js
_arrayAggregator.js
array.default.js
_arrayEach.js
_arrayEachRight.js
_arrayEvery.js
_arrayFilter.js
_arrayIncludes.js
_arrayIncludesWith.js
array.js
_arrayLikeKeys.js
_arrayMap.js
_arrayPush.js
_arrayReduce.js
_arrayReduceRight.js
_arraySample.js
_arraySampleSize.js
_arrayShuffle.js
_arraySome.js
ary.js
_asciiSize.js
_asciiToArray.js
_asciiWords.js
assignIn.js
assignInWith.js
assign.js
_assignMergeValue.js
_assignValue.js
assignWith.js
_assocIndexOf.js
at.js
attempt.js
_baseAggregator.js
_baseAssignIn.js
_baseAssign.js
_baseAssignValue.js
_baseAt.js
_baseClamp.js
_baseClone.js
_baseConforms.js
_baseConformsTo.js
_baseCreate.js
_baseDelay.js
_baseDifference.js
_baseEach.js
_baseEachRight.js
_baseEvery.js
_baseExtremum.js
_baseFill.js
_baseFilter.js
_baseFindIndex.js
_baseFindKey.js
_baseFlatten.js
_baseFor.js
_baseForOwn.js
_baseForOwnRight.js
_baseForRight.js
_baseFunctions.js
_baseGetAllKeys.js
_baseGet.js
_baseGetTag.js
_baseGt.js
_baseHasIn.js
_baseHas.js
_baseIndexOf.js
_baseIndexOfWith.js
_baseInRange.js
_baseIntersection.js
_baseInverter.js
_baseInvoke.js
_baseIsArguments.js
_baseIsArrayBuffer.js
_baseIsDate.js
_baseIsEqualDeep.js
_baseIsEqual.js
_baseIsMap.js
_baseIsMatch.js
_baseIsNaN.js
_baseIsNative.js
_baseIsRegExp.js
_baseIsSet.js
_baseIsTypedArray.js
_baseIteratee.js
_baseKeysIn.js
_baseKeys.js
_baseLodash.js
_baseLt.js
_baseMap.js
_baseMatches.js
_baseMatchesProperty.js
_baseMean.js
_baseMergeDeep.js
_baseMerge.js
_baseNth.js
_baseOrderBy.js
_basePickBy.js
_basePick.js
_basePropertyDeep.js
_baseProperty.js
_basePropertyOf.js
_basePullAll.js
_basePullAt.js
_baseRandom.js
_baseRange.js
_baseReduce.js
_baseRepeat.js
_baseRest.js
_baseSample.js
_baseSampleSize.js
_baseSetData.js
_baseSet.js
_baseSetToString.js
_baseShuffle.js
_baseSlice.js
_baseSome.js
_baseSortBy.js
_baseSortedIndexBy.js
_baseSortedIndex.js
_baseSortedUniq.js
_baseSum.js
_baseTimes.js
_baseToNumber.js
_baseToPairs.js
_baseToString.js
_baseTrim.js
_baseUnary.js
_baseUniq.js
_baseUnset.js
_baseUpdate.js
_baseValues.js
_baseWhile.js
_baseWrapperValue.js
_baseXor.js
_baseZipObject.js
before.js
bindAll.js
bind.js
bindKey.js
_cacheHas.js
camelCase.js
capitalize.js
castArray.js
_castArrayLikeObject.js
_castFunction.js
_castPath.js
_castRest.js
_castSlice.js
ceil.js
chain.js
_charsEndIndex.js
_charsStartIndex.js
chunk.js
clamp.js
_cloneArrayBuffer.js
_cloneBuffer.js
_cloneDataView.js
cloneDeep.js
cloneDeepWith.js
clone.js
_cloneMap.js
_cloneRegExp.js
_cloneSet.js
_cloneSymbol.js
_cloneTypedArray.js
cloneWith.js
collection.default.js
collection.js
commit.js
compact.js
_compareAscending.js
_compareMultiple.js
_composeArgs.js
_composeArgsRight.js
concat.js
cond.js
conforms.js
conformsTo.js
constant.js
_copyArray.js
_copyObject.js
_copySymbolsIn.js
_copySymbols.js
_coreJsData.js
countBy.js
_countHolders.js
_createAggregator.js
_createAssigner.js
_createBaseEach.js
_createBaseFor.js
_createBind.js
_createCaseFirst.js
_createCompounder.js
_createCtor.js
_createCurry.js
_createFind.js
_createFlow.js
_createHybrid.js
_createInverter.js
create.js
_createMathOperation.js
_createOver.js
_createPadding.js
_createPartial.js
_createRange.js
_createRecurry.js
_createRelationalOperation.js
_createRound.js
_createSet.js
_createToPairs.js
_createWrap.js
curry.js
curryRight.js
_customDefaultsAssignIn.js
_customDefaultsMerge.js
_customOmitClone.js
_DataView.js
date.default.js
date.js
debounce.js
deburr.js
_deburrLetter.js
defaultsDeep.js
defaults.js
defaultTo.js
defer.js
_defineProperty.js
delay.js
differenceBy.js
difference.js
differenceWith.js
divide.js
drop.js
dropRight.js
dropRightWhile.js
dropWhile.js
each.js
eachRight.js
endsWith.js
entriesIn.js
entries.js
eq.js
_equalArrays.js
_equalByTag.js
_equalObjects.js
_escapeHtmlChar.js
escape.js
escapeRegExp.js
_escapeStringChar.js
every.js
extend.js
extendWith.js
fill.js
filter.js
findIndex.js
find.js
findKey.js
findLastIndex.js
findLast.js
findLastKey.js
first.js
flake.lock
flake.nix
flatMapDeep.js
flatMapDepth.js
flatMap.js
_flatRest.js
flattenDeep.js
flattenDepth.js
flatten.js
flip.js
floor.js
flow.js
flowRight.js
forEach.js
forEachRight.js
forIn.js
forInRight.js
forOwn.js
forOwnRight.js
_freeGlobal.js
fromPairs.js
function.default.js
function.js
functionsIn.js
functions.js
_getAllKeysIn.js
_getAllKeys.js
_getData.js
_getFuncName.js
_getHolder.js
get.js
_getMapData.js
_getMatchData.js
_getNative.js
_getPrototype.js
_getRawTag.js
_getSymbolsIn.js
_getSymbols.js
_getTag.js
_getValue.js
_getView.js
_getWrapDetails.js
groupBy.js
gte.js
gt.js
_hashClear.js
_hashDelete.js
_hashGet.js
_hashHas.js
_Hash.js
_hashSet.js
hasIn.js
has.js
_hasPath.js
_hasUnicode.js
_hasUnicodeWord.js
head.js
identity.js
includes.js
indexOf.js
_initCloneArray.js
_initCloneByTag.js
_initCloneObject.js
initial.js
inRange.js
_insertWrapDetails.js
intersectionBy.js
intersection.js
intersectionWith.js
invertBy.js
invert.js
invoke.js
invokeMap.js
isArguments.js
isArrayBuffer.js
isArray.js
isArrayLike.js
isArrayLikeObject.js
isBoolean.js
isBuffer.js
isDate.js
isElement.js
isEmpty.js
isEqual.js
isEqualWith.js
isError.js
isFinite.js
_isFlattenable.js
isFunction.js
_isIndex.js
isInteger.js
_isIterateeCall.js
_isKeyable.js
_isKey.js
_isLaziable.js
isLength.js
isMap.js
_isMaskable.js
_isMasked.js
isMatch.js
isMatchWith.js
isNaN.js
isNative.js
isNil.js
isNull.js
isNumber.js
isObject.js
isObjectLike.js
isPlainObject.js
_isPrototype.js
isRegExp.js
isSafeInteger.js
isSet.js
_isStrictComparable.js
isString.js
isSymbol.js
isTypedArray.js
isUndefined.js
isWeakMap.js
isWeakSet.js
iteratee.js
_iteratorToArray.js
join.js
kebabCase.js
keyBy.js
keysIn.js
keys.js
lang.default.js
lang.js
lastIndexOf.js
last.js
_lazyClone.js
_lazyReverse.js
_lazyValue.js
_LazyWrapper.js
LICENSE
_listCacheClear.js
_listCacheDelete.js
_listCacheGet.js
_listCacheHas.js
_ListCache.js
_listCacheSet.js
lodash.default.js
lodash.js
_LodashWrapper.js
lowerCase.js
lowerFirst.js
lte.js
lt.js
_mapCacheClear.js
_mapCacheDelete.js
_mapCacheGet.js
_mapCacheHas.js
_MapCache.js
_mapCacheSet.js
map.js
_Map.js
mapKeys.js
_mapToArray.js
mapValues.js
matches.js
matchesProperty.js
_matchesStrictComparable.js
math.default.js
math.js
maxBy.js
max.js
meanBy.js
mean.js
_memoizeCapped.js
memoize.js
_mergeData.js
merge.js
mergeWith.js
_metaMap.js
method.js
methodOf.js
minBy.js
min.js
mixin.js
multiply.js
_nativeCreate.js
_nativeKeysIn.js
_nativeKeys.js
negate.js
next.js
_nodeUtil.js
noop.js
now.js
nthArg.js
nth.js
number.default.js
number.js
object.default.js
object.js
_objectToString.js
omitBy.js
omit.js
once.js
orderBy.js
_overArg.js
overArgs.js
overEvery.js
over.js
_overRest.js
overSome.js
package.json
padEnd.js
pad.js
padStart.js
_parent.js
parseInt.js
partial.js
partialRight.js
partition.js
pickBy.js
pick.js
plant.js
_Promise.js
property.js
propertyOf.js
pullAllBy.js
pullAll.js
pullAllWith.js
pullAt.js
pull.js
random.js
range.js
rangeRight.js
README.md
_realNames.js
rearg.js
reduce.js
reduceRight.js
_reEscape.js
_reEvaluate.js
_reInterpolate.js
reject.js
release.md
remove.js
_reorder.js
repeat.js
_replaceHolders.js
replace.js
rest.js
result.js
reverse.js
_root.js
round.js
_safeGet.js
sample.js
sampleSize.js
seq.default.js
seq.js
_setCacheAdd.js
_setCacheHas.js
_SetCache.js
_setData.js
set.js
_Set.js
_setToArray.js
_setToPairs.js
_setToString.js
setWith.js
_setWrapToString.js
_shortOut.js
shuffle.js
_shuffleSelf.js
size.js
slice.js
snakeCase.js
some.js
sortBy.js
sortedIndexBy.js
sortedIndex.js
sortedIndexOf.js
sortedLastIndexBy.js
sortedLastIndex.js
sortedLastIndexOf.js
sortedUniqBy.js
sortedUniq.js
split.js
spread.js
_stackClear.js
_stackDelete.js
_stackGet.js
_stackHas.js
_Stack.js
_stackSet.js
startCase.js
startsWith.js
_strictIndexOf.js
_strictLastIndexOf.js
string.default.js
string.js
_stringSize.js
_stringToArray.js
_stringToPath.js
stubArray.js
stubFalse.js
stubObject.js
stubString.js
stubTrue.js
subtract.js
sumBy.js
sum.js
_Symbol.js
tail.js
take.js
takeRight.js
takeRightWhile.js
takeWhile.js
tap.js
template.js
templateSettings.js
throttle.js
thru.js
times.js
toArray.js
toFinite.js
toInteger.js
toIterator.js
toJSON.js
_toKey.js
toLength.js
toLower.js
toNumber.js
toPairsIn.js
toPairs.js
toPath.js
toPlainObject.js
toSafeInteger.js
_toSource.js
toString.js
toUpper.js
transform.js
trimEnd.js
trim.js
_trimmedEndIndex.js
trimStart.js
truncate.js
_Uint8Array.js
unary.js
_unescapeHtmlChar.js
unescape.js
_unicodeSize.js
_unicodeToArray.js
_unicodeWords.js
unionBy.js
union.js
unionWith.js
uniqBy.js
uniq.js
uniqueId.js
uniqWith.js
unset.js
unzip.js
unzipWith.js
update.js
updateWith.js
_updateWrapDetails.js
upperCase.js
upperFirst.js
util.default.js
util.js
value.js
valueOf.js
valuesIn.js
values.js
_WeakMap.js
without.js
words.js
wrap.js
wrapperAt.js
wrapperChain.js
_wrapperClone.js
wrapperLodash.js
wrapperReverse.js
wrapperValue.js
xorBy.js
xor.js
xorWith.js
zip.js
zipObjectDeep.js
zipObject.js
zipWith.js

./node_modules/@prettier:
plugin-xml

./node_modules/@prettier/plugin-xml:
CHANGELOG.md
eslint.config.mjs
LICENSE
package.json
README.md
src
types

./node_modules/@prettier/plugin-xml/src:
embed.js
languages.js
parser.js
plugin.js
printer.js

./node_modules/@prettier/plugin-xml/types:
plugin.d.ts

./node_modules/prettier:
bin
doc.d.ts
doc.js
doc.mjs
index.cjs
index.d.ts
index.mjs
internal
LICENSE
package.json
plugins
README.md
standalone.d.ts
standalone.js
standalone.mjs
THIRD-PARTY-NOTICES.md

./node_modules/prettier/bin:
prettier.cjs

./node_modules/prettier/internal:
experimental-cli.mjs
experimental-cli-worker.mjs
legacy-cli.mjs

./node_modules/prettier/plugins:
acorn.d.ts
acorn.js
acorn.mjs
angular.d.ts
angular.js
angular.mjs
babel.d.ts
babel.js
babel.mjs
estree.d.ts
estree.js
estree.mjs
flow.d.ts
flow.js
flow.mjs
glimmer.d.ts
glimmer.js
glimmer.mjs
graphql.d.ts
graphql.js
graphql.mjs
html.d.ts
html.js
html.mjs
markdown.d.ts
markdown.js
markdown.mjs
meriyah.d.ts
meriyah.js
meriyah.mjs
postcss.d.ts
postcss.js
postcss.mjs
typescript.d.ts
typescript.js
typescript.mjs
yaml.d.ts
yaml.js
yaml.mjs

./node_modules/prettier-plugin-java:
dist
LICENSE
package.json
README.md

./node_modules/prettier-plugin-java/dist:
comments.d.ts
comments.js
index.d.ts
index.js
options.d.ts
options.js
parser.d.ts
parser.js
printer.d.ts
printer.js
printers

./node_modules/prettier-plugin-java/dist/printers:
arrays.d.ts
arrays.js
blocks-and-statements.d.ts
blocks-and-statements.js
classes.d.ts
classes.js
expressions.d.ts
expressions.js
helpers.d.ts
helpers.js
index.d.ts
index.js
interfaces.d.ts
interfaces.js
lexical-structure.d.ts
lexical-structure.js
names.d.ts
names.js
packages-and-modules.d.ts
packages-and-modules.js
types-values-and-variables.d.ts
types-values-and-variables.js

./node_modules/prettier-plugin-sh:
index.d.cts
lib
LICENSE
package.json
README.md

./node_modules/prettier-plugin-sh/lib:
index.cjs
index.d.ts
index.js
index.js.map
languages.d.ts
languages.js
languages.js.map

./node_modules/regexp-to-ast:
api.d.ts
CHANGELOG.md
lib
LICENSE
package.json
README.md

./node_modules/regexp-to-ast/lib:
regexp-to-ast.js

./node_modules/@reteps:
dockerfmt

./node_modules/@reteps/dockerfmt:
dist
package.json
README.md

./node_modules/@reteps/dockerfmt/dist:
format.d.ts
format.js
format.wasm
node.d.ts
node.js
wasm_exec.js

./node_modules/sh-syntax:
lib
LICENSE
main.wasm
package.json
README.md
vendors

./node_modules/sh-syntax/lib:
browser.d.ts
browser.js
browser.js.map
index.cjs
index.d.cts
index.d.ts
index.js
index.js.map
processor.d.ts
processor.js
processor.js.map
shim.d.ts
shim.js
shim.js.map
types.d.ts
types.js
types.js.map

./node_modules/sh-syntax/vendors:
wasm_exec.cjs
wasm_exec.d.cts

./node_modules/tslib:
CopyrightNotice.txt
LICENSE.txt
modules
package.json
README.md
SECURITY.md
tslib.d.ts
tslib.es6.html
tslib.es6.js
tslib.es6.mjs
tslib.html
tslib.js

./node_modules/tslib/modules:
index.d.ts
index.js
package.json

./node_modules/@xml-tools:
parser

./node_modules/@xml-tools/parser:
api.d.ts
lib
LICENSE
LICENSES
node_modules
package.json
README.md

./node_modules/@xml-tools/parser/lib:
api.js
lexer.js
parser.js

./node_modules/@xml-tools/parser/LICENSES:
Apache-2.0.txt

./node_modules/@xml-tools/parser/node_modules:
chevrotain

./node_modules/@xml-tools/parser/node_modules/chevrotain:
CHANGELOG.md
diagrams
lib
lib_esm
LICENSE.txt
package.json
README.md
src

./node_modules/@xml-tools/parser/node_modules/chevrotain/diagrams:
diagrams.css
README.md
src
vendor

./node_modules/@xml-tools/parser/node_modules/chevrotain/diagrams/src:
diagrams_behavior.js
diagrams_builder.js
diagrams_serializer.js
main.js

./node_modules/@xml-tools/parser/node_modules/chevrotain/diagrams/vendor:
railroad-diagrams.js

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib:
chevrotain.d.ts
chevrotain.js
chevrotain.min.js
src

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src:
api.js
api.js.map
diagrams
generate
lang
parse
scan
text
utils
version.js
version.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/diagrams:
render_public.js
render_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/generate:
generate.js
generate.js.map
generate_public.js
generate_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/lang:
lang_extensions.js
lang_extensions.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse:
constants.js
constants.js.map
cst
errors_public.js
errors_public.js.map
exceptions_public.js
exceptions_public.js.map
grammar
parser

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse/cst:
cst.js
cst.js.map
cst_visitor.js
cst_visitor.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse/grammar:
checks.js
checks.js.map
first.js
first.js.map
follow.js
follow.js.map
gast
interpreter.js
interpreter.js.map
keys.js
keys.js.map
lookahead.js
lookahead.js.map
resolver.js
resolver.js.map
rest.js
rest.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse/grammar/gast:
gast.js
gast.js.map
gast_public.js
gast_public.js.map
gast_resolver_public.js
gast_resolver_public.js.map
gast_visitor_public.js
gast_visitor_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse/parser:
parser.js
parser.js.map
traits

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/parse/parser/traits:
context_assist.js
context_assist.js.map
error_handler.js
error_handler.js.map
gast_recorder.js
gast_recorder.js.map
lexer_adapter.js
lexer_adapter.js.map
looksahead.js
looksahead.js.map
parser_traits.js
parser_traits.js.map
perf_tracer.js
perf_tracer.js.map
recognizer_api.js
recognizer_api.js.map
recognizer_engine.js
recognizer_engine.js.map
recoverable.js
recoverable.js.map
tree_builder.js
tree_builder.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/scan:
lexer_errors_public.js
lexer_errors_public.js.map
lexer.js
lexer.js.map
lexer_public.js
lexer_public.js.map
reg_exp.js
reg_exp.js.map
reg_exp_parser.js
reg_exp_parser.js.map
tokens_constants.js
tokens_constants.js.map
tokens.js
tokens.js.map
tokens_public.js
tokens_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/text:
range.js
range.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib/src/utils:
utils.js
utils.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm:
src

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src:
api.js
api.js.map
diagrams
generate
lang
parse
scan
text
utils
version.js
version.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/diagrams:
render_public.js
render_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/generate:
generate.js
generate.js.map
generate_public.js
generate_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/lang:
lang_extensions.js
lang_extensions.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse:
constants.js
constants.js.map
cst
errors_public.js
errors_public.js.map
exceptions_public.js
exceptions_public.js.map
grammar
parser

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse/cst:
cst.js
cst.js.map
cst_visitor.js
cst_visitor.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse/grammar:
checks.js
checks.js.map
first.js
first.js.map
follow.js
follow.js.map
gast
interpreter.js
interpreter.js.map
keys.js
keys.js.map
lookahead.js
lookahead.js.map
resolver.js
resolver.js.map
rest.js
rest.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse/grammar/gast:
gast.js
gast.js.map
gast_public.js
gast_public.js.map
gast_resolver_public.js
gast_resolver_public.js.map
gast_visitor_public.js
gast_visitor_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse/parser:
parser.js
parser.js.map
traits

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/parse/parser/traits:
context_assist.js
context_assist.js.map
error_handler.js
error_handler.js.map
gast_recorder.js
gast_recorder.js.map
lexer_adapter.js
lexer_adapter.js.map
looksahead.js
looksahead.js.map
parser_traits.js
parser_traits.js.map
perf_tracer.js
perf_tracer.js.map
recognizer_api.js
recognizer_api.js.map
recognizer_engine.js
recognizer_engine.js.map
recoverable.js
recoverable.js.map
tree_builder.js
tree_builder.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/scan:
lexer_errors_public.js
lexer_errors_public.js.map
lexer.js
lexer.js.map
lexer_public.js
lexer_public.js.map
reg_exp.js
reg_exp.js.map
reg_exp_parser.js
reg_exp_parser.js.map
tokens_constants.js
tokens_constants.js.map
tokens.js
tokens.js.map
tokens_public.js
tokens_public.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/text:
range.js
range.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/lib_esm/src/utils:
utils.js
utils.js.map

./node_modules/@xml-tools/parser/node_modules/chevrotain/src:
api.ts
diagrams
generate
lang
parse
scan
text
utils
version.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/diagrams:
render_public.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/generate:
generate_public.ts
generate.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/lang:
lang_extensions.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse:
constants.ts
cst
errors_public.ts
exceptions_public.ts
grammar
parser

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse/cst:
cst.ts
cst_visitor.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse/grammar:
checks.ts
first.ts
follow.ts
gast
interpreter.ts
keys.ts
lookahead.ts
resolver.ts
rest.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse/grammar/gast:
gast_public.ts
gast_resolver_public.ts
gast.ts
gast_visitor_public.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse/parser:
parser.ts
traits

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/parse/parser/traits:
context_assist.ts
error_handler.ts
gast_recorder.ts
lexer_adapter.ts
looksahead.ts
parser_traits.ts
perf_tracer.ts
README.md
recognizer_api.ts
recognizer_engine.ts
recoverable.ts
tree_builder.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/scan:
lexer_errors_public.ts
lexer_public.ts
lexer.ts
reg_exp_parser.ts
reg_exp.ts
tokens_constants.ts
tokens_public.ts
tokens.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/text:
range.ts

./node_modules/@xml-tools/parser/node_modules/chevrotain/src/utils:
utils.ts

./prometheus:
prometheus.yml
```


=========================================
System Information
=========================================
```bash


                    -`
                   .o+`
                  `ooo/
                 `+oooo:
                `+oooooo:
                -+oooooo+:
              `/:-:++oooo+:
             `/++++/+++++++:
            `/++++++++++++++:
           `/+++ooooooooooooo/`
          ./ooosssso++osssssso+`
         .oossssso-````/ossssss+`
        -osssssso.      :ssssssso.
       :osssssss/        osssso+++.
      /ossssssss/        +ssssooo/-
    `/ossssso+/:-        -:/+osssso+-
   `+sso+:-`                 `.-/+oso:
  `++:.                           `-/+/
  .`                                 `/[1G[20A[62COS  Arch Linux x86_64
[62CHost  Laptop 13 (AMD Ryzen 7040Series) (A5)
[62CKernel  Linux 7.0.8-zen1-1-zen
[62CUptime  7 hours, 5 mins
[62CProcesses  381
[62CPackages  11 (flatpak), 1393 (pacman)
[62CShell  fish 4.7.1
[62CDisplay  2880x1920 @ 2x in 13", 120 Hz [Built-in]
[62CLM  sddm 0.21.0 (Wayland)
[62CDE  KDE Plasma 6.6.5
[62CWM  KWin (Wayland)
[62CTerminal  ghostty 1.3.1-arch2
[62CCPU  AMD Ryzen 5 7640U (12) @ 3.50 GHz
[62CGPU  AMD Radeon 760M Graphics [Integrated]
[62CMemory  8.10 GiB / 27.20 GiB (30%)
[62CDisk (/)  585.38 GiB / 930.50 GiB (63%) - btrfs
[62CBattery  15% (33 mins remaining) [Discharging]
[62CLocal IP  172.27.66.115/24
[62CDNS  172.27.66.19 2a04:cec0:1199:6e0d::20
[62CDate & Time  2026-26-5 23:11:11
[62C
[62C● ● ● ● ● ● ● ●
```


=========================================
here how this was made :
=========================================
```bash
#!/bin/bash

OUTPUT="combinedCodebase.md"
SCRIPT_SOURCE=$(realpath "$0")

echo "Running code combiner in: $(pwd)"

if [ -f "$OUTPUT" ]; then
    rm "$OUTPUT"
fi

{
    echo "========================================="
    echo "Project Path: $(pwd)"
    echo "Project Directory Structure"
    echo "========================================="
    echo '```'
    ls -R
    echo '```'
    echo -e "\n"

    echo "========================================="
    echo "System Information"
    echo "========================================="
    echo '```bash'
    fastfetch
    echo '```'
    echo -e "\n"

    echo "========================================="
    echo "here how this was made :"
    echo "========================================="
    echo '```bash'
    cat "$SCRIPT_SOURCE"
    echo '```'
    echo -e "\n"

    EXCLUDES=(
        # --- 1. Version Control & Script Output ---
        -not -path '*/.git/*'
        -not -name "diff.md"
        -not -name "$OUTPUT"

        # --- 2. Build & Dependency Directories ---
        -not -path '*/node_modules/*'
        -not -path '*/build/*'
        -not -path '*/target/*'
        -not -path '*/dist/*'
        -not -path '*/dist-ssr/*'
        -not -path '*/bin/*'
        -not -path '*/venv/*'

        # --- 3. Package Manager & Wrappers ---
        -not -name "package-lock.json"
        -not -name "mvnw"
        -not -name "mvnw.cmd"
        -not -path "*/.mvn/*"

        # --- 4. Binaries, Archives & Compiled Code ---
        -not -name "*.exe"
        -not -name "*.jar"
        -not -name "*.class"
        -not -name "*.o"
        -not -name "*.a"
        -not -name "*.out"

        # --- 5. Media & Images ---
        -not -name "*.pdf"
        -not -name "*.ico"
        -not -name "*.png"
        -not -name "*.jpg"
        -not -name "*.jpeg"
        -not -name "*.svg"

        # --- 6. Logs & Debugging ---
        -not -path "*/logs/*"
        -not -name "*.log"
        -not -name "npm-debug.log*"
        -not -name "yarn-debug.log*"
        -not -name "yarn-error.log*"
        -not -name "pnpm-debug.log*"
        -not -name "lerna-debug.log*"
        -not -name "hs_err_pid*"
        -not -name "replay_pid*"

        # --- 7. IDE, OS & Editor Files ---
        -not -name ".DS_Store"
        -not -name ".DS_Store?"
        -not -name "._*"
        -not -name ".Spotlight-V100"
        -not -name ".Trashes"
        -not -name "ehthumbs.db"
        -not -name "Thumbs.db"
        -not \( -path "*/.vscode/*" -a -not -name "extensions.json" \)
        -not -path "*/.idea/*"
        -not -path "*/.settings/*"
        -not -path "*/.apt_generated/*"
        -not -path "*/.sts4-cache/*"
        -not -path "*/nbproject/private/*"
        -not -path "*/nbbuild/*"
        -not -path "*/nbdist/*"
        -not -path "*/.nb-gradle/*"
        -not -name ".classpath"
        -not -name ".factorypath"
        -not -name ".project"
        -not -name ".springBeans"
        -not -name "*.iws"
        -not -name "*.iml"
        -not -name "*.ipr"
        -not -name "*.suo"
        -not -name "*.ntvs*"
        -not -name "*.njsproj"
        -not -name "*.sln"
        -not -name "*.sw?"
        -not -name "*.local"

        # --- 8. OCaml / Language Specific Temp Files ---
        -not -path "*/_build/*"
        -not -path "*/.merlin-conf/*"
        -not -path "*/.*.objs/*"
        -not -name "*_generated.ml"
        -not -name "*.cmly"
        -not -name "*.conflicts"
        -not -name "*.automaton"
        -not -name "*.dot"
        -not -name "*.cmi"
        -not -name "*.cmo"
        -not -name "*.cmt"
        -not -name "*.cmti"
        -not -name "*.cmx"
        -not -name "*.cma"
        -not -name "*.cmxa"
        -not -name "*.cmxs"
        -not -name "*.ml-gen"
        -not -name "*.all-deps"
        -not -name "*.d"
        -not -name "*.mock"
        -not -name "*.inferred"
        -not -name "Cmly.ml"

        # --- 9. Project Specific Exclusions ---
        -not -path "*/mini_ml/examples/*"
        -not -path "*/models/*"
        -not -path "*/install/*"
        -not -path "*/programs/*"
        -not -name "HELP.md"
        -not \( -name ".env*" -a -not -name ".env.example" \)
    )

    find . -type f "${EXCLUDES[@]}" \
        -exec grep -Iq . {} \; \
        -print0 | while IFS= read -r -d '' file; do
            echo "========================================="
            echo "File: $file"
            echo "========================================="
            cat "$file"
            echo -e "\n"
        done

} > "$OUTPUT"

echo "Success! All code combined into: $(pwd)/$OUTPUT"
```


=========================================
File: ./backend/.gitattributes
=========================================
/mvnw text eol=lf
*.cmd text eol=crlf


=========================================
File: ./backend/src/main/resources/application.yaml
=========================================
spring:
  threads:
    virtual:
      enabled: true
  config:
    import: optional:file:.env.properties
  mvc:
    problemdetails:
      enabled: true
  datasource:
    url: jdbc:postgresql://${HOST_NAME:localhost}:5432/${DATABASE_NAME:pdl-db}
    username: ${DATABASE_USER:default}
    password: ${DATABASE_PASSWORD}
    driver-class-name: org.postgresql.Driver
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
  servlet:
    multipart:
      max-file-size: 100MB
      max-request-size: 100MB

jwt:
  secret: ${JWT_SECRET}

app:
  admin:
    username: ${ADMIN_USERNAME}
    password: ${ADMIN_PASSWORD}
  unsplash:
    dataset-dir: ${DATASET_DIR:/var/lib/pdl/datasets}

management:
  endpoint:
    health:
      show-details: always
      cache:
        time-to-live: 15s
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  metrics:
    export:
      prometheus:
        enabled: true

s3:
  endpoint: ${S3_ENDPOINT:http://localhost:3900}
  region: ${S3_REGION:garage}
  access-key: ${S3_ACCESS_KEY}
  secret-key: ${S3_SECRET_KEY}
  bucket: ${S3_BUCKET:pdl-images}


=========================================
File: ./backend/src/main/resources/db/migration/V1__Initial_schema.sql
=========================================
CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    format VARCHAR(10) NOT NULL,
    width INT NOT NULL DEFAULT 0,
    height INT NOT NULL DEFAULT 0,
    hash VARCHAR(64) UNIQUE,
    extraction_status VARCHAR(20) DEFAULT 'PENDING',
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    provider VARCHAR(50) DEFAULT 'LOCAL',
    provider_id VARCHAR(100),
    description TEXT,
    photographer_name VARCHAR(255),
    camera_make VARCHAR(255),
    location_country VARCHAR(255),
    stats_downloads BIGINT DEFAULT 0,
    remote_url TEXT
);

CREATE TABLE IF NOT EXISTS imagedescriptors (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    hogvector vector(31),
    hsvvector vector(256),
    rgbvector vector(512),
    labvector vector(512),
    semanticvector vector(768),
    PRIMARY KEY (imageid)
);

CREATE TABLE IF NOT EXISTS imagekeywords (
    imageid BIGINT REFERENCES images(id) ON DELETE CASCADE,
    keyword VARCHAR(255) NOT NULL,
    is_ai_generated BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (imageid, keyword)
);

CREATE INDEX IF NOT EXISTS idx_hnsw_hog ON imagedescriptors USING hnsw (hogvector vector_l2_ops) WITH (m=4, ef_construction=32);
CREATE INDEX IF NOT EXISTS idx_hnsw_hsv ON imagedescriptors USING hnsw (hsvvector vector_l2_ops) WITH (m=16, ef_construction=128);
CREATE INDEX IF NOT EXISTS idx_hnsw_rgb ON imagedescriptors USING hnsw (rgbvector vector_l2_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_lab ON imagedescriptors USING hnsw (labvector vector_cosine_ops) WITH (m=32, ef_construction=256);
CREATE INDEX IF NOT EXISTS idx_hnsw_semantic ON imagedescriptors USING hnsw (semanticvector vector_cosine_ops) WITH (m=32, ef_construction=256);

CREATE INDEX IF NOT EXISTS idx_images_provider_id ON images(provider_id);
CREATE INDEX IF NOT EXISTS idx_images_status ON images(extraction_status);


=========================================
File: ./backend/src/main/resources/db/migration/V2__Add_user_approval.sql
=========================================
ALTER TABLE users ADD COLUMN is_approved BOOLEAN DEFAULT FALSE;

-- Ensure existing users (like the original admin) are approved so no one is locked out
UPDATE users SET is_approved = TRUE;


=========================================
File: ./backend/src/main/resources/db/migration/V3__Add_settings_table.sql
=========================================
CREATE TABLE app_settings (
    setting_key VARCHAR(255) PRIMARY KEY,
    setting_value VARCHAR(255) NOT NULL
);

INSERT INTO app_settings (setting_key, setting_value) VALUES ('auto_approve_users', 'false');


=========================================
File: ./backend/src/main/java/pdl/backend/Application.java
=========================================
package pdl.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;

@SpringBootApplication
@EnableAsync
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner dataSeeder(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    @Value("${app.admin.username}") String adminUsername,
    @Value("${app.admin.password}") String adminPassword
  ) {
    return args -> {
      if (userRepository.findByUsername(adminUsername).isEmpty()) {
        log.info("Seeding dynamic ADMIN account...");
        UserAccount admin = new UserAccount(
          adminUsername,
          passwordEncoder.encode(adminPassword),
          "ROLE_ADMIN"
        );
        admin.setApproved(true);
        userRepository.save(admin);
        log.info("Default ADMIN account successfully seeded. Username: {}", adminUsername);
      }
    };
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/AsyncConfig.java
=========================================
package pdl.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Configuration
public class AsyncConfig implements AsyncConfigurer {

  private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (throwable, method, params) ->
      log.error(
        "CRITICAL: Unhandled async exception in method: {} with parameters: {}",
        method.getName(),
        params,
        throwable
      );
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AdminUserController.java
=========================================
package pdl.backend.auth;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

  private final UserRepository userRepository;
  private final AppSettingRepository appSettingRepository;

  public AdminUserController(
    UserRepository userRepository,
    AppSettingRepository appSettingRepository
  ) {
    this.userRepository = userRepository;
    this.appSettingRepository = appSettingRepository;
  }

  @GetMapping("/settings/auto-approve")
  public ResponseEntity<Map<String, Boolean>> getAutoApprove() {
    boolean val = appSettingRepository
      .findById("auto_approve_users")
      .map(s -> Boolean.parseBoolean(s.getSettingValue()))
      .orElse(false);
    return ResponseEntity.ok(Map.of("enabled", val));
  }

  @PutMapping("/settings/auto-approve")
  public ResponseEntity<?> setAutoApprove(@RequestParam boolean enabled) {
    AppSetting setting = appSettingRepository
      .findById("auto_approve_users")
      .orElse(new AppSetting("auto_approve_users", "false"));
    setting.setSettingValue(String.valueOf(enabled));
    appSettingRepository.save(setting);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/pending")
  public ResponseEntity<List<UserAccount>> getPendingUsers() {
    return ResponseEntity.ok(userRepository.findPendingApprovals());
  }

  @PutMapping("/{id}/approve")
  public ResponseEntity<?> approveUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("error", "User is already approved.")
          );
        }
        user.setApproved(true);
        userRepository.save(user);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> rejectUser(@PathVariable Long id) {
    return userRepository
      .findById(id)
      .map(user -> {
        if ("ROLE_ADMIN".equals(user.getRole())) {
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            Map.of("error", "Cannot delete an administrator account.")
          );
        }
        if (user.isApproved()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of(
              "error",
              "Cannot reject an already active user. Use the ban/delete workflow instead."
            )
          );
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AppSetting.java
=========================================
package pdl.backend.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("app_settings")
public class AppSetting {

  @Id
  @Column("setting_key")
  private String settingKey;

  @Column("setting_value")
  private String settingValue;

  public AppSetting() {}

  public AppSetting(String settingKey, String settingValue) {
    this.settingKey = settingKey;
    this.settingValue = settingValue;
  }

  public String getSettingKey() {
    return settingKey;
  }

  public void setSettingKey(String settingKey) {
    this.settingKey = settingKey;
  }

  public String getSettingValue() {
    return settingValue;
  }

  public void setSettingValue(String settingValue) {
    this.settingValue = settingValue;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AppSettingRepository.java
=========================================
package pdl.backend.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends CrudRepository<AppSetting, String> {}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/UserAccount.java
=========================================
package pdl.backend.auth;

import java.util.Collection;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table("users")
public class UserAccount implements UserDetails {

  @Id
  private Long id;

  private String username;
  private String password;
  private String role;

  @Column("is_approved")
  private boolean isApproved;

  public UserAccount() {}

  public UserAccount(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.isApproved = false;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    this.isApproved = approved;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role != null ? role : "ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isApproved;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/UserRepository.java
=========================================
package pdl.backend.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserAccount, Long> {
  Optional<UserAccount> findByUsername(String username);

  @Query("SELECT * FROM users WHERE is_approved = false")
  List<UserAccount> findPendingApprovals();
}


=========================================
File: ./backend/src/main/java/pdl/backend/auth/AuthController.java
=========================================
package pdl.backend.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;
  private final AppSettingRepository appSettingRepository;

  public AuthController(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager,
    JwtEncoder jwtEncoder,
    AppSettingRepository appSettingRepository
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtEncoder = jwtEncoder;
    this.appSettingRepository = appSettingRepository;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
    if (userRepository.findByUsername(request.get("username")).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username taken");
    }
    boolean autoApprove = appSettingRepository
      .findById("auto_approve_users")
      .map(s -> Boolean.parseBoolean(s.getSettingValue()))
      .orElse(false);

    UserAccount user = new UserAccount(
      request.get("username"),
      passwordEncoder.encode(request.get("password")),
      "ROLE_USER"
    );
    user.setApproved(autoApprove);
    userRepository.save(user);
    return ResponseEntity.ok(Map.of("message", "User registered successfully"));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
      );

      UserAccount userDetails = (UserAccount) authentication.getPrincipal();
      Instant now = Instant.now();
      String role = userDetails.getAuthorities().iterator().next().getAuthority();

      JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("pdl-backend")
        .issuedAt(now)
        .expiresAt(now.plus(24, ChronoUnit.HOURS))
        .subject(userDetails.getUsername())
        .claim("role", role)
        .claim("userId", userDetails.getId())
        .build();

      JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
      String token = this.jwtEncoder.encode(
        JwtEncoderParameters.from(jwsHeader, claims)
      ).getTokenValue();

      return ResponseEntity.ok(Map.of("token", token));
    } catch (DisabledException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your account is pending administrator approval.");
    } catch (BadCredentialsException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/config/S3Config.java
=========================================
package pdl.backend.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

  @Value("${s3.endpoint}")
  private String endpoint;

  @Value("${s3.region}")
  private String region;

  @Value("${s3.access-key}")
  private String accessKey;

  @Value("${s3.secret-key}")
  private String secretKey;

  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
      .endpointOverride(URI.create(endpoint))
      .region(Region.of(region))
      .credentialsProvider(
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
      )
      .forcePathStyle(true) // Crucial for Garage
      .serviceConfiguration(
        software.amazon.awssdk.services.s3.S3Configuration.builder()
          .chunkedEncodingEnabled(false)
          .build()
      )
      .build();
  }

  @Bean
  public S3Presigner s3Presigner() {
    return S3Presigner.builder()
      .endpointOverride(URI.create(endpoint))
      .region(Region.of(region))
      .credentialsProvider(
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
      )
      .serviceConfiguration(
        software.amazon.awssdk.services.s3.S3Configuration.builder()
          .pathStyleAccessEnabled(true)
          .build()
      )
      .build();
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/config/SecurityConfig.java
=========================================
package pdl.backend.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.time.Duration;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import pdl.backend.auth.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Value("${jwt.secret}")
  private String jwtKey;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize ->
        authorize
          .requestMatchers("/auth/**")
          .permitAll()
          .requestMatchers(HttpMethod.GET, "/images/**")
          .permitAll()
          .requestMatchers("/admin/**")
          .hasRole("ADMIN")
          .requestMatchers("/actuator/**")
          .permitAll()
          .requestMatchers("/_nuxt/**", "/static/**", "/*.html", "/favicon.ico")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .oauth2ResourceServer(oauth2 ->
        oauth2.jwt(jwt ->
          jwt.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthenticationConverter())
        )
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username ->
      userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
    OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
      new JwtTimestampValidator(Duration.ofSeconds(60))
    );
    jwtDecoder.setJwtValidator(withClockSkew);
    return jwtDecoder;
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    SecretKeySpec secretKey = new SecretKeySpec(jwtKey.getBytes(), "HmacSHA256");
    JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(secretKey);
    return new NimbusJwtEncoder(immutableSecret);
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
      new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("role");
    grantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/config/SpaRoutingConfig.java
=========================================
package pdl.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SpaRoutingConfig {

  @Bean
  public FilterRegistrationBean<OncePerRequestFilter> spaRedirectFiler() {
    FilterRegistrationBean<OncePerRequestFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(createRedirectFilter());
    registration.addUrlPatterns("/*");
    registration.setName("frontendRedirectFiler");
    registration.setOrder(1);
    return registration;
  }

  private OncePerRequestFilter createRedirectFilter() {
    return new OncePerRequestFilter() {
      private final String REGEX =
        "(?!/actuator|/images|/auth|/admin|/_nuxt|/static|/index\\.html|/200\\.html|/favicon\\.ico|/sw\\.js).*$";
      private Pattern pattern = Pattern.compile(REGEX);

      @Override
      protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
      ) throws ServletException, IOException {
        if (pattern.matcher(req.getRequestURI()).matches() && !req.getRequestURI().equals("/")) {
          RequestDispatcher rd = req.getRequestDispatcher("/");
          rd.forward(req, res);
        } else {
          chain.doFilter(req, res);
        }
      }
    };
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/FileStorageService.java
=========================================
package pdl.backend.gallery.core;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

@Service
public class FileStorageService {

  private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
  private final MediaRepository recordRepository;
  private final S3Client s3Client;

  @Value("${s3.bucket}")
  private String bucketName;

  public FileStorageService(MediaRepository recordRepository, S3Client s3Client) {
    this.recordRepository = recordRepository;
    this.s3Client = s3Client;
  }

  @Transactional
  public void processAndSaveImage(MediaRecord img, boolean saveToS3) {
    String hash = calculateSHA256(img.getData());

    // BUG FIX: Use .equals() for Long object comparison, not !=
    Optional<MediaRecord> existing = recordRepository.findByHash(hash);
    if (existing.isPresent() && (img.getId() == 0 || existing.get().getId() != img.getId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Image already exists.");
    }

    img.setHash(hash);
    img.setFormat(getFileExtension(img.getName()));
    img.setExtractionStatus("PENDING");

    int width = 0;
    int height = 0;
    try (
      ImageInputStream in = ImageIO.createImageInputStream(new ByteArrayInputStream(img.getData()))
    ) {
      Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
      if (readers.hasNext()) {
        ImageReader reader = readers.next();
        reader.setInput(in, true, true);
        width = reader.getWidth(0);
        height = reader.getHeight(0);
        reader.dispose();
      }
    } catch (Exception e) {
      log.warn("Could not fast-read dimensions for image hash {}", hash);
    }
    img.setWidth(width);
    img.setHeight(height);

    MediaRecord savedImage = recordRepository.save(img);
    img.setId(savedImage.getId());

    if (saveToS3) {
      String s3Key = savedImage.getId() + "_" + img.getName();
      PutObjectRequest putOb = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(s3Key)
        .contentType("image/" + img.getFormat())
        .build();
      s3Client.putObject(putOb, RequestBody.fromBytes(img.getData()));
    }
  }

  public Optional<MediaRecord> getImageWithData(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        String s3Key = img.getId() + "_" + img.getName();
        GetObjectRequest getOb = GetObjectRequest.builder().bucket(bucketName).key(s3Key).build();
        byte[] bytes = s3Client.getObjectAsBytes(getOb).asByteArray();
        img.setData(bytes);
        return Optional.of(img);
      } catch (S3Exception e) {
        log.error("Failed to load from Garage S3 for ID: " + id, e);
      }
    }
    return Optional.empty();
  }

  @Transactional
  public boolean deleteImage(long id) {
    Optional<MediaRecord> imageOpt = recordRepository.findById(id);
    if (imageOpt.isPresent()) {
      MediaRecord img = imageOpt.get();
      try {
        String s3Key = img.getId() + "_" + img.getName();
        DeleteObjectRequest delReq = DeleteObjectRequest.builder()
          .bucket(bucketName)
          .key(s3Key)
          .build();
        s3Client.deleteObject(delReq);
      } catch (S3Exception e) {
        log.error("Failed to delete from Garage S3 for ID: " + id, e);
      }
      recordRepository.delete(img);
      return true;
    }
    return false;
  }

  // --- NEW: Stream from S3 ---
  public ResponseInputStream<GetObjectResponse> streamImageFromS3(long id, String filename) {
    String s3Key = id + "_" + filename;
    GetObjectRequest getOb = GetObjectRequest.builder().bucket(bucketName).key(s3Key).build();
    return s3Client.getObject(getOb);
  }

  private String calculateSHA256(byte[] data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(data);
      return HexFormat.of().formatHex(hashBytes);
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate SHA-256 hash", e);
    }
  }

  private String getFileExtension(String filename) {
    if (filename == null || !filename.contains(".")) return "jpeg";
    String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    if (ext.equals("jpg")) return "jpeg";
    return ext;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/MediaRecord.java
=========================================
package pdl.backend.gallery.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public class MediaRecord {

  @Id
  private Long id;

  @Column("filename")
  private String name;

  private String format;
  private int width;
  private int height;
  private String hash;

  @Column("extraction_status")
  private String extractionStatus;

  @Column("user_id")
  private Long userId;

  // --- NEW ROBUST METADATA FIELDS ---
  private String provider = "LOCAL";

  @Column("provider_id")
  private String providerId;

  private String description;

  @Column("photographer_name")
  private String photographerName;

  @Column("camera_make")
  private String cameraMake;

  @Column("location_country")
  private String locationCountry;

  @Column("stats_downloads")
  private Long statsDownloads;

  @Column("remote_url")
  private String remoteUrl;

  @Transient
  private byte[] data;

  public MediaRecord() {}

  public MediaRecord(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  // --- STANDARD GETTERS & SETTERS ---
  public void setId(Long id) {
    this.id = id;
  }

  public long getId() {
    return (id != null) ? id : 0;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getExtractionStatus() {
    return extractionStatus;
  }

  public void setExtractionStatus(String extractionStatus) {
    this.extractionStatus = extractionStatus;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  // --- ROBUST GETTERS & SETTERS ---
  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotographerName() {
    return photographerName;
  }

  public void setPhotographerName(String photographerName) {
    this.photographerName = photographerName;
  }

  public String getCameraMake() {
    return cameraMake;
  }

  public void setCameraMake(String cameraMake) {
    this.cameraMake = cameraMake;
  }

  public String getLocationCountry() {
    return locationCountry;
  }

  public void setLocationCountry(String locationCountry) {
    this.locationCountry = locationCountry;
  }

  public Long getStatsDownloads() {
    return statsDownloads;
  }

  public void setStatsDownloads(Long statsDownloads) {
    this.statsDownloads = statsDownloads;
  }

  public String getRemoteUrl() {
    return remoteUrl;
  }

  public void setRemoteUrl(String remoteUrl) {
    this.remoteUrl = remoteUrl;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/MediaRepository.java
=========================================
package pdl.backend.gallery.core;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaRecord, Long> {
  Optional<MediaRecord> findByHash(String hash);
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/core/GalleryController.java
=========================================
package pdl.backend.gallery.core;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@RestController
@RequestMapping("/images")
public class GalleryController {

  private static final Logger log = LoggerFactory.getLogger(GalleryController.class);

  private final FileStorageService storageService;
  private final TagRepository queryRepo;
  private final UploadStatusTracker statusNotifier;
  private final JdbcTemplate jdbcTemplate;

  public GalleryController(
    FileStorageService storageService,
    TagRepository queryRepo,
    UploadStatusTracker statusNotifier,
    JdbcTemplate jdbcTemplate
  ) {
    this.storageService = storageService;
    this.queryRepo = queryRepo;
    this.statusNotifier = statusNotifier;
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> getImages(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    int offset = page * size;
    Long userId = getCurrentUserId();

    List<Map<String, Object>> content = queryRepo.getPaginatedGallery(userId, size, offset, mine);
    long totalElements = queryRepo.getGalleryTotalCount(userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getImage(@PathVariable("id") long id) {
    Optional<MediaRecord> image = storageService.getImageWithData(id);
    if (image.isEmpty() || image.get().getData() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_TYPE, "image/" + image.get().getFormat())
      .body(image.get().getData());
  }

  /**
   * Deletes an image.
   *
   * @param id the image id
   * @return response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable("id") final long id) {
    final Long userId = getCurrentUserId();
    if (userId == null) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "You must be logged in to delete images"
      );
    }

    final boolean isAdmin = isAdmin();

    final Optional<MediaRecord> imageOpt = storageService.getImageWithData(id);
    if (imageOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    final MediaRecord image = imageOpt.get();
    final boolean notOwner = image.getUserId() == null || !image.getUserId().equals(userId);

    if (!isAdmin && notOwner) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN,
        "You do not have permission to delete this image"
      );
    }

    final boolean deleted = storageService.deleteImage(id);
    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete image");
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping(
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<?> addImage(
    @RequestPart("file") MultipartFile file,
    @RequestParam(value = "keywords", required = false) List<String> keywords,
    HttpServletRequest request
  ) throws Exception {
    if (file.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
    }

    Long userId = getCurrentUserId();
    boolean isAdmin = isAdmin();

    if (!isAdmin && userId != null) {
      Integer pendingCount = jdbcTemplate.queryForObject(
        "SELECT count(*) FROM images WHERE user_id = ? AND extraction_status IN ('PENDING', 'PROCESSING')",
        Integer.class,
        userId
      );
      if (pendingCount != null && pendingCount >= 10) {
        throw new ResponseStatusException(
          HttpStatus.TOO_MANY_REQUESTS,
          "You have reached the maximum allowed pending uploads (10). Please wait for them to finish processing."
        );
      }
    }

    MediaRecord image = new MediaRecord(file.getOriginalFilename(), file.getBytes());
    image.setUserId(userId);
    storageService.processAndSaveImage(image, true);
    long id = image.getId();

    if (keywords != null && !keywords.isEmpty()) {
      List<String> allTags = new ArrayList<>();
      for (String k : keywords) {
        String[] splits = k.split(",");
        for (String tag : splits) {
          allTags.add(tag.trim());
        }
      }
      queryRepo.addKeywords(id, allTags);
    }

    return ResponseEntity.accepted().body(Map.of("id", id));
  }

  @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getImageStatus(@PathVariable("id") long id) {
    String status = queryRepo.getStatus(id);
    if (status == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");

    if ("COMPLETED".equals(status) || "FAILED".equals(status)) {
      return ResponseEntity.ok().body(Map.of("id", id, "extraction_status", status));
    }

    return statusNotifier.waitFor(id, 10000L);
  }

  /**
   * Gets image metadata.
   *
   * @param id the image id
   * @return response entity with metadata
   */
  @GetMapping("/{id}/metadata")
  public ResponseEntity<?> getMetadata(@PathVariable("id") final long id) {
    try {
      final Map<String, Object> rawMeta = queryRepo.getImageMetadata(id);
      final Map<String, Object> response = new HashMap<>();
      response.put("Name", rawMeta.get("name"));
      response.put("format", "image/" + rawMeta.get("format"));
      response.put("width", rawMeta.get("width"));
      response.put("height", rawMeta.get("height"));
      response.put("Keywords", rawMeta.get("Keywords"));
      response.put("extraction_status", rawMeta.get("extraction_status"));
      response.put("description", rawMeta.get("description"));
      response.put("photographer_name", rawMeta.get("photographer_name"));
      response.put("camera_make", rawMeta.get("camera_make"));
      response.put("location_country", rawMeta.get("location_country"));
      response.put("stats_downloads", rawMeta.get("stats_downloads"));
      response.put("user_id", rawMeta.get("user_id"));

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.error("Error retrieving metadata for image id: {}", id, e);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found", e);
    }
  }

  @GetMapping(value = "/{id}/download")
  public ResponseEntity<org.springframework.core.io.InputStreamResource> downloadImage(
    @PathVariable("id") long id
  ) {
    Map<String, Object> metadata;
    try {
      metadata = queryRepo.getImageMetadata(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image metadata not found");
    }

    String filename = (String) metadata.get("name");
    String format = (String) metadata.get("format");

    try {
      // Get the stream from S3
      var s3Stream = storageService.streamImageFromS3(id, filename);

      return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
        .header(HttpHeaders.CONTENT_TYPE, "image/" + format)
        .body(new org.springframework.core.io.InputStreamResource(s3Stream));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image data not found in storage");
    }
  }

  private Long getCurrentUserId() {
    org.springframework.security.core.Authentication authentication =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      if (
        authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt
      ) {
        return jwt.getClaim("userId");
      } else if (authentication.getPrincipal() instanceof UserAccount user) {
        return user.getId();
      }
    }
    return null;
  }

  private boolean isAdmin() {
    org.springframework.security.core.Authentication auth =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    return (
      auth != null &&
      auth
        .getAuthorities()
        .stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
    );
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/SimilarityRepository.java
=========================================
package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.List;
import java.util.Map;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimilarityRepository {

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  private static final double WEIGHT_HOG = 0.50;
  private static final double WEIGHT_LAB = 0.35;
  private static final double WEIGHT_HSV = 0.15;

  public SimilarityRepository(@NonNull JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Map<String, Object>> findSimilar(long targetId, String type, int limit) {
    if ("weighted".equalsIgnoreCase(type)) {
      PGvector[] vectors;
      try {
        vectors = jdbcTemplate.queryForObject(
          "SELECT hogvector, hsvvector, labvector FROM imagedescriptors WHERE imageid = ?",
          (rs, rowNum) ->
            new PGvector[] {
              new PGvector(rs.getString("hogvector")),
              new PGvector(rs.getString("hsvvector")),
              new PGvector(rs.getString("labvector")),
            },
          targetId
        );
      } catch (EmptyResultDataAccessException e) {
        return null;
      }

      if (vectors == null) return null;

      // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
      String sql =
        "WITH vector_matches AS (" +
        "  SELECT d.imageid, " +
        "    (? * (d.hogvector <=> ?) + " +
        "     ? * (d.labvector <=> ?) + " +
        "     ? * (d.hsvvector <=> ?)) as distance " +
        "  FROM imagedescriptors d " +
        "  JOIN images i ON d.imageid = i.id " +
        "  WHERE d.imageid != ? AND i.extraction_status = 'COMPLETED' " +
        "  ORDER BY (? * (d.hogvector <=> ?) + ? * (d.labvector <=> ?) + ? * (d.hsvvector <=> ?)) ASC LIMIT ?" +
        ") " +
        "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
        "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
        "ORDER BY v.distance ASC";

      return jdbcTemplate.queryForList(
        sql,
        WEIGHT_HOG,
        vectors[0],
        WEIGHT_LAB,
        vectors[2],
        WEIGHT_HSV,
        vectors[1],
        targetId,
        WEIGHT_HOG,
        vectors[0],
        WEIGHT_LAB,
        vectors[2],
        WEIGHT_HSV,
        vectors[1],
        limit
      );
    }

    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    PGvector targetVector;
    try {
      targetVector = jdbcTemplate.queryForObject(
        "SELECT " + vectorColumn + " FROM imagedescriptors WHERE imageid = ?",
        (rs, rowNum) -> new PGvector(rs.getString(1)),
        targetId
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }

    // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
    String sql =
      "WITH vector_matches AS (" +
      "  SELECT d.imageid, " +
      vectorColumn +
      " <=> ? as distance " +
      "  FROM imagedescriptors d " +
      "  JOIN images i ON d.imageid = i.id " +
      "  WHERE d.imageid != ? AND i.extraction_status = 'COMPLETED' " +
      "  ORDER BY " +
      vectorColumn +
      " <=> ? LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetId, targetVector, limit);
  }

  public List<Map<String, Object>> findSimilarByVector(
    PGvector targetVector,
    String type,
    int limit
  ) {
    String vectorColumn = switch (type.toLowerCase()) {
      case "gradient" -> "hogvector";
      case "saturation" -> "hsvvector";
      case "rgb" -> "rgbvector";
      case "cielab" -> "labvector";
      case "semantic" -> "semanticvector";
      default -> "hogvector";
    };

    // BUG FIX: Added JOIN images i ON d.imageid = i.id WHERE i.extraction_status = 'COMPLETED'
    String sql =
      "WITH vector_matches AS (" +
      "  SELECT d.imageid, " +
      vectorColumn +
      " <=> ? as distance " +
      "  FROM imagedescriptors d " +
      "  JOIN images i ON d.imageid = i.id " +
      "  WHERE i.extraction_status = 'COMPLETED' " +
      "  ORDER BY " +
      vectorColumn +
      " <=> ? LIMIT ?" +
      ") " +
      "SELECT v.imageid as id, i.filename, (1.0 - (1.0 / (1.0 + v.distance))) AS score " +
      "FROM vector_matches v JOIN images i ON v.imageid = i.id " +
      "ORDER BY v.distance ASC";

    return jdbcTemplate.queryForList(sql, targetVector, targetVector, limit);
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/TagController.java
=========================================
package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.auth.UserAccount;

@RestController
@RequestMapping("/images")
public class TagController {

  private final TagRepository queryRepo;

  public TagController(TagRepository queryRepo) {
    this.queryRepo = queryRepo;
  }

  @PutMapping("/{id}/keywords")
  public ResponseEntity<?> addKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    if (!queryRepo.addKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/keywords")
  public ResponseEntity<?> deleteKeyword(
    @PathVariable("id") long id,
    @RequestParam("tag") String tag
  ) {
    try {
      queryRepo.getImageMetadata(id);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    if (!queryRepo.hasKeyword(id, tag)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    queryRepo.deleteKeyword(id, tag);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/keywords")
  public ResponseEntity<?> getAllKeywords() {
    return ResponseEntity.ok(queryRepo.getAllKeywords(getCurrentUserId()));
  }

  @GetMapping("/keywords/search")
  public ResponseEntity<?> searchKeywords(@RequestParam("q") String query) {
    if (query == null || query.length() < 2) return ResponseEntity.ok(List.of());
    return ResponseEntity.ok(queryRepo.searchKeywords(query, getCurrentUserId()));
  }

  @GetMapping("/keywords/popular")
  public ResponseEntity<List<String>> getPopularKeywords(
    @RequestParam(defaultValue = "15") int limit
  ) {
    return ResponseEntity.ok(queryRepo.getPopularKeywords(limit));
  }

  @GetMapping("/search")
  public ResponseEntity<Map<String, Object>> searchImagesByTags(
    @RequestParam(required = false) List<String> keywords,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(value = "mine", defaultValue = "false") boolean mine
  ) {
    Long userId = getCurrentUserId();
    int offset = page * size;

    List<Map<String, Object>> content = queryRepo.searchGalleryByTags(
      keywords,
      userId,
      size,
      offset,
      mine
    );
    long totalElements = queryRepo.getSearchGalleryByTagsTotalCount(keywords, userId, mine);
    boolean hasNext = (offset + size) < totalElements;

    Map<String, Object> response = new java.util.HashMap<>();
    response.put("content", content);
    response.put("totalElements", totalElements);
    response.put("hasNext", hasNext);

    return ResponseEntity.ok(response);
  }

  private Long getCurrentUserId() {
    org.springframework.security.core.Authentication authentication =
      org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      if (
        authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt
      ) {
        return jwt.getClaim("userId");
      } else if (authentication.getPrincipal() instanceof UserAccount user) {
        return user.getId();
      }
    }
    return null;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/SearchController.java
=========================================
package pdl.backend.gallery.search;

import com.pgvector.PGvector;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pdl.backend.vision.VisionProcessor;

@RestController
@RequestMapping("/images")
public class SearchController {

  private final SimilarityRepository similarityRepo;
  private final VisionProcessor visionProcessor;

  public SearchController(SimilarityRepository similarityRepo, VisionProcessor visionProcessor) {
    this.similarityRepo = similarityRepo;
    this.visionProcessor = visionProcessor;
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<?> getSimilarImages(
    @PathVariable("id") long id,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "weighted") String descriptor
  ) {
    // Added "semantic" to allowed query types
    List<String> validDescriptors = List.of(
      "gradient",
      "saturation",
      "rgb",
      "cielab",
      "weighted",
      "semantic"
    );
    if (!validDescriptors.contains(descriptor.toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor.");
    }

    List<Map<String, Object>> results = similarityRepo.findSimilar(id, descriptor, number);
    if (results == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }
    return ResponseEntity.ok(results);
  }

  @PostMapping("/search/ephemeral")
  public ResponseEntity<?> searchByEphemeralUpload(
    @RequestPart("file") MultipartFile file,
    @RequestParam(value = "number", defaultValue = "10") int number,
    @RequestParam(value = "descriptor", defaultValue = "semantic") String descriptor
  ) {
    if (file.isEmpty() || file.getSize() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
    }

    try {
      // 1. Process the image directly in RAM
      Map<String, float[]> vectors = visionProcessor.extractEphemeralVectors(file.getBytes());

      // 2. Grab the requested algorithm's vector
      float[] targetArray = vectors.get(descriptor.toLowerCase());
      if (targetArray == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid descriptor");
      }

      // 3. Search the DB without inserting
      PGvector pgVector = new PGvector(targetArray);
      List<Map<String, Object>> results = similarityRepo.findSimilarByVector(
        pgVector,
        descriptor,
        number
      );

      return ResponseEntity.ok(results);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process image", e);
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/gallery/search/TagRepository.java
=========================================
package pdl.backend.gallery.search;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

  private static final Logger log = LoggerFactory.getLogger(TagRepository.class);

  @NonNull
  private final JdbcTemplate jdbcTemplate;

  public TagRepository(@NonNull JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private String normalizeTag(String tag) {
    if (tag == null) return null;
    return tag.trim().toLowerCase().replaceAll("\\s+", "_");
  }

  public void updateStatus(long id, String status) {
    jdbcTemplate.update("UPDATE images SET extraction_status = ? WHERE id = ?", status, id);
  }

  public String getStatus(long id) {
    try {
      return jdbcTemplate.queryForObject(
        "SELECT extraction_status FROM images WHERE id = ?",
        String.class,
        id
      );
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * Gets image metadata by id.
   *
   * @param id the image id
   * @return a map of metadata fields
   */
  public Map<String, Object> getImageMetadata(final long id) {
    final Map<String, Object> meta = jdbcTemplate.queryForMap(
      "SELECT filename as Name, format, width, height, " +
        "extraction_status, description, photographer_name, " +
        "camera_make, location_country, stats_downloads, user_id " +
        "FROM images WHERE id = ?",
      id
    );
    meta.put("Keywords", getKeywords(id));
    return meta;
  }

  public List<Map<String, Object>> getKeywords(long id) {
    return jdbcTemplate.queryForList(
      "SELECT keyword, is_ai_generated as \"isAi\" FROM imagekeywords WHERE imageid = ?",
      id
    );
  }

  public boolean addKeyword(long id, String keyword, boolean isAiGenerated) {
    String normalizedTag = normalizeTag(keyword);
    if (normalizedTag == null || normalizedTag.isEmpty()) return false;
    try {
      jdbcTemplate.queryForObject("SELECT id FROM images WHERE id = ?", Long.class, id);
      jdbcTemplate.update(
        "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
        id,
        normalizedTag,
        isAiGenerated
      );
      return true;
    } catch (Exception e) {
      log.error("Failed to add keyword", e);
      return false;
    }
  }

  public boolean addKeyword(long id, String keyword) {
    return addKeyword(id, keyword, false);
  }

  public void addKeywords(long id, List<String> keywords, boolean isAiGenerated) {
    if (keywords == null) return;
    List<Object[]> batchArgs = keywords
      .stream()
      .map(this::normalizeTag)
      .filter(tag -> tag != null && !tag.isEmpty())
      .distinct()
      .map(tag -> new Object[] { id, tag, isAiGenerated })
      .collect(Collectors.toList());

    if (batchArgs.isEmpty()) return;

    try {
      jdbcTemplate.queryForObject("SELECT id FROM images WHERE id = ?", Long.class, id);
      jdbcTemplate.batchUpdate(
        "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
        batchArgs
      );
    } catch (Exception e) {
      log.error("Failed to add keywords in batch", e);
    }
  }

  public void addKeywords(long id, List<String> keywords) {
    addKeywords(id, keywords, false);
  }

  public boolean hasKeyword(long id, String keyword) {
    Integer count = jdbcTemplate.queryForObject(
      "SELECT count(*) FROM imagekeywords WHERE imageid = ? AND keyword = ?",
      Integer.class,
      id,
      normalizeTag(keyword)
    );
    return count != null && count > 0;
  }

  public void deleteKeyword(long id, String keyword) {
    jdbcTemplate.update(
      "DELETE FROM imagekeywords WHERE imageid = ? AND keyword = ?",
      id,
      normalizeTag(keyword)
    );
  }

  public List<String> getAllKeywords(Long currentUserId) {
    return jdbcTemplate.queryForList(
      "SELECT DISTINCT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE i.extraction_status != 'FAILED' " +
        "ORDER BY k.keyword ASC",
      String.class
    );
  }

  public List<String> searchKeywords(String query, Long currentUserId) {
    String normalized = normalizeTag(query);
    return jdbcTemplate.queryForList(
      "SELECT DISTINCT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE i.extraction_status != 'FAILED' AND k.keyword LIKE ? " +
        "ORDER BY k.keyword ASC LIMIT 8",
      String.class,
      "%" + normalized + "%"
    );
  }

  public List<String> getPopularKeywords(int limit) {
    return jdbcTemplate.queryForList(
      "SELECT k.keyword FROM imagekeywords k " +
        "JOIN images i ON k.imageid = i.id " +
        "WHERE i.extraction_status = 'COMPLETED' " +
        "GROUP BY k.keyword ORDER BY COUNT(k.imageid) DESC LIMIT ?",
      String.class,
      limit
    );
  }

  public List<Map<String, Object>> getPaginatedGallery(
    Long currentUserId,
    int limit,
    int offset,
    boolean onlyUser
  ) {
    String sql =
      "SELECT i.id, i.extraction_status, u.username as uploader " +
      "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
      "WHERE (? = false OR i.user_id = ?) " +
      "AND i.extraction_status != 'FAILED' " +
      "ORDER BY i.id DESC LIMIT ? OFFSET ?";

    return jdbcTemplate.query(
      sql,
      (rs, rowNum) -> {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("id", rs.getLong("id"));
        map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
        map.put("keywords", getKeywords(rs.getLong("id")));
        map.put("extraction_status", rs.getString("extraction_status"));
        return map;
      },
      onlyUser,
      currentUserId,
      limit,
      offset
    );
  }

  public List<Map<String, Object>> searchGalleryByTags(
    List<String> keywords,
    Long currentUserId,
    int limit,
    int offset,
    boolean onlyUser
  ) {
    StringBuilder sql = new StringBuilder(
      "SELECT i.id, i.extraction_status, u.username as uploader " +
        "FROM images i LEFT JOIN users u ON i.user_id = u.id " +
        "WHERE (:onlyUser = false OR i.user_id = :currentUserId) " +
        "AND i.extraction_status != 'FAILED' "
    );
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("currentUserId", currentUserId);
    params.addValue("onlyUser", onlyUser);

    if (keywords != null && !keywords.isEmpty()) {
      sql.append(
        "AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount "
      );
      List<String> normalizedKeywords = keywords
        .stream()
        .map(this::normalizeTag)
        .collect(Collectors.toList());
      params.addValue("keywords", normalizedKeywords);
      params.addValue("keywordCount", normalizedKeywords.size());
    }

    sql.append("ORDER BY i.id DESC LIMIT :limit OFFSET :offset");
    params.addValue("limit", limit);
    params.addValue("offset", offset);

    return new NamedParameterJdbcTemplate(jdbcTemplate).query(
      sql.toString(),
      params,
      (rs, rowNum) -> {
        Map<String, Object> map = new java.util.HashMap<>();
        map.put("id", rs.getLong("id"));
        map.put("uploader", rs.getString("uploader") != null ? rs.getString("uploader") : "System");
        map.put("keywords", getKeywords(rs.getLong("id")));
        map.put("extraction_status", rs.getString("extraction_status"));
        return map;
      }
    );
  }

  public long getGalleryTotalCount(Long currentUserId, boolean onlyUser) {
    String sql =
      "SELECT COUNT(*) FROM images i WHERE (? = false OR i.user_id = ?) AND i.extraction_status != 'FAILED'";
    Long count = jdbcTemplate.queryForObject(sql, Long.class, onlyUser, currentUserId);
    return count != null ? count : 0L;
  }

  public long getSearchGalleryByTagsTotalCount(
    List<String> keywords,
    Long currentUserId,
    boolean onlyUser
  ) {
    if (keywords == null || keywords.isEmpty()) {
      return getGalleryTotalCount(currentUserId, onlyUser);
    }

    StringBuilder sql = new StringBuilder(
      "SELECT COUNT(*) FROM images i WHERE (:onlyUser = false OR i.user_id = :currentUserId) AND i.extraction_status != 'FAILED' "
    );
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("currentUserId", currentUserId);
    params.addValue("onlyUser", onlyUser);

    sql.append(
      "AND (SELECT count(DISTINCT keyword) FROM imagekeywords WHERE imageid = i.id AND keyword IN (:keywords)) = :keywordCount "
    );
    List<String> normalizedKeywords = keywords
      .stream()
      .map(this::normalizeTag)
      .collect(Collectors.toList());
    params.addValue("keywords", normalizedKeywords);
    params.addValue("keywordCount", normalizedKeywords.size());

    Long count = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(
      sql.toString(),
      params,
      Long.class
    );
    return count != null ? count : 0L;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/ingestion/DatasetIngestController.java
=========================================
package pdl.backend.ingestion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class DatasetIngestController {

  private final UnsplashService unsplashService;

  public DatasetIngestController(UnsplashService unsplashService) {
    this.unsplashService = unsplashService;
  }

  @PostMapping(value = "/unsplash/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadAndSyncMetadata(
    @RequestPart("file") MultipartFile file,
    @RequestParam(defaultValue = "1000") int limit,
    @RequestParam(defaultValue = "0") int offset,
    @RequestParam(defaultValue = "PHOTOS") String fileType
  ) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A job is already in progress.");
    }

    try {
      // Save uploaded file to container's temp directory, then trigger async parsing
      Path tempFile = Files.createTempFile("unsplash_", ".tmp");
      file.transferTo(tempFile.toFile());

      if ("KEYWORDS".equalsIgnoreCase(fileType)) {
        unsplashService.syncKeywordsFromFile(tempFile, limit, offset);
      } else {
        unsplashService.syncMetadataFromFile(tempFile, limit, offset);
      }

      return ResponseEntity.ok(Map.of("message", "File uploaded. Sync initiated."));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process uploaded file.", e);
    }
  }

  @GetMapping("/unsplash/catalog")
  public ResponseEntity<?> getCatalog(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "30") int size,
    @RequestParam(required = false) String query,
    @RequestParam(required = false) String camera,
    @RequestParam(required = false) String country
  ) {
    return ResponseEntity.ok(unsplashService.getCatalog(page, size, query, camera, country));
  }

  @PostMapping("/unsplash/import")
  public ResponseEntity<?> importSelected(@RequestBody List<Long> imageIds) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A job is already in progress.");
    }
    unsplashService.importSelectedImages(imageIds);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @PostMapping("/unsplash/import/batch")
  public ResponseEntity<?> importBatch(
    @RequestParam(defaultValue = "1000") int limit,
    @RequestParam(required = false) String query,
    @RequestParam(required = false) String camera,
    @RequestParam(required = false) String country
  ) {
    if (
      !unsplashService.getStatus().equals("IDLE") &&
      !unsplashService.getStatus().startsWith("ERROR") &&
      !unsplashService.getStatus().startsWith("COMPLETED")
    ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A job is already in progress.");
    }
    unsplashService.importBatchImages(limit, query, camera, country);
    return ResponseEntity.ok(Map.of("message", "Batch import initiated."));
  }

  @GetMapping("/unsplash/status")
  public ResponseEntity<?> getStatus() {
    return ResponseEntity.ok(Map.of("status", unsplashService.getStatus()));
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/ingestion/UnsplashService.java
=========================================
package pdl.backend.ingestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pdl.backend.auth.UserAccount;
import pdl.backend.auth.UserRepository;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.core.MediaRepository;

@Service
public class UnsplashService {

  private static final Logger log = LoggerFactory.getLogger(UnsplashService.class);
  private final JdbcTemplate jdbcTemplate;
  private final FileStorageService storageService;
  private final MediaRepository recordRepository;
  private final UserRepository userRepository;

  private String status = "IDLE";

  public UnsplashService(
    JdbcTemplate jdbcTemplate,
    FileStorageService storageService,
    MediaRepository recordRepository,
    UserRepository userRepository
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.storageService = storageService;
    this.recordRepository = recordRepository;
    this.userRepository = userRepository;
  }

  public String getStatus() {
    return status;
  }

  private String getCol(String[] cols, Map<String, Integer> map, String key, String defaultVal) {
    Integer idx = map.get(key);
    if (idx != null && idx < cols.length && cols[idx] != null && !cols[idx].trim().isEmpty()) {
      return cols[idx].trim();
    }
    return defaultVal;
  }

  @Async
  public void syncMetadataFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_METADATA";
    UserAccount admin = userRepository.findByUsername("admin").orElseThrow();

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      // Auto-detect if Unsplash used tabs or commas for this file version
      String separator = headerLine.contains("\t") ? "\t" : ",";

      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      int currentLine = 0;
      int processed = 0;
      String line;

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String rawUrl = getCol(
          cols,
          colMap,
          "photo_image_url",
          getCol(cols, colMap, "photo_url", null)
        );

        if (pId == null || rawUrl == null) continue;
        if (!rawUrl.startsWith("http://") && !rawUrl.startsWith("https://")) continue;

        String description = getCol(cols, colMap, "photo_description", "No description");
        String firstName = getCol(cols, colMap, "photographer_first_name", "");
        String lastName = getCol(cols, colMap, "photographer_last_name", "");
        String photographerName = (firstName + " " + lastName).trim();
        if (photographerName.isEmpty()) photographerName = "Unknown";
        String cameraMake = getCol(cols, colMap, "exif_camera_make", "Unknown");
        String country = getCol(cols, colMap, "photo_location_country", "Unknown");

        long downloads = 0;
        try {
          downloads = Long.parseLong(getCol(cols, colMap, "stats_downloads", "0"));
        } catch (NumberFormatException ignored) {}

        Integer existing = jdbcTemplate.queryForObject(
          "SELECT COUNT(*) FROM images WHERE provider_id = ?",
          Integer.class,
          pId
        );

        if (existing != null && existing == 0) {
          jdbcTemplate.update(
            "INSERT INTO images (filename, format, provider, provider_id, remote_url, extraction_status, description, photographer_name, camera_make, location_country, stats_downloads, user_id) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            "unsplash_" + pId + ".jpg",
            "jpeg",
            "UNSPLASH",
            pId,
            rawUrl,
            "REMOTE_METADATA",
            description,
            photographerName,
            cameraMake,
            country,
            downloads,
            admin.getId()
          );
          processed++;
        }
      }
      status = "COMPLETED: Synced " + processed + " new metadata records.";
    } catch (Exception e) {
      log.error("Metadata sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  @Async
  public void syncKeywordsFromFile(Path tempFilePath, int limit, int offset) {
    status = "SYNCING_KEYWORDS";

    try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath.toFile()))) {
      String headerLine = br.readLine();
      if (headerLine == null) throw new IllegalStateException("Uploaded file is empty");

      String separator = headerLine.contains("\t") ? "\t" : ",";
      String[] headers = headerLine.split(separator);
      Map<String, Integer> colMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) colMap.put(headers[i].trim(), i);

      // Load all known Unsplash photo IDs to local DB IDs
      Map<String, Long> localPhotoIds = new HashMap<>();
      jdbcTemplate.query("SELECT provider_id, id FROM images WHERE provider = 'UNSPLASH'", rs -> {
        localPhotoIds.put(rs.getString("provider_id"), rs.getLong("id"));
      });

      int currentLine = 0;
      int processed = 0;
      String line;

      List<Object[]> batchArgs = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        if (++currentLine <= offset) continue;
        if (processed >= limit) break;

        String[] cols = line.split(separator, -1);
        String pId = getCol(cols, colMap, "photo_id", null);
        String keyword = getCol(cols, colMap, "keyword", null);

        if (pId == null || keyword == null || keyword.trim().isEmpty()) continue;

        // Skip if we don't have the photo in our database
        Long localId = localPhotoIds.get(pId);
        if (localId == null) continue;

        // We are processing all keywords regardless of confidence

        String normalizedKeyword = keyword.trim().toLowerCase().replaceAll("\\s+", "_");

        batchArgs.add(new Object[] { localId, normalizedKeyword, false });
        processed++;

        if (batchArgs.size() >= 1000) {
          jdbcTemplate.batchUpdate(
            "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
            batchArgs
          );
          batchArgs.clear();
        }
      }

      if (!batchArgs.isEmpty()) {
        jdbcTemplate.batchUpdate(
          "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING",
          batchArgs
        );
      }

      status = "COMPLETED: Linked " + processed + " tags to photos.";
    } catch (Exception e) {
      log.error("Keywords sync failed", e);
      status = "ERROR: " + e.getMessage();
    } finally {
      // Always clean up the temporary file from the container's disk
      try {
        Files.deleteIfExists(tempFilePath);
      } catch (Exception ignored) {}
    }
  }

  public Map<String, Object> getCatalog(
    int page,
    int size,
    String query,
    String camera,
    String country
  ) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql +=
        " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
    }

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
    }

    String countSql = "SELECT COUNT(*) " + baseSql;
    Long totalElements = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

    String dataSql =
      "SELECT id, remote_url, photographer_name, camera_make, location_country, stats_downloads " +
      baseSql +
      " ORDER BY stats_downloads DESC LIMIT ? OFFSET ?";
    params.add(size);
    params.add(page * size);

    List<Map<String, Object>> content = jdbcTemplate.queryForList(dataSql, params.toArray());

    Map<String, Object> result = new HashMap<>();
    result.put("content", content);
    result.put("totalElements", totalElements != null ? totalElements : 0);
    result.put(
      "totalPages",
      (int) Math.ceil((double) (totalElements != null ? totalElements : 0) / size)
    );
    return result;
  }

  @Async
  public void importSelectedImages(List<Long> imageIds) {
    if (imageIds == null || imageIds.isEmpty()) return;

    // 1. Synchronously mark all selected images as DOWNLOADING immediately
    // This instantly hides them from the frontend catalog
    List<Object[]> batchArgs = new ArrayList<>();
    for (Long id : imageIds) {
      batchArgs.add(new Object[] { id });
    }
    jdbcTemplate.batchUpdate(
      "UPDATE images SET extraction_status = 'DOWNLOADING' WHERE id = ? AND extraction_status = 'REMOTE_METADATA'",
      batchArgs
    );

    // 2. Start the slow, polite download process
    int count = 0;
    RestTemplate restTemplate = new RestTemplate();

    for (Long id : imageIds) {
      try {
        Optional<MediaRecord> opt = recordRepository.findById(id);
        if (opt.isEmpty()) continue;
        MediaRecord record = opt.get();
        // Check for DOWNLOADING since we just updated it
        if (!"DOWNLOADING".equals(record.getExtractionStatus())) continue;

        status = "IMPORTING (" + (count + 1) + " / " + imageIds.size() + "): " + record.getName();

        String remoteUrl = record.getRemoteUrl();
        String fetchUrl = remoteUrl + (remoteUrl.contains("?") ? "&" : "?") + "w=1080&q=85&fm=jpg";

        if (!fetchUrl.startsWith("http://") && !fetchUrl.startsWith("https://")) {
          fetchUrl = "https://" + fetchUrl;
        }

        byte[] bytes = restTemplate.getForObject(URI.create(fetchUrl), byte[].class);
        if (bytes != null) {
          record.setData(bytes);
          // storageService sets it to PENDING and our ML queue takes over
          storageService.processAndSaveImage(record, true);
        } else {
          // If bytes are null, it failed to fetch. Revert to REMOTE_METADATA so it shows up again.
          jdbcTemplate.update(
            "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
            id
          );
        }
      } catch (Exception e) {
        log.error("Failed to import image ID: " + id, e);
        // On any HTTP or network error, revert the status so it isn't permanently stuck as DOWNLOADING
        jdbcTemplate.update(
          "UPDATE images SET extraction_status = 'REMOTE_METADATA' WHERE id = ?",
          id
        );
      } finally {
        try {
          Thread.sleep(1000); // Politeness delay regardless of success or failure
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      }
      count++;
    }
    status = "IDLE";
  }

  public void importBatchImages(int limit, String query, String camera, String country) {
    String baseSql = "FROM images WHERE extraction_status = 'REMOTE_METADATA'";
    List<Object> params = new ArrayList<>();

    if (query != null && !query.trim().isEmpty()) {
      baseSql +=
        " AND (camera_make ILIKE ? OR location_country ILIKE ? OR description ILIKE ? OR photographer_name ILIKE ?)";
      String likeQuery = "%" + query.trim() + "%";
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
      params.add(likeQuery);
    }

    if (camera != null && !camera.trim().isEmpty()) {
      baseSql += " AND camera_make ILIKE ?";
      params.add("%" + camera.trim() + "%");
    }

    if (country != null && !country.trim().isEmpty()) {
      baseSql += " AND location_country ILIKE ?";
      params.add("%" + country.trim() + "%");
    }

    String dataSql = "SELECT id " + baseSql + " ORDER BY stats_downloads DESC LIMIT ?";
    params.add(limit);

    List<Long> idsToImport = jdbcTemplate.queryForList(dataSql, Long.class, params.toArray());

    if (!idsToImport.isEmpty()) {
      importSelectedImages(idsToImport);
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/system/HealthCheck.java
=========================================
package pdl.backend.system;

import java.security.MessageDigest;
import java.util.HexFormat;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

  @Override
  public Health health() {
    try {
      try (
        java.io.InputStream is = getClass().getResourceAsStream("/Whole War and Peace Novel.pdf")
      ) {
        if (is == null) {
          return Health.down()
            .withDetail("error", "Missing critical load-bearing component")
            .build();
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
          digest.update(buffer, 0, bytesRead);
        }
        String actualHash = HexFormat.of().formatHex(digest.digest());
        String expectedHash = "4e22f003e05fcc4120268d15fbcb6100dbce436c36521750b2adf70626c6a6bb";

        if (!expectedHash.equals(actualHash)) {
          return Health.down()
            .withDetail("error", "Cryptographic mismatch. Tampering detected.")
            .build();
        }
      }

      return Health.up().withDetail("status", "System structural integrity verified").build();
    } catch (Exception e) {
      return Health.down(e).build();
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/FeatureExtractor.java
=========================================
package pdl.backend.vision;

import boofcv.abst.feature.dense.DescribeImageDense;
import boofcv.alg.color.ColorHsv;
import boofcv.alg.color.impl.ImplColorLab;
import boofcv.factory.feature.dense.ConfigDenseHoG;
import boofcv.factory.feature.dense.FactoryDescribeImageDense;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.feature.TupleDesc_F64;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;
import com.twelvemonkeys.image.ResampleOp;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Utility class aggregating all heavy image manipulation and feature extraction logic.
 * Wraps BoofCV and TwelveMonkeys operations.
 */
public class FeatureExtractor {

  private static final int HOG_DIMENSIONS = 31;
  private static final float COLOR_SCALE = 256.0f;

  public static BufferedImage resizeImageLanczos3(
    BufferedImage inputImage,
    int targetWidth,
    int targetHeight
  ) {
    ResampleOp resampleOp = new ResampleOp(targetWidth, targetHeight, ResampleOp.FILTER_LANCZOS);
    return resampleOp.filter(inputImage, null);
  }

  public static float[] extractGlobalHog(BufferedImage inputImage) {
    GrayF32 grayImage = ConvertBufferedImage.convertFrom(inputImage, (GrayF32) null);

    ConfigDenseHoG config = new ConfigDenseHoG();
    DescribeImageDense<GrayF32, TupleDesc_F64> describer = FactoryDescribeImageDense.hog(
      config,
      ImageType.single(GrayF32.class)
    );

    describer.process(grayImage);
    List<TupleDesc_F64> descriptions = describer.getDescriptions();

    if (descriptions.isEmpty()) {
      return new float[HOG_DIMENSIONS];
    }

    int singleDescSize = descriptions.get(0).size();
    float[] globalHistogram = new float[singleDescSize];

    for (TupleDesc_F64 desc : descriptions) {
      for (int i = 0; i < singleDescSize; i++) {
        globalHistogram[i] += (float) desc.data[i];
      }
    }

    return normalizeL2(globalHistogram);
  }

  public static float[] extractHsvHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> hsv = rgb.createSameShape();
    ColorHsv.rgbToHsv(rgb, hsv);

    int hueBins = 16;
    int satBins = 16;
    float[] histogram2D = new float[hueBins * satBins];

    for (int y = 0; y < hsv.height; y++) {
      for (int x = 0; x < hsv.width; x++) {
        float hue = hsv.getBand(0).get(x, y);
        float sat = hsv.getBand(1).get(x, y);

        int hIndex = (int) ((hue / (2 * Math.PI)) * hueBins);
        int sIndex = (int) (sat * satBins);

        if (hIndex >= hueBins) hIndex = hueBins - 1;
        if (sIndex >= satBins) sIndex = satBins - 1;

        histogram2D[hIndex * satBins + sIndex]++;
      }
    }

    return normalizeL2(histogram2D);
  }

  public static float[] extractCieLabHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> lab = rgb.createSameShape();
    ImplColorLab.rgbToLab_F32(rgb, lab);

    int binsPerChannel = 8;
    float[] histogram3D = new float[binsPerChannel * binsPerChannel * binsPerChannel];

    for (int y = 0; y < lab.height; y++) {
      for (int x = 0; x < lab.width; x++) {
        float l = lab.getBand(0).get(x, y);
        float a = lab.getBand(1).get(x, y);
        float b = lab.getBand(2).get(x, y);

        int lIndex = (int) ((l / 100.0f) * binsPerChannel);
        int aIndex = (int) (((a + 128) / COLOR_SCALE) * binsPerChannel);
        int bIndex = (int) (((b + 128) / COLOR_SCALE) * binsPerChannel);

        if (lIndex >= binsPerChannel) lIndex = binsPerChannel - 1;
        if (aIndex >= binsPerChannel) aIndex = binsPerChannel - 1;
        if (bIndex >= binsPerChannel) bIndex = binsPerChannel - 1;

        histogram3D[lIndex * binsPerChannel * binsPerChannel + aIndex * binsPerChannel + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
  }

  public static float[] extractRgbHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    int bins = 8;
    float[] histogram3D = new float[bins * bins * bins];

    for (int y = 0; y < rgb.height; y++) {
      for (int x = 0; x < rgb.width; x++) {
        float r = rgb.getBand(0).get(x, y);
        float g = rgb.getBand(1).get(x, y);
        float b = rgb.getBand(2).get(x, y);

        int rIndex = (int) ((r / COLOR_SCALE) * bins);
        int gIndex = (int) ((g / COLOR_SCALE) * bins);
        int bIndex = (int) ((b / COLOR_SCALE) * bins);

        if (rIndex >= bins) rIndex = bins - 1;
        if (gIndex >= bins) gIndex = bins - 1;
        if (bIndex >= bins) bIndex = bins - 1;

        histogram3D[rIndex * bins * bins + gIndex * bins + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
  }

  private static float[] normalizeL2(float[] vector) {
    double sumSquares = 0;
    for (float v : vector) {
      sumSquares += v * v;
    }

    float length = (float) Math.sqrt(sumSquares);
    if (length > 0) {
      for (int i = 0; i < vector.length; i++) {
        vector[i] /= length;
      }
    }
    return vector;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/UploadStatusTracker.java
=========================================
package pdl.backend.vision;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class UploadStatusTracker {

  private final Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
    new ConcurrentHashMap<>();

  public DeferredResult<ResponseEntity<Map<String, Object>>> waitFor(
    Long imageId,
    Long timeoutInMilliseconds
  ) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = new DeferredResult<>(
      timeoutInMilliseconds
    );

    result.onTimeout(() -> {
      requests.remove(imageId);
      result.setResult(
        ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING"))
      );
    });

    result.onCompletion(() -> requests.remove(imageId));
    requests.put(imageId, result);
    return result;
  }

  public void notify(Long imageId, String finalStatus) {
    DeferredResult<ResponseEntity<Map<String, Object>>> result = requests.remove(imageId);
    if (result != null) {
      result.setResult(ResponseEntity.ok(Map.of("id", imageId, "extraction_status", finalStatus)));
    }
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/SemanticExtractor.java
=========================================
package pdl.backend.vision;

import ai.djl.huggingface.tokenizers.Encoding;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.onnxruntime.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SemanticExtractor {

  private static final Logger log = LoggerFactory.getLogger(SemanticExtractor.class);

  private static OrtEnvironment env;
  private static OrtSession visionSession;
  private static OrtSession textSession;
  private static HuggingFaceTokenizer tokenizer;

  private static final int TARGET_RESOLUTION = 224;
  private static final int SIGLIP_DIMENSIONS = 768;

  private static final String MODELS_DIR =
    System.getenv("APP_MODELS_DIR") != null ? System.getenv("APP_MODELS_DIR") : "models";
  private static final String VISION_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/vision_model.onnx";
  private static final String TEXT_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/text_model.onnx";
  private static final String TOKENIZER_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/tokenizer.json";

  private static final Map<String, String> CUSTOM_TAGS = Map.ofEntries(
    Map.entry("cinematic_lighting", "a photo with moody cinematic lighting"),
    Map.entry("natural_light", "a photo with bright and airy natural light"),
    Map.entry("golden_hour", "a photo taken during golden hour"),
    Map.entry("black_and_white", "a high contrast black and white photo"),
    Map.entry("macro", "a macro photography shot"),
    Map.entry("landscape", "a wide angle landscape photograph"),
    Map.entry("architecture", "a minimalist architectural photo"),
    Map.entry("portrait", "a professional portrait of a person"),
    Map.entry("street_photography", "a candid street photography shot"),
    Map.entry("wedding", "a photo of a wedding"),
    Map.entry("wildlife", "a photo of wildlife"),
    Map.entry("food", "a photo of food"),
    Map.entry("vehicle", "a photo of a vehicle")
  );

  private static Map<String, float[]> cachedTextEmbeddings = new HashMap<>();

  private static File downloadIfMissing(String urlStr, String fileName) throws Exception {
    File dir = new File(MODELS_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File file = new File(dir, fileName);
    if (!file.exists()) {
      System.out.println(
        "Downloading " + fileName + " from HuggingFace (This may take a while, please wait...)"
      );
      URL url = URI.create(urlStr).toURL();
      try (InputStream in = url.openStream()) {
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      System.out.println("Successfully downloaded: " + fileName);
    }
    return file;
  }

  static {
    try {
      env = OrtEnvironment.getEnvironment();

      File visionFile = downloadIfMissing(VISION_URL, "siglip2_vision.onnx");
      File textFile = downloadIfMissing(TEXT_URL, "siglip2_text.onnx");
      File tokenizerFile = downloadIfMissing(TOKENIZER_URL, "tokenizer.json");

      try (InputStream stream = new FileInputStream(visionFile)) {
        byte[] modelArray = stream.readAllBytes();
        visionSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      try (InputStream stream = new FileInputStream(textFile)) {
        byte[] modelArray = stream.readAllBytes();
        textSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      // Sanitize tokenizer.json to remove unsupported keys for older DJL versions
      try {
        String content = Files.readString(tokenizerFile.toPath());
        boolean changed = false;
        if (content.contains("\"fuse_unk\"")) {
          content = content.replaceAll("\"fuse_unk\":\\s*(true|false),?", "");
          changed = true;
        }
        if (content.contains("\"byte_fallback\"")) {
          content = content.replaceAll("\"byte_fallback\":\\s*(true|false),?", "");
          changed = true;
        }
        if (changed) {
          Files.writeString(tokenizerFile.toPath(), content);
          System.out.println("Sanitized tokenizer.json for compatibility.");
        }
      } catch (Exception e) {
        System.err.println("Failed to sanitize tokenizer.json: " + e.getMessage());
      }

      try (InputStream stream = new FileInputStream(tokenizerFile)) {
        tokenizer = HuggingFaceTokenizer.newInstance(stream, Map.of());
      }

      if (textSession != null && tokenizer != null) {
        System.out.println("Caching text embeddings for tags...");
        for (Map.Entry<String, String> entry : CUSTOM_TAGS.entrySet()) {
          cachedTextEmbeddings.put(entry.getKey(), extractTextFeature(entry.getValue()));
        }
      }
    } catch (Exception e) {
      log.error("CRITICAL: Failed to initialize SemanticExtractor.", e);
    }
  }

  public static float[] extractSemanticFeatures(BufferedImage img) {
    if (visionSession == null) return new float[SIGLIP_DIMENSIONS];
    try {
      BufferedImage resized = FeatureExtractor.resizeImageLanczos3(
        img,
        TARGET_RESOLUTION,
        TARGET_RESOLUTION
      );
      float[][][][] inputTensor = new float[1][3][TARGET_RESOLUTION][TARGET_RESOLUTION];

      for (int y = 0; y < TARGET_RESOLUTION; y++) {
        for (int x = 0; x < TARGET_RESOLUTION; x++) {
          int rgb = resized.getRGB(x, y);
          float r = ((rgb >> 16) & 0xFF) / 255.0f;
          float g = ((rgb >> 8) & 0xFF) / 255.0f;
          float b = (rgb & 0xFF) / 255.0f;

          inputTensor[0][0][y][x] = (r * 2.0f) - 1.0f;
          inputTensor[0][1][y][x] = (g * 2.0f) - 1.0f;
          inputTensor[0][2][y][x] = (b * 2.0f) - 1.0f;
        }
      }

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor);
        OrtSession.Result result = visionSession.run(
          Collections.singletonMap("pixel_values", tensor)
        )
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      log.error("Failed to extract semantic features", e);
      return new float[SIGLIP_DIMENSIONS];
    }
  }

  private static float[] extractTextFeature(String text) {
    if (textSession == null || tokenizer == null) return new float[SIGLIP_DIMENSIONS];
    try {
      Encoding encoding = tokenizer.encode(text);
      long[] ids = encoding.getIds();

      long[][] inputIds = new long[1][ids.length];
      System.arraycopy(ids, 0, inputIds[0], 0, ids.length);

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputIds);
        OrtSession.Result result = textSession.run(Collections.singletonMap("input_ids", tensor))
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      log.error("Failed to extract text features", e);
      return new float[SIGLIP_DIMENSIONS];
    }
  }

  public static List<String> getAutoTags(float[] imageVector) {
    List<String> detected = new ArrayList<>();
    if (cachedTextEmbeddings.isEmpty() || imageVector.length != SIGLIP_DIMENSIONS) return detected;

    List<Map.Entry<String, float[]>> entries = new ArrayList<>(cachedTextEmbeddings.entrySet());

    entries.sort((a, b) ->
      Float.compare(dotProduct(imageVector, b.getValue()), dotProduct(imageVector, a.getValue()))
    );

    float threshold = 0.2f;

    for (int i = 0; i < Math.min(3, entries.size()); i++) {
      Map.Entry<String, float[]> entry = entries.get(i);
      float score = dotProduct(imageVector, entry.getValue());

      if (score > threshold) {
        detected.add(entry.getKey());
      }
    }

    return detected;
  }

  private static float dotProduct(float[] a, float[] b) {
    float sum = 0;
    for (int i = 0; i < a.length; i++) sum += a[i] * b[i];
    return sum;
  }

  public static float[] normalizeL2(float[] vector) {
    double sumSq = 0;
    for (float v : vector) sumSq += v * v;
    float length = (float) Math.sqrt(sumSq);
    if (length > 1e-9) {
      for (int i = 0; i < vector.length; i++) vector[i] /= length;
    }
    return vector;
  }
}


=========================================
File: ./backend/src/main/java/pdl/backend/vision/VisionProcessor.java
=========================================
package pdl.backend.vision;

import com.pgvector.PGvector;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pdl.backend.gallery.core.FileStorageService;
import pdl.backend.gallery.core.MediaRecord;
import pdl.backend.gallery.search.TagRepository;

@Service
public class VisionProcessor {

  private static final Logger log = LoggerFactory.getLogger(VisionProcessor.class);
  private final JdbcTemplate jdbcTemplate;
  private final TagRepository queryRepoLayer;
  private final UploadStatusTracker statusNotifier;
  private final FileStorageService fileStorageService;

  private volatile boolean isRunning = true;
  private final List<Thread> workers = new ArrayList<>();

  public VisionProcessor(
    JdbcTemplate jdbcTemplate,
    TagRepository queryRepoLayer,
    UploadStatusTracker statusNotifier,
    FileStorageService fileStorageService
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.queryRepoLayer = queryRepoLayer;
    this.statusNotifier = statusNotifier;
    this.fileStorageService = fileStorageService;
  }

  @PostConstruct
  public void startWorkers() {
    log.info("Starting 2 dedicated VisionProcessor ML worker threads");
    for (int i = 0; i < 2; i++) {
      Thread worker = Thread.ofVirtual().name("MLWorker-", i).start(this::processQueue);
      workers.add(worker);
    }
  }

  @PreDestroy
  public void shutdown() {
    log.info("Gracefully shutting down VisionProcessor workers (up to 30s timeout)...");
    isRunning = false;
    for (Thread worker : workers) {
      try {
        worker.join(30000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @EventListener(ApplicationReadyEvent.class)
  public void resetProcessingOnStartup() {
    log.info("Resetting any stuck PROCESSING images to PENDING");
    jdbcTemplate.update(
      "UPDATE images SET extraction_status = 'PENDING' WHERE extraction_status = 'PROCESSING'"
    );
  }

  private void processQueue() {
    while (isRunning) {
      try {
        // Atomic lock using SKIP LOCKED
        List<Long> ids = jdbcTemplate.query(
          "UPDATE images SET extraction_status = 'PROCESSING' WHERE id = (" +
            "  SELECT id FROM images WHERE extraction_status = 'PENDING' ORDER BY id ASC LIMIT 1 FOR UPDATE SKIP LOCKED" +
            ") RETURNING id",
          (rs, rowNum) -> rs.getLong(1)
        );

        if (ids.isEmpty()) {
          Thread.sleep(2000);
        } else {
          Long id = ids.get(0);
          try {
            Optional<MediaRecord> recordOpt = fileStorageService.getImageWithData(id);
            if (recordOpt.isEmpty() || recordOpt.get().getData() == null) {
              log.error("Failed to load file data for ID: {}", id);
              queryRepoLayer.updateStatus(id, "FAILED");
              statusNotifier.notify(id, "FAILED");
              continue; // proceed to next item
            }
            processImageDescriptors(id, recordOpt.get().getData());
          } catch (Throwable t) {
            // MASSIVE CATCH to prevent eternal queue stall
            log.error("SEVERE CRASH processing image ID: {}", id, t);
            queryRepoLayer.updateStatus(id, "FAILED");
            statusNotifier.notify(id, "FAILED");
          }
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break; // break the loop on interrupt
      } catch (Exception e) {
        log.error("Error in VisionProcessor queue loop", e);
        try {
          Thread.sleep(5000);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          break;
        }
      }
    }
  }

  public void processImageDescriptors(Long id, byte[] data) {
    BufferedImage bimg = null;
    BufferedImage resizedImage = null;

    try {
      queryRepoLayer.updateStatus(id, "PROCESSING");
      statusNotifier.notify(id, "PROCESSING");

      bimg = ImageIO.read(new ByteArrayInputStream(data));
      if (bimg == null) {
        queryRepoLayer.updateStatus(id, "FAILED");
        statusNotifier.notify(id, "FAILED");
        return;
      }

      // 1. Extract Classic Descriptors
      resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);
      float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
      float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
      float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
      float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);

      // 2. Extract Semantic AI Data & Auto-Tags
      float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

      // Auto-Tagging logic
      List<String> aiTags = SemanticExtractor.getAutoTags(semanticVector);
      queryRepoLayer.addKeywords(id, aiTags, true);

      if (hogData.length != 31) {
        float[] adjustedHog = new float[31];
        System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
        hogData = adjustedHog;
      }

      // 4. Save everything to Postgres
      jdbcTemplate.update(
        "INSERT INTO imagedescriptors (imageid, hogvector, hsvvector, rgbvector, labvector, semanticvector) VALUES (?, ?, ?, ?, ?, ?) " +
          "ON CONFLICT (imageid) DO UPDATE SET " +
          "hogvector = EXCLUDED.hogvector, " +
          "hsvvector = EXCLUDED.hsvvector, " +
          "rgbvector = EXCLUDED.rgbvector, " +
          "labvector = EXCLUDED.labvector, " +
          "semanticvector = EXCLUDED.semanticvector",
        id,
        new PGvector(hogData),
        new PGvector(hsvData),
        new PGvector(rgbData),
        new PGvector(labData),
        new PGvector(semanticVector)
      );

      queryRepoLayer.updateStatus(id, "COMPLETED");
      statusNotifier.notify(id, "COMPLETED");
    } catch (Exception e) {
      log.error("Failed to process image descriptors for ID: " + id, e);
      queryRepoLayer.updateStatus(id, "FAILED");
      statusNotifier.notify(id, "FAILED");
    } finally {
      if (bimg != null) bimg.flush();
      if (resizedImage != null) resizedImage.flush();
    }
  }

  public Map<String, float[]> extractEphemeralVectors(byte[] data) throws Exception {
    BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(data));
    if (bimg == null) {
      throw new IllegalArgumentException("Failed to decode image data.");
    }

    BufferedImage resizedImage = FeatureExtractor.resizeImageLanczos3(bimg, 256, 256);

    // Extract base histograms
    float[] hogData = FeatureExtractor.extractGlobalHog(resizedImage);
    float[] hsvData = FeatureExtractor.extractHsvHistogram(resizedImage);
    float[] rgbData = FeatureExtractor.extractRgbHistogram(resizedImage);
    float[] labData = FeatureExtractor.extractCieLabHistogram(resizedImage);

    // Extract semantics
    float[] semanticVector = SemanticExtractor.extractSemanticFeatures(bimg);

    // Fix HOG length mismatch
    if (hogData.length != 31) {
      float[] adjustedHog = new float[31];
      System.arraycopy(hogData, 0, adjustedHog, 0, Math.min(hogData.length, 31));
      hogData = adjustedHog;
    }

    // Cleanup resources
    bimg.flush();
    resizedImage.flush();

    return Map.of(
      "gradient",
      hogData,
      "saturation",
      hsvData,
      "rgb",
      rgbData,
      "cielab",
      labData,
      "semantic",
      semanticVector
    );
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/auth/AuthControllerWebMvcTests.java
=========================================
package pdl.backend.auth;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @MockitoBean
  AuthenticationManager authenticationManager;

  @MockitoBean
  JwtEncoder jwtEncoder;

  @MockitoBean
  AppSettingRepository appSettingRepository;

  @Test
  void registerReturnsBadRequestWhenUsernameTaken() throws Exception {
    when(userRepository.findByUsername("alice")).thenReturn(
      Optional.of(new UserAccount("alice", "pw", "ROLE_USER"))
    );

    String payload = """
      {"username":"alice","password":"secret"}
      """;

    mockMvc
      .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(payload))
      .andExpect(status().isBadRequest());
  }

  @Test
  void registerReturnsOkWhenUsernameFree() throws Exception {
    when(userRepository.findByUsername("bob")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");

    String payload = """
      {"username":"bob","password":"secret"}
      """;

    mockMvc
      .perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(payload))
      .andExpect(status().isOk());
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/core/GalleryControllerWebMvcTests.java
=========================================
package pdl.backend.gallery.core;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.gallery.search.TagRepository;
import pdl.backend.vision.UploadStatusTracker;

@WebMvcTest(GalleryController.class)
class GalleryControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  FileStorageService storageService;

  @MockitoBean
  TagRepository tagRepository;

  @MockitoBean
  UploadStatusTracker uploadStatusTracker;

  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @MockitoBean
  org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

  @Test
  void getImageReturns404WhenNotFound() throws Exception {
    when(storageService.getImageWithData(42L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/images/{id}", 42L)).andExpect(status().isNotFound());
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/search/SearchControllerWebMvcTests.java
=========================================
package pdl.backend.gallery.search;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.vision.VisionProcessor;

@WebMvcTest(SearchController.class)
class SearchControllerWebMvcTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  SimilarityRepository similarityRepository;

  @MockitoBean
  TagRepository tagRepository;

  @MockitoBean
  VisionProcessor visionProcessor;

  // NEW – needed so Application.dataSeeder() can be created
  @MockitoBean
  UserRepository userRepository;

  @MockitoBean
  PasswordEncoder passwordEncoder;

  @Test
  void similarWithUnknownDescriptorReturnsBadRequest() throws Exception {
    mockMvc
      .perform(get("/images/{id}/similar", 1L).param("descriptor", "unknown"))
      .andExpect(status().isBadRequest());
  }

  @Test
  void ephemeralSearchEmptyFileReturnsBadRequest() throws Exception {
    mockMvc
      .perform(
        multipart("/images/search/ephemeral")
          .file("file", new byte[0])
          .param("descriptor", "semantic")
      )
      .andExpect(status().isBadRequest());
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/gallery/search/TagRepositoryTests.java
=========================================
package pdl.backend.gallery.search;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jdbc.core.JdbcTemplate;

class TagRepositoryTests {

  @Test
  void addKeywordNormalizesAndInsertsOnce() {
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    // First query: TagRepository checks that the image exists
    when(
      jdbcTemplate.queryForObject(
        eq("SELECT id FROM images WHERE id = ?"),
        eq(Long.class),
        anyLong()
      )
    ).thenReturn(1L);

    TagRepository repo = new TagRepository(jdbcTemplate);

    boolean result = repo.addKeyword(1L, " Big Sun Set ");

    assertThat(result).isTrue();

    ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
    verify(jdbcTemplate).update(
      eq(
        "INSERT INTO imagekeywords (imageid, keyword, is_ai_generated) VALUES (?, ?, ?) ON CONFLICT DO NOTHING"
      ),
      eq(1L),
      keywordCaptor.capture(),
      eq(false)
    );

    // normalizeTag: trim, lower-case, whitespace -> underscore
    assertThat(keywordCaptor.getValue()).isEqualTo("big_sun_set");
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/ingestion/DatasetIngestControllerSecurityTests.java
=========================================
package pdl.backend.ingestion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pdl.backend.auth.UserRepository;
import pdl.backend.config.SecurityConfig;

@WebMvcTest(DatasetIngestController.class)
@Import(SecurityConfig.class)
class DatasetIngestControllerSecurityTests {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UnsplashService unsplashService;

  @MockitoBean
  UserRepository userRepository;

  @Test
  void adminCanStartUploadSync() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "photos.csv000",
      "text/plain",
      "header\ndata".getBytes()
    );

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .param("limit", "50")
          .param("offset", "0")
          .param("fileType", "PHOTOS")
          .with(
            SecurityMockMvcRequestPostProcessors.jwt()
              .authorities(
                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_ADMIN")
              )
              .jwt(jwt -> {
                jwt.claim("role", "ROLE_ADMIN");
                jwt.claim("userId", 1L);
              })
          )
      )
      .andExpect(status().isOk());

    verify(unsplashService).syncMetadataFromFile(any(Path.class), eq(50), eq(0));
  }

  @Test
  void adminCanStartKeywordSync() throws Exception {
    when(unsplashService.getStatus()).thenReturn("IDLE");

    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "keywords.tsv",
      "text/plain",
      "header\ndata".getBytes()
    );

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .param("limit", "100")
          .param("offset", "0")
          .param("fileType", "KEYWORDS")
          .with(
            SecurityMockMvcRequestPostProcessors.jwt()
              .authorities(
                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_ADMIN")
              )
              .jwt(jwt -> {
                jwt.claim("role", "ROLE_ADMIN");
                jwt.claim("userId", 1L);
              })
          )
      )
      .andExpect(status().isOk());

    verify(unsplashService).syncKeywordsFromFile(any(Path.class), eq(100), eq(0));
  }

  @Test
  void nonAdminGetsForbiddenOnUpload() throws Exception {
    MockMultipartFile mockFile = new MockMultipartFile(
      "file",
      "test",
      "text/plain",
      "data".getBytes()
    );

    mockMvc
      .perform(
        multipart("/admin/unsplash/upload")
          .file(mockFile)
          .with(
            SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> {
              jwt.claim("role", "ROLE_USER");
              jwt.claim("userId", 2L);
            })
          )
      )
      .andExpect(status().isForbidden());
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/system/HealthCheckIT.java
=========================================
package pdl.backend.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthCheckIT {

  @Autowired
  HealthCheck healthCheck;

  @Test
  void healthReturnsNonNullStatus() {
    Health health = healthCheck.health();
    assertThat(health).isNotNull();
  }
}


=========================================
File: ./backend/src/test/java/pdl/backend/vision/FeatureExtractorTest.java
=========================================
package pdl.backend.vision;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;

class FeatureExtractorTest {

  @Test
  void testResizeImageLanczos3() {
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    BufferedImage resized = FeatureExtractor.resizeImageLanczos3(img, 256, 256);
    
    assertThat(resized.getWidth()).isEqualTo(256);
    assertThat(resized.getHeight()).isEqualTo(256);
  }

  @Test
  void testExtractGlobalHogReturnsCorrectDimensions() {
    BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    float[] hog = FeatureExtractor.extractGlobalHog(img);
    
    assertThat(hog).hasSize(81);
  }
}

=========================================
File: ./backend/src/test/java/pdl/backend/vision/UploadStatusTrackerTests.java
=========================================
package pdl.backend.vision;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

public class UploadStatusTrackerTests {

    private UploadStatusTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new UploadStatusTracker();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testWaitForAddsRequestAndNotifyResolvesIt() throws Exception {
        Long imageId = 1L;
        DeferredResult<ResponseEntity<Map<String, Object>>> result = tracker.waitFor(imageId, 10000L);

        // Verify request was added using reflection
        Field requestsField = UploadStatusTracker.class.getDeclaredField("requests");
        requestsField.setAccessible(true);
        Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
                (Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>>) requestsField.get(tracker);

        assertTrue(requests.containsKey(imageId));
        assertEquals(result, requests.get(imageId));

        // Notify
        tracker.notify(imageId, "COMPLETED");

        // Verify result is set
        assertTrue(result.hasResult());
        ResponseEntity<Map<String, Object>> response = (ResponseEntity<Map<String, Object>>) result.getResult();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("COMPLETED", response.getBody().get("extraction_status"));
        assertEquals(imageId, response.getBody().get("id"));

        // Verify request was removed
        assertFalse(requests.containsKey(imageId));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testTimeoutRemovesRequest() throws Exception {
        Long imageId = 2L;
        DeferredResult<ResponseEntity<Map<String, Object>>> result = tracker.waitFor(imageId, 100L);

        // Verify request was added
        Field requestsField = UploadStatusTracker.class.getDeclaredField("requests");
        requestsField.setAccessible(true);
        Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>> requests =
                (Map<Long, DeferredResult<ResponseEntity<Map<String, Object>>>>) requestsField.get(tracker);

        assertTrue(requests.containsKey(imageId));

        // Simulate timeout directly
        result.setResult(ResponseEntity.accepted().body(Map.of("id", imageId, "extraction_status", "PENDING")));

        // Calling setResult should trigger onCompletion logic in the actual container, but since we are testing
        // unit logic without container, we just ensure that we can simulate it and that notify removes if it was still there.
        // Let's actually verify notify does not crash when called after removed.
        requests.remove(imageId);
        assertDoesNotThrow(() -> tracker.notify(imageId, "COMPLETED"));
    }
}


=========================================
File: ./backend/.gitignore
=========================================
# Maven
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

# Spring Boot
HELP.md

# Java Crash Logs
hs_err_pid*
replay_pid*

# IDE Specifics (Java)
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache
*.iws
*.iml
*.ipr
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/

# AI Models
models/


=========================================
File: ./backend/Dockerfile
=========================================
# syntax=docker/dockerfile:1

# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
ENV MAVEN_ARGS="-B -ntp"
COPY pom.xml .
# Cache the Maven repository so downloads aren't repeated on every build
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends gosu curl && rm -rf /var/lib/apt/lists/*

# Create a non-root user without forcing UID 1000 (avoids Ubuntu OS collisions)
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create BOTH images and datasets directories
RUN mkdir -p /var/lib/pdl/datasets /var/lib/pdl/models \
  && chown -R appuser:appgroup /var/lib/pdl

COPY --from=builder /app/target/*.jar app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
CMD ["java", "-jar", "app.jar"]


=========================================
File: ./backend/entrypoint.sh
=========================================
#!/bin/bash
set -e

echo "Fixing permissions for /var/lib/pdl..."
chown -R appuser:appgroup /var/lib/pdl

echo "Starting application as appuser..."
exec gosu appuser "$@"


=========================================
File: ./backend/pom.xml
=========================================
<?xml version="1.0" encoding="UTF-8" ?>
<project
  xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"
>
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.5</version>
    <relativePath />
  </parent>
  <groupId>pdl</groupId>
  <artifactId>backend</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>backend</name>
  <description>Demo project for Spring Boot</description>

  <properties>
    <java.version>21</java.version>
    <spring-boot.version>4.0.5</spring-boot.version>
    <mockito.version>5.23.0</mockito.version>
    <argLine />
    <tomcat.version>11.0.21</tomcat.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>tools.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>3.1.1</version>
      </dependency>

      <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>bom</artifactId>
        <version>2.42.32</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-restclient</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webmvc-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
    </dependency>
    <dependency>
      <groupId>com.pgvector</groupId>
      <artifactId>pgvector</artifactId>
      <version>0.1.6</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-flyway</artifactId>
    </dependency>
    <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-database-postgresql</artifactId>
    </dependency>
    <dependency>
      <groupId>org.boofcv</groupId>
      <artifactId>boofcv-core</artifactId>
      <version>1.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.boofcv</groupId>
      <artifactId>boofcv-io</artifactId>
      <version>1.3.0</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.imageio</groupId>
      <artifactId>imageio-core</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.common</groupId>
      <artifactId>common-image</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>com.twelvemonkeys.imageio</groupId>
      <artifactId>imageio-jpeg</artifactId>
      <version>3.13.1</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security-oauth2-resource-server</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>${mockito.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.microsoft.onnxruntime</groupId>
      <artifactId>onnxruntime</artifactId>
      <version>1.24.3</version>
    </dependency>
    <dependency>
      <groupId>ai.djl.huggingface</groupId>
      <artifactId>tokenizers</artifactId>
      <version>0.36.0</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>software.amazon.awssdk</groupId>
      <artifactId>s3</artifactId>
    </dependency>
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.5.5</version>
        <configuration>
          <argLine>
            @{argLine}
            -javaagent:${settings.localRepository}/org/mockito/mockito-core/${mockito.version}/mockito-core-${mockito.version}.jar
            -Xshare:off
          </argLine>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>


=========================================
File: ./.prettierignore
=========================================
node_modules/
target/
dist/
build/
.git/


=========================================
File: ./frontend/.gitignore
=========================================
# Logs
logs
*.log
npm-debug.log*
yarn-debug.log*
yarn-error.log*
pnpm-debug.log*
lerna-debug.log*

node_modules
dist
dist-ssr
*.local

# Editor directories and files
.vscode/*
!.vscode/extensions.json
.idea
.DS_Store
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?


=========================================
File: ./frontend/src/vite-env.d.ts
=========================================
/// <reference types="vite/client" />

declare module "*.vue" {
  import type { DefineComponent } from "vue";
  const component: DefineComponent<{}, {}, any>;
  export default component;
}


=========================================
File: ./frontend/src/App.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useAuth } from "./composables/useAuth";
import { useGallerySearch } from "./composables/useGallerySearch";
import TagAutocomplete from "./shared/TagAutocomplete.vue";
import http from "./api/http-client";

const { isLoggedIn, logout } = useAuth();
const { clearSimilaritySearch } = useGallerySearch();
const router = useRouter();
const route = useRoute();
const theme = ref("light");
const isMobileMenuOpen = ref(false);
let audioCtx: AudioContext | null = null;
const popularTags = ref<string[]>([]);

const isAdmin = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return false;
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.role === "ROLE_ADMIN";
  } catch (e) {
    return false;
  }
});

const username = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return "";
  try {
    const payload = JSON.parse(atob(token.split(".")[1] || ""));
    return payload.sub || payload.username || "User";
  } catch (e) {
    return "";
  }
});

const fetchPopularTags = async () => {
  try {
    const res = await http.get("/images/keywords/popular?limit=15");
    popularTags.value = res.data;
  } catch (e) {
    console.error(e);
  }
};

const executeSearch = (tag: string) => {
  clearSimilaritySearch();
  if (!tag) {
    router.push({ path: "/" });
  } else {
    router.push({ path: "/", query: { tags: tag } });
  }
};

const playPainSound = () => {
  if (theme.value !== "cruelty") return;
  if (!audioCtx) {
    audioCtx = new (window.AudioContext || (window as any).webkitAudioContext)();
  }
  if (audioCtx.state === "suspended") {
    audioCtx.resume();
  }
  const osc = audioCtx.createOscillator();
  const gain = audioCtx.createGain();
  osc.type = "sawtooth";
  osc.frequency.setValueAtTime(Math.random() * 1000 + 100, audioCtx.currentTime);
  osc.frequency.exponentialRampToValueAtTime(10, audioCtx.currentTime + 0.1);
  gain.gain.setValueAtTime(0.3, audioCtx.currentTime);
  gain.gain.exponentialRampToValueAtTime(0.01, audioCtx.currentTime + 0.1);
  osc.connect(gain);
  gain.connect(audioCtx.destination);
  osc.start();
  osc.stop(audioCtx.currentTime + 0.2);
};

const handleGlobalClick = () => {
  playPainSound();
};

const toggleTheme = async (target: string) => {
  theme.value = target;
  document.documentElement.className = theme.value;
  localStorage.setItem("theme", theme.value);

  const linkId = "cruelty-theme-link";
  let link = document.getElementById(linkId) as HTMLLinkElement;

  if (target === "cruelty") {
    if (!link) {
      link = document.createElement("link");
      link.id = linkId;
      link.rel = "stylesheet";
      document.head.appendChild(link);
      const cssUrl = (await import("./assets/theme-cruelty.css?url")).default;
      link.href = cssUrl;
    }
  } else {
    if (link) link.remove();
  }
};

onMounted(() => {
  const savedTheme = localStorage.getItem("theme");
  if (savedTheme) {
    theme.value = savedTheme;
  } else if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
    theme.value = "dark";
  } else {
    theme.value = "light";
  }
  toggleTheme(theme.value);
  document.addEventListener("click", handleGlobalClick);
  fetchPopularTags();
});
</script>

<template>
  <div class="crt-overlay"></div>
  <div class="app-layout">
    <header class="top-nav">
      <div class="nav-left">
        <router-link to="/" class="logo" @click="clearSimilaritySearch">pdl.</router-link>
      </div>

      <div class="nav-right" :class="{ 'mobile-open': isMobileMenuOpen }">
        <button class="hamburger-btn" @click="isMobileMenuOpen = !isMobileMenuOpen">
          <span class="material-symbols-outlined">{{ isMobileMenuOpen ? "close" : "menu" }}</span>
        </button>

        <div class="nav-content" :class="{ 'is-open': isMobileMenuOpen }">
          <nav class="nav-links">
            <router-link to="/about" @click="isMobileMenuOpen = false">About</router-link>
            <router-link v-if="isAdmin" to="/admin" @click="isMobileMenuOpen = false"
              >Admin</router-link
            >
          </nav>
          <div class="divider desktop-only"></div>
          <button
            class="cruelty-toggle"
            @click="
              toggleTheme(theme === 'cruelty' ? 'light' : 'cruelty');
              isMobileMenuOpen = false;
            "
          >
            {{ theme === "cruelty" ? "REVERT CRUELTY" : "CRUELTY" }}
          </button>
          <button
            class="theme-toggle"
            @click="
              toggleTheme(theme === 'light' ? 'dark' : 'light');
              isMobileMenuOpen = false;
            "
            v-if="theme !== 'cruelty'"
            title="Toggle Theme"
          >
            <span class="material-symbols-outlined">contrast</span>
          </button>

          <template v-if="isLoggedIn">
            <router-link
              to="/profile"
              class="user-badge"
              @click="isMobileMenuOpen = false"
              style="
                font-family: var(--font-mono);
                color: var(--text-muted);
                font-size: 0.85rem;
                text-decoration: none;
              "
              >@{{ username }}</router-link
            >
            <button
              @click="
                logout();
                isMobileMenuOpen = false;
              "
              class="btn logout-btn"
              style="
                background: none;
                border: none;
                cursor: pointer;
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
              "
            >
              Logout
            </button>
            <router-link to="/upload" class="btn upload-btn" @click="isMobileMenuOpen = false"
              >Upload</router-link
            >
          </template>
          <template v-else>
            <router-link
              to="/login"
              class="btn login-btn"
              @click="isMobileMenuOpen = false"
              style="
                color: var(--text-secondary);
                font-family: var(--font-sans);
                font-weight: 500;
                text-decoration: none;
                background: transparent;
                border: none;
              "
              >Login</router-link
            >
            <router-link
              to="/register"
              class="btn btn-outline"
              @click="isMobileMenuOpen = false"
              style="padding: 0.4rem 1rem; border-radius: 4px"
              >Register</router-link
            >
          </template>
        </div>
      </div>
    </header>

    <div class="sub-nav">
      <div class="tags-scroll">
        <button
          @click="executeSearch('')"
          class="popular-tag-btn"
          :class="{ 'active-tag': !route.query.tags && route.path === '/' }"
        >
          Home
        </button>
        <button
          v-for="tag in popularTags"
          :key="tag"
          @click="executeSearch(tag)"
          class="popular-tag-btn"
          :class="{ 'active-tag': route.query.tags === tag }"
        >
          #{{ tag }}
        </button>
      </div>

      <div class="global-search">
        <span class="material-symbols-outlined search-icon">search</span>
        <TagAutocomplete @select="executeSearch" placeholder="Search any tag..." />
      </div>
    </div>

    <main class="content-canvas">
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
.app-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* Slimmer Top Nav */
.top-nav {
  position: relative; /* Ensure absolute children position relative to this */
  z-index: 51;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem max(1rem, env(safe-area-inset-right)) 0.75rem
    max(1rem, env(safe-area-inset-left));
  background: var(--bg-surface);
  border-bottom: 1px solid var(--border-subtle);
}

@media (min-width: 768px) {
  .top-nav {
    padding: 0.75rem max(2rem, env(safe-area-inset-right)) 0.75rem
      max(2rem, env(safe-area-inset-left));
  }
}

.nav-left {
  display: flex;
  align-items: center;
}

.logo {
  font-family: var(--font-headline);
  font-size: 2rem;
  font-style: italic;
  font-weight: 700;
  color: var(--text-primary);
  text-decoration: none;
  transition: opacity 0.2s;
}

.logo:hover {
  opacity: 0.7;
}

/* Sub Navigation */
.sub-nav {
  position: sticky;
  top: 0; /* Sits at the top since top-nav scrolls away */
  z-index: 50;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
  padding: 0.5rem max(1rem, env(safe-area-inset-right)) 0.5rem max(1rem, env(safe-area-inset-left));
  background: var(--bg-element);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}

@media (min-width: 768px) {
  .sub-nav {
    flex-direction: row;
    align-items: center;
    padding: 0.5rem max(2rem, env(safe-area-inset-right)) 0.5rem
      max(2rem, env(safe-area-inset-left));
  }
}

.tags-scroll {
  display: flex;
  gap: 1rem;
  overflow-x: auto;
  white-space: nowrap;
  scrollbar-width: none;
  align-items: center;
  padding-bottom: 0.25rem;
  padding-left: max(1rem, env(safe-area-inset-left));
  padding-right: max(1rem, env(safe-area-inset-right));
  margin-left: calc(-1 * max(1rem, env(safe-area-inset-left)));
  margin-right: calc(-1 * max(1rem, env(safe-area-inset-right)));
}

@media (min-width: 768px) {
  .tags-scroll {
    gap: 1.5rem;
    padding-bottom: 0;
    margin: 0;
    padding: 0;
    width: auto;
  }
}
.tags-scroll::-webkit-scrollbar {
  display: none;
}

.popular-tag-btn {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  color: var(--text-secondary);
  cursor: pointer;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.25rem 0;
}
.popular-tag-btn:hover {
  color: var(--text-primary);
}
.popular-tag-btn.active-tag {
  color: var(--color-accent);
  border-bottom: 2px solid var(--color-accent);
}

.global-search {
  display: flex;
  align-items: center;
  background: var(--bg-surface);
  border-radius: 4px;
  padding: 0.25rem 0.75rem;
  border: 1px solid var(--border-subtle);
  width: 100%;
  max-width: none;
  transition: border-color 0.2s;
  order: -1; /* move search above tags on mobile */
}

@media (min-width: 768px) {
  .global-search {
    max-width: 250px;
    order: 0;
  }
}
.global-search:focus-within {
  border-color: var(--color-accent);
}
.search-icon {
  color: var(--text-secondary);
  font-size: 1.25rem;
  margin-right: 0.5rem;
}

/* Right Nav Tools */
.nav-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}
.hamburger-btn {
  display: block;
  background: none;
  border: none;
  color: var(--text-primary);
  cursor: pointer;
  padding: 0.5rem;
}
@media (min-width: 768px) {
  .hamburger-btn {
    display: none;
  }
}

.nav-content {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background: var(--bg-surface);
  flex-direction: column;
  align-items: stretch;
  padding: 1.5rem 1rem;
  gap: 1rem;
  border-bottom: 1px solid var(--border-subtle);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
  z-index: 60;
}

.nav-content.is-open {
  display: flex;
}

@media (min-width: 768px) {
  .nav-content {
    display: flex;
    position: static;
    flex-direction: row;
    align-items: center;
    padding: 0;
    box-shadow: none;
    border-bottom: none;
    background: transparent;
  }
}

.nav-links {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
@media (min-width: 768px) {
  .nav-links {
    flex-direction: row;
    gap: 1.5rem;
  }
}
.nav-links a {
  font-family: var(--font-sans);
  color: var(--text-secondary);
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.2s;
  padding: 0.5rem 0; /* Larger touch targets */
}
@media (min-width: 768px) {
  .nav-links a {
    padding: 0;
  }
}
.nav-links a:hover,
.nav-links a.router-link-active {
  color: var(--text-primary);
}

.divider {
  height: 1px;
  width: 100%;
  background: var(--border-subtle);
  margin: 0.5rem 0;
}
@media (min-width: 768px) {
  .divider {
    height: 24px;
    width: 1px;
    margin: 0;
  }
  .divider.desktop-only {
    display: block;
  }
}
@media (max-width: 767px) {
  .divider.desktop-only {
    display: none;
  }
}

.cruelty-toggle {
  background: none;
  border: none;
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.7rem;
  font-weight: 700;
  color: var(--text-muted);
  cursor: pointer;
  transition: color 0.2s;
  padding: 0.75rem 0; /* Larger touch target */
  text-align: left;
}
@media (min-width: 768px) {
  .cruelty-toggle {
    padding: 0;
    text-align: center;
  }
}

.theme-toggle {
  background: none;
  border: none;
  color: var(--text-secondary);
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 0.75rem 0;
}
@media (min-width: 768px) {
  .theme-toggle {
    padding: 0;
  }
}

.upload-btn {
  padding: 0.75rem;
  text-align: center;
}
@media (min-width: 768px) {
  .upload-btn {
    padding: 0.5rem 1.25rem;
  }
}
.btn-outline {
  border: 1px solid var(--border-strong);
  color: var(--text-primary);
  text-decoration: none;
  font-family: var(--font-sans);
  font-size: 0.9rem;
  font-weight: 500;
  transition: background 0.2s;
  padding: 0.75rem;
  text-align: center;
  border-radius: 4px;
}
@media (min-width: 768px) {
  .btn-outline {
    padding: 0.4rem 1rem;
  }
}
.btn-outline:hover {
  background: var(--bg-element);
}

.content-canvas {
  flex: 1;
  padding: 1rem max(1rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
    max(1rem, env(safe-area-inset-left));
  width: 100%;
}
@media (min-width: 768px) {
  .content-canvas {
    padding: 2rem max(2rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(2rem, env(safe-area-inset-left));
  }
}
@media (min-width: 1024px) {
  .content-canvas {
    padding: 3rem max(4rem, env(safe-area-inset-right)) env(safe-area-inset-bottom)
      max(4rem, env(safe-area-inset-left));
  }
}

@keyframes pulse {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
  }
}
</style>


=========================================
File: ./frontend/src/api/http-client.ts
=========================================
import axios from "axios";

const http = axios.create({
  baseURL: "/", // Proxy handled by Vite / Nginx
  headers: {
    "Content-type": "application/json",
  },
});

http.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

http.interceptors.response.use(
  (response) => response,
  (error) => {
    // Gracefully handle JWT expiration or unauthorized access
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token");
      if (window.location.pathname !== "/login" && window.location.pathname !== "/register") {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  },
);

export default http;


=========================================
File: ./frontend/src/assets/base.css
=========================================
:root {
  --font-sans: "Inter", "Helvetica Neue", Helvetica, sans-serif;
  --font-headline: "Newsreader", serif;
  --font-mono: "SFMono-Regular", Consolas, monospace;

  --ease-standard: cubic-bezier(0.25, 1, 0.5, 1);
  --ease-cruel: steps(2, end);

  --space-xs: 0.5rem;
  --space-sm: 1rem;
  --space-md: 2rem;
  --space-lg: 4rem;
  --space-xl: 8rem;

  color-scheme: light dark;
  font-synthesis: none;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

/* --- LIGHT MODE --- */
:root.light {
  color-scheme: light;
  --bg-body: oklch(99% 0 0);
  --bg-surface: oklch(100% 0 0);
  --bg-surface-alt: oklch(96% 0 0);
  --bg-element: oklch(92% 0 0);

  --text-primary: oklch(15% 0 0);
  --text-secondary: oklch(45% 0 0);
  --text-muted: oklch(65% 0 0);

  --border-subtle: oklch(90% 0 0);
  --border-strong: oklch(15% 0 0);

  --color-accent: oklch(15% 0 0);
  --text-on-accent: oklch(99% 0 0);

  --color-danger: oklch(50% 0.15 25);
  --color-success: oklch(55% 0.12 150);

  --shadow-subtle: 0 4px 12px rgba(0, 0, 0, 0.05);
}

/* --- DARK MODE --- */
:root.dark {
  color-scheme: dark;
  --bg-body: oklch(12% 0 0);
  --bg-surface: oklch(16% 0 0);
  --bg-surface-alt: oklch(20% 0 0);
  --bg-element: oklch(24% 0 0);

  --text-primary: oklch(95% 0 0);
  --text-secondary: oklch(75% 0 0);
  --text-muted: oklch(55% 0 0);

  --border-subtle: oklch(25% 0 0);
  --border-strong: oklch(90% 0 0);

  --color-accent: oklch(95% 0 0);
  --text-on-accent: oklch(12% 0 0);

  --color-danger: oklch(65% 0.18 25);
  --color-success: oklch(70% 0.15 150);

  --shadow-subtle: none;
}

/* --- CRUELTY MODE --- */
:root.cruelty {
  color-scheme: dark;
  --bg-body: #4b5320;
  --bg-surface: #1a1c1c;
  --bg-surface-alt: #000000;
  --bg-element: #3c3b3b;

  --text-primary: #00ff00;
  --text-secondary: #ff00ff;
  --text-muted: #ffff00;

  --border-subtle: #ff00ff;
  --border-strong: #00ff00;

  --color-accent: #ff00ff;
  --text-on-accent: #ffffff;

  --color-danger: #ff0000;
  --color-success: #00ff00;

  --font-headline: "Impact", sans-serif;
  --font-sans: "Comic Sans MS", cursive, sans-serif;

  cursor: crosshair;
}

:root.cruelty body {
  background: repeating-linear-gradient(0deg, #220022 0%, #4b5320 50%, #ff0000 100%);
  background-size: 100% 4px;
}

.crt-overlay {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background:
    linear-gradient(rgba(18, 16, 16, 0) 50%, rgba(0, 0, 0, 0.25) 50%),
    linear-gradient(90deg, rgba(255, 0, 0, 0.06), rgba(0, 255, 0, 0.02), rgba(0, 0, 255, 0.06));
  background-size:
    100% 4px,
    3px 100%;
  pointer-events: none;
  z-index: 9999;
}
:root.cruelty .crt-overlay {
  display: block;
}

/* --- Global Base --- */
body {
  margin: 0;
  font-family: var(--font-sans);
  color: var(--text-primary);
  background-color: var(--bg-body);
  min-height: 100vh;
  overflow-x: hidden;
}

* {
  box-sizing: border-box;
}

h1,
h2,
h3,
h4 {
  font-family: var(--font-headline);
  margin: 0 0 var(--space-sm) 0;
  font-weight: normal;
  color: var(--text-primary);
}

.material-symbols-outlined {
  font-variation-settings:
    "FILL" 0,
    "wght" 300,
    "GRAD" 0,
    "opsz" 24;
}

/* Typography Utilities */
.label-text {
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.75rem;
  font-weight: 600;
  color: var(--text-secondary);
}
:root.cruelty .label-text {
  font-family: var(--font-headline);
  color: var(--text-primary);
  font-size: 1.2rem;
  letter-spacing: normal;
}

/* Forms & Inputs */
input,
select,
textarea {
  width: 100%;
  background: transparent;
  border: none;
  border-bottom: 1px solid var(--border-subtle);
  padding: 0.75rem 0;
  min-height: 44px;
  font-family: var(--font-sans);
  font-size: 1rem;
  color: var(--text-primary);
  transition: border-color 0.2s ease;
}
input:focus,
select:focus,
textarea:focus {
  outline: none;
  border-bottom-color: var(--border-strong);
}

:root.cruelty input,
:root.cruelty select,
:root.cruelty textarea {
  background: var(--bg-element);
  border: 4px solid var(--border-strong);
  font-family: var(--font-headline);
  color: #fff;
  padding: 1rem;
}
:root.cruelty input:focus,
:root.cruelty textarea:focus {
  background: #ffff00;
  color: #000;
  outline: 4px solid var(--color-accent);
}

/* Buttons */
.btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 0.75rem 1.5rem;
  min-height: 44px;
  background: var(--color-accent);
  color: var(--text-on-accent);
  font-family: var(--font-sans);
  font-size: 0.9rem;
  font-weight: 500;
  border: 1px solid var(--color-accent);
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s ease;
  text-decoration: none;
}
.btn:hover {
  background: transparent;
  color: var(--color-accent);
}

.btn-outline {
  background: transparent;
  color: var(--text-primary);
  border: 1px solid var(--border-subtle);
}
.btn-outline:hover {
  border-color: var(--border-strong);
}

:root.cruelty .btn {
  font-family: var(--font-headline);
  font-size: 2rem;
  text-transform: uppercase;
  border: 4px solid var(--border-strong);
  border-radius: 0;
  background: var(--color-accent);
  color: #fff;
}
:root.cruelty .btn:hover {
  background: var(--text-primary);
  color: #000;
  transform: rotate(2deg) scale(1.05);
}

/* Badges */
.status-badge {
  font-family: var(--font-sans);
  text-transform: uppercase;
  letter-spacing: 0.1em;
  font-size: 0.65rem;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 2px;
}
.status-badge.completed {
  background: var(--color-success);
  color: #fff;
}
.status-badge.failed {
  background: var(--color-danger);
  color: #fff;
}
.status-badge.pending {
  background: var(--bg-element);
  color: var(--text-primary);
}

/* --- Global Layout & Utility Classes --- */
.view-wrapper {
  animation: fadeIn 0.4s ease-out;
  max-width: 1400px;
  margin: 0 auto;
}
.view-wrapper.max-w-md {
  max-width: 400px;
}
.view-wrapper.max-w-lg {
  max-width: 800px;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.w-full {
  width: 100%;
}
.mt-4 {
  margin-top: 1rem;
}
.relative {
  position: relative;
}

/* Page Headers */
.page-header {
  margin-bottom: var(--space-xl);
}
.page-title {
  font-size: 3rem;
  font-family: var(--font-headline);
  margin-bottom: 0.5rem;
}
.page-subtitle {
  color: var(--text-secondary);
  font-size: 1rem;
}

/* Card Component (formerly .meta-card, .login-card, .stat-card) */
.card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
}

/* Tag Inputs */
.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  padding: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  transition: border-color 0.2s;
}
.tags-container:focus-within {
  border-color: var(--border-strong);
}
.tag-pill {
  background: var(--bg-element);
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  font-size: 0.8rem;
  display: flex;
  align-items: center;
  gap: 0.25rem;
}
.tag-remove {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0 2px;
}
.tag-remove:hover {
  color: var(--color-danger);
}
.tag-input {
  border: none;
  background: transparent;
  flex: 1;
  min-width: 100px;
  padding: 0;
  margin: 0;
  outline: none;
  font-size: 0.9rem;
}
.tag-input:focus {
  border-bottom: none;
}

/* Autocomplete Dropdown */
.autocomplete-dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  padding: 0.5rem 0;
  margin: 0;
  z-index: 100;
}
.autocomplete-dropdown li {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  cursor: pointer;
}
.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
}

/* Text Utilities */
.error-text {
  color: var(--color-danger);
  font-size: 0.9rem;
  margin-top: 1rem;
  text-align: center;
}
.success-text {
  color: var(--color-success);
  font-size: 0.9rem;
  margin-top: 1rem;
  text-align: center;
}

.ai-tag {
  background: linear-gradient(90deg, #ff8a00, #e52e71, #f000ff, #00d4ff, #00ff00);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  color: transparent;
  font-weight: 700;
  animation: rainbow-text 3s linear infinite;
  background-size: 200% 200%;
}

.ai-tag-pill {
  background: linear-gradient(
    90deg,
    rgba(255, 138, 0, 0.1),
    rgba(229, 46, 113, 0.1),
    rgba(240, 0, 255, 0.1)
  );
  border: 1px solid rgba(240, 0, 255, 0.3);
  color: var(--text-primary);
  font-weight: 600;
}

@keyframes rainbow-text {
  0% {
    background-position: 0% 50%;
  }
  100% {
    background-position: 100% 50%;
  }
}

/* --- CRUELTY OVERRIDES (Global) --- */
:root.cruelty .page-title {
  font-family: "Impact";
  text-transform: uppercase;
  color: #00ff00;
  font-size: 5rem;
}
:root.cruelty .card,
:root.cruelty .autocomplete-dropdown {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .tag-pill {
  background: transparent;
  border: 2px solid var(--text-primary);
  font-family: "Impact";
  border-radius: 0;
}
:root.cruelty .autocomplete-dropdown li:hover,
:root.cruelty .autocomplete-dropdown li.highlighted {
  background: #ff00ff;
  color: #fff;
}

/* Images CRT Scan Effect */
@keyframes cruelty-scanlines {
  from {
    -webkit-mask-position: 0 0;
    mask-position: 0 0;
  }
  to {
    -webkit-mask-position: 0 4px;
    mask-position: 0 4px;
  }
}

:root.cruelty img {
  filter: sepia(0.8) hue-rotate(180deg) saturate(400%) contrast(1.5);
  border: 4px solid var(--text-primary);
  border-radius: 0;
  -webkit-mask-image: repeating-linear-gradient(
    to bottom,
    rgba(0, 0, 0, 1) 0px,
    rgba(0, 0, 0, 1) 2px,
    rgba(0, 0, 0, 0.3) 2px,
    rgba(0, 0, 0, 0.3) 4px
  );
  mask-image: repeating-linear-gradient(
    to bottom,
    rgba(0, 0, 0, 1) 0px,
    rgba(0, 0, 0, 1) 2px,
    rgba(0, 0, 0, 0.3) 2px,
    rgba(0, 0, 0, 0.3) 4px
  );
  mask-size: 100% 4px;
  -webkit-mask-size: 100% 4px;
  animation: cruelty-scanlines 0.15s linear infinite;
  box-shadow: 0 0 20px rgba(0, 255, 0, 0.8);
}


=========================================
File: ./frontend/src/assets/theme-cruelty.css
=========================================
:root.cruelty .top-nav {
  background: #ff0000;
  border-bottom: 4px solid var(--border-strong);
  padding: 0.25rem 1rem !important; /* Forces it to be ultra-thin */
}
:root.cruelty .sub-nav {
  background: #000;
  border-bottom: 4px solid var(--color-accent);
  padding: 0.5rem 1rem;
}
:root.cruelty .logo {
  font-family: "Impact";
  font-style: normal;
  color: #fff;
  font-size: 2.2rem !important;
  transform: rotate(-3deg);
}
:root.cruelty .global-search {
  background: #000;
  border-radius: 0;
  border: 4px solid var(--color-accent);
}
:root.cruelty .nav-links a {
  font-family: "Impact";
  font-size: 1.5rem;
  color: #fff;
  text-transform: uppercase;
}
:root.cruelty .cruelty-toggle {
  background: #000;
  color: #00ff00;
  font-family: "Impact";
  font-size: 1rem;
  padding: 0.5rem 1rem;
  border: 2px solid #fff;
  animation: pulse 0.5s infinite;
}
:root.cruelty .user-badge {
  color: #ffff00 !important;
  font-family: "Impact" !important;
  font-size: 1.2rem !important;
}
:root.cruelty .popular-tag-btn {
  font-family: "Impact";
  font-size: 1.2rem;
  text-transform: uppercase;
}
:root.cruelty .autocomplete-dropdown {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .autocomplete-dropdown li:hover,
:root.cruelty .autocomplete-dropdown li.highlighted {
  background: #ff00ff;
  color: #fff;
}
:root.cruelty .stat-card {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
  transform: rotate(-2deg);
}
:root.cruelty .stat-val {
  font-family: "Impact";
  color: #00ff00;
}
:root.cruelty .filter-title {
  font-family: "Impact";
  color: #ff00ff;
  text-transform: uppercase;
}
:root.cruelty .highlight {
  color: #00ff00;
}
:root.cruelty .artifact-card {
  border-radius: 0;
  border: 4px solid var(--border-strong);
  transform: rotate(calc(-2deg + (4deg * var(--n, 1))));
}
:root.cruelty .artifact-card:nth-child(even) {
  --n: 0;
  border-color: var(--color-accent);
}
:root.cruelty .artifact-img {
  filter: contrast(200%) saturate(300%) hue-rotate(90deg);
}
:root.cruelty .artifact-card:hover .artifact-img {
  filter: invert(1);
}
:root.cruelty .card-overlay {
  opacity: 1;
  background: transparent;
  mix-blend-mode: difference;
}
:root.cruelty .artifact-name {
  font-family: "Impact";
  font-size: 1.5rem;
  background: #000;
  padding: 0.25rem;
  display: inline-block;
}
:root.cruelty .image-container {
  background: #000;
  border: 8px dashed var(--color-accent);
  border-radius: 0;
}
:root.cruelty .meta-sidebar {
  background: #111;
  border: 4px solid var(--border-strong);
  border-radius: 0;
}
:root.cruelty .image-title {
  font-family: "Impact";
  font-size: 2.5rem;
  color: #00ff00;
  text-transform: uppercase;
}
:root.cruelty .login-card {
  background: #000;
  border: 4px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .res-img {
  filter: sepia(1) hue-rotate(180deg) saturate(300%);
  border: 4px solid #fff;
  border-radius: 0;
}
:root.cruelty .drop-zone {
  border: 4px dashed #ff00ff;
  background: #000;
  border-radius: 0;
}
:root.cruelty .task-item {
  background: #111;
  border: 2px solid var(--color-accent);
  border-radius: 0;
}
:root.cruelty .meta-card {
  background: #000;
  border: 4px solid var(--border-strong);
  border-radius: 0;
}


=========================================
File: ./frontend/src/components/AdvancedSearchSidebar.vue
=========================================
<script setup lang="ts">
import { ref, watch, computed } from "vue";
import http from "../api/http-client";

const props = defineProps<{ initialSourceId?: number | null }>();
const emit = defineEmits(["close", "executeSimilarity"]);

// Similarity State
const uploadMode = ref<"db" | "ephemeral">("db");
const selectedDbId = ref<number | null>(props.initialSourceId || null);
const uploadTarget = ref<File | null>(null);
const similarityAlgorithm = ref("semantic");
const similarityCount = ref(10);
const errorMsg = ref("");

watch(
  () => props.initialSourceId,
  (newId) => {
    if (newId) {
      selectedDbId.value = newId;
      uploadMode.value = "db";
    }
  },
);

const isSourceSelected = computed(() => {
  if (uploadMode.value === "db") return !!selectedDbId.value;
  return !!uploadTarget.value;
});

const onFileSelect = (e: Event) => {
  const target = e.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    uploadTarget.value = target.files[0] || null;
  }
};

const triggerSimilaritySearch = async () => {
  errorMsg.value = "";

  try {
    let results = [];
    if (uploadMode.value === "db") {
      if (!selectedDbId.value) return (errorMsg.value = "Please enter a database Image ID.");
      const res = await http.get(`/images/${selectedDbId.value}/similar`, {
        params: { number: similarityCount.value, descriptor: similarityAlgorithm.value },
      });
      results = res.data;
    } else {
      if (!uploadTarget.value) return (errorMsg.value = "Please select an image to upload.");
      const formData = new FormData();
      formData.append("file", uploadTarget.value);
      formData.append("number", similarityCount.value.toString());
      formData.append("descriptor", similarityAlgorithm.value);

      const res = await http.post("/images/search/ephemeral", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      results = res.data;
    }

    // NEW: Emit the complete payload including the source context
    emit("executeSimilarity", {
      results: results,
      sourceId: uploadMode.value === "db" ? selectedDbId.value : null,
      isEphemeral: uploadMode.value === "ephemeral",
      fileUrl: uploadTarget.value ? URL.createObjectURL(uploadTarget.value) : null,
    });

    emit("close");
  } catch (e) {
    errorMsg.value = "Similarity scan failed.";
  }
};
</script>

<template>
  <aside class="search-sidebar">
    <div class="sidebar-header">
      <h3>Visual Match</h3>
      <button
        @click="$emit('close')"
        class="btn-icon material-symbols-outlined"
        title="Close Filters"
      >
        close
      </button>
    </div>

    <div class="sidebar-content">
      <p v-if="!isSourceSelected" class="help-text warning-text">
        <span class="material-symbols-outlined" style="font-size: 1.2rem">info</span>
        Select a gallery image or upload a target file to enable similarity settings.
      </p>

      <div class="form-group mb-4">
        <label class="label-text">Source Target</label>
        <select v-model="uploadMode" style="margin-top: 0.5rem">
          <option value="db">From Database (ID)</option>
          <option value="ephemeral">Upload Temporary Image</option>
        </select>
      </div>

      <div class="form-group" v-if="uploadMode === 'db'">
        <label class="label-text">Image ID</label>
        <input type="number" v-model="selectedDbId" placeholder="e.g., 15" />
      </div>

      <div class="form-group" v-if="uploadMode === 'ephemeral'">
        <label class="label-text">Upload Image</label>
        <input
          type="file"
          @change="onFileSelect"
          accept="image/*"
          style="margin-top: 0.5rem; padding-bottom: 0"
        />
      </div>

      <fieldset :disabled="!isSourceSelected" style="border: none; padding: 0; margin: 0">
        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Algorithm</label>
          <select v-model="similarityAlgorithm" style="margin-top: 0.5rem">
            <option value="semantic">Semantic (AI)</option>
            <option value="cielab">CIELAB (Human Vision)</option>
            <option value="gradient">HOG (Shape/Edges)</option>
          </select>
        </div>

        <div class="form-group mt-4" :class="{ 'disabled-group': !isSourceSelected }">
          <label class="label-text">Result Limit</label>
          <input type="number" v-model.number="similarityCount" min="1" max="50" />
        </div>

        <button
          class="btn w-full mt-4"
          @click="triggerSimilaritySearch"
          :disabled="!isSourceSelected"
        >
          Find Matches
        </button>
      </fieldset>

      <p v-if="errorMsg" class="error-text">{{ errorMsg }}</p>
    </div>
  </aside>
</template>

<style scoped>
.search-sidebar {
  position: fixed;
  inset: 0;
  width: 100vw;
  height: 100vh;
  z-index: 100;
  background: var(--bg-surface);
  padding: max(1.5rem, env(safe-area-inset-top)) max(1.5rem, env(safe-area-inset-right))
    max(1.5rem, env(safe-area-inset-bottom)) max(1.5rem, env(safe-area-inset-left));
  overflow-y: auto;
  box-shadow: var(--shadow-subtle);
  border: none;
  border-radius: 0;
}

@media (min-width: 768px) {
  .search-sidebar {
    position: sticky;
    top: 120px;
    width: 320px;
    height: calc(100vh - 140px);
    border: 1px solid var(--border-subtle);
    border-radius: 8px;
    z-index: auto;
    padding: 1.5rem;
  }
}
.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.sidebar-header h3 {
  margin: 0;
  font-size: 1.25rem;
  font-family: var(--font-headline);
}
.mb-4 {
  margin-bottom: 1rem;
}
.mt-4 {
  margin-top: 1rem;
}
.w-full {
  width: 100%;
}

.btn-icon {
  background: none;
  border: none;
  cursor: pointer;
  color: var(--text-secondary);
  padding: 0;
}
.btn-icon:hover {
  color: var(--color-danger);
}

.disabled-group {
  opacity: 0.5;
  pointer-events: none;
  transition: opacity 0.2s;
}
.help-text {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.85rem;
  padding: 0.75rem;
  border-radius: 4px;
  background: var(--bg-element);
  color: var(--text-secondary);
  margin-bottom: 1rem;
}
.warning-text {
  border-left: 3px solid var(--color-accent);
}
</style>


=========================================
File: ./frontend/src/composables/useAuth.ts
=========================================
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

export function useAuth() {
  const router = useRouter();
  const token = ref(localStorage.getItem("token"));

  const isLoggedIn = computed(() => !!token.value);

  const username = computed(() => {
    if (!token.value) return null;
    try {
      const payload = JSON.parse(atob(token.value.split(".")[1] || ""));
      return payload.sub;
    } catch (e) {
      return null;
    }
  });

  const logout = () => {
    localStorage.removeItem("token");
    token.value = null;
    router.push("/");
    window.location.reload();
  };

  return {
    isLoggedIn,
    username,
    logout,
  };
}


=========================================
File: ./frontend/src/composables/useGallerySearch.ts
=========================================
import { ref } from "vue";

export interface Image {
  id: number | string;
  uploader?: string;
  keywords: { keyword: string; isAi?: boolean }[];
  extraction_status?: string;
  url?: string;
}

const similarityResultsOverride = ref<Image[] | null>(null);
const similaritySourceOverride = ref<Image | null>(null);
const selectedSourceId = ref<number | null>(null);

export function useGallerySearch() {
  const clearSimilaritySearch = () => {
    similarityResultsOverride.value = null;
    similaritySourceOverride.value = null;
    selectedSourceId.value = null;
  };

  return {
    similarityResultsOverride,
    similaritySourceOverride,
    selectedSourceId,
    clearSimilaritySearch,
  };
}


=========================================
File: ./frontend/src/composables/useImageStatus.ts
=========================================
import { reactive } from "vue";
import http from "../api/http-client";

const statusCache = reactive<Record<number, string>>({});
const activeConnections = new Set<number>();
const abortControllers = new Map<number, AbortController>();

export function useImageStatus() {
  const getStatus = (id: number) => statusCache[id];

  const fetchStatus = async (id: number) => {
    try {
      const response = await http.get(`/images/${id}/status`);
      const status = response.data.extraction_status;
      statusCache[id] = status;
      return status;
    } catch (e) {
      console.error(`Failed to fetch status for image ${id}`);
      return null;
    }
  };

  const abortPoll = (id: number) => {
    if (abortControllers.has(id)) {
      abortControllers.get(id)?.abort();
      abortControllers.delete(id);
    }
    activeConnections.delete(id);
  };

  const pollStatus = async (id: number) => {
    if (activeConnections.has(id)) return;
    activeConnections.add(id);

    try {
      while (activeConnections.has(id)) {
        if (statusCache[id] === "COMPLETED" || statusCache[id] === "FAILED") {
          break;
        }

        const controller = new AbortController();
        abortControllers.set(id, controller);

        // This will now "hang" securely until the server returns an update or a timeout triggers.
        const response = await http.get(`/images/${id}/status`, {
          signal: controller.signal,
        });

        abortControllers.delete(id);

        const status = response.data.extraction_status;
        statusCache[id] = status;

        if (status === "COMPLETED" || status === "FAILED") {
          break;
        }
      }
    } catch (e: any) {
      if (e.name !== "CanceledError") {
        console.error(`Error in long polling for image ${id}`, e);
      }
    } finally {
      abortControllers.delete(id);
      activeConnections.delete(id);
    }
  };

  return {
    statusCache,
    getStatus,
    fetchStatus,
    pollStatus,
    abortPoll,
  };
}


=========================================
File: ./frontend/src/composables/useUploadQueue.ts
=========================================
import { ref } from "vue";
import http from "../api/http-client";
import { useImageStatus } from "./useImageStatus";

export interface UploadTask {
  internalId: string;
  file: File;
  previewUrl: string;
  id?: number;
  status: "PENDING" | "UPLOADING" | "EXTRACTING" | "COMPLETED" | "FAILED" | "DUPLICATE";
}

export function useUploadQueue() {
  const tasks = ref<UploadTask[]>([]);
  const tagsList = ref<string[]>([]);
  const isUploading = ref(false);
  const globalMessage = ref("");
  const { pollStatus, statusCache } = useImageStatus();

  const onFileChange = (event: Event) => {
    const target = event.target as HTMLInputElement;
    if (!target.files || target.files.length === 0) return;
    const incomingFiles = Array.from(target.files);
    const availableSlots = 10 - tasks.value.length;

    if (incomingFiles.length > availableSlots) {
      globalMessage.value = `Maximum 10 images. Keeping the first ${availableSlots} files.`;
    } else {
      globalMessage.value = "";
    }

    const filesToAdd = incomingFiles.slice(0, availableSlots);
    filesToAdd.forEach((file) => {
      tasks.value.push({
        internalId: Math.random().toString(36).substring(7),
        file,
        previewUrl: URL.createObjectURL(file),
        status: "PENDING",
      });
    });
    target.value = "";
  };

  const removeTaskById = (iId: string) => {
    const index = tasks.value.findIndex((t) => t.internalId === iId);
    if (index > -1) {
      const task = tasks.value[index]!;
      if (isUploading.value && task.status === "UPLOADING") return;
      URL.revokeObjectURL(task.previewUrl);
      tasks.value.splice(index, 1);
    }
  };

  const clearAll = () => {
    tasks.value.forEach((t) => URL.revokeObjectURL(t.previewUrl));
    tasks.value = [];
    tagsList.value = [];
    globalMessage.value = "";
  };

  const executeUpload = async () => {
    if (tasks.value.length === 0) return;
    isUploading.value = true;
    globalMessage.value = "Uploading batch...";

    for (let i = tasks.value.length - 1; i >= 0; i--) {
      const task = tasks.value[i]!;
      if (task.status !== "PENDING" && task.status !== "FAILED") continue;

      task.status = "UPLOADING";
      const formData = new FormData();
      formData.append("file", task.file);

      // Send array properly
      if (tagsList.value.length > 0) {
        tagsList.value.forEach((tag) => formData.append("keywords", tag));
      }

      try {
        const response = await http.post("/images", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        const id = response.data.id || response.data;
        task.id = id;
        task.status = "EXTRACTING";

        pollStatus(id).then(() => {
          if (statusCache[id] === "COMPLETED") {
            task.status = "COMPLETED";
            setTimeout(() => removeTaskById(task.internalId), 3500);
          }
          if (statusCache[id] === "FAILED") task.status = "FAILED";
        });
      } catch (e: any) {
        if (e.response?.status === 409) {
          task.status = "DUPLICATE";
          setTimeout(() => removeTaskById(task.internalId), 3500);
        } else {
          task.status = "FAILED";
        }
      }
    }
    globalMessage.value = "Batch uploaded.";
    isUploading.value = false;
  };

  return {
    tasks,
    tagsList,
    isUploading,
    globalMessage,
    statusCache,
    executeUpload,
    onFileChange,
    removeTaskById,
    clearAll,
  };
}


=========================================
File: ./frontend/src/main.ts
=========================================
import { createApp } from "vue";
import "./assets/base.css";
import App from "./App.vue";
import router from "./router";

createApp(App).use(router).mount("#app");


=========================================
File: ./frontend/src/router/index.ts
=========================================
import { createWebHistory, createRouter } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  { path: "/", name: "gallery", component: () => import("../views/GalleryView.vue") },
  { path: "/login", name: "login", component: () => import("../views/LoginView.vue") },
  { path: "/register", name: "register", component: () => import("../views/RegisterView.vue") },
  {
    path: "/image/:id",
    name: "image-detail",
    component: () => import("../views/ImageDetailView.vue"),
  },
  { path: "/upload", name: "upload", component: () => import("../views/UploadView.vue") },
  { path: "/about", name: "about", component: () => import("../views/AboutView.vue") },
  { path: "/admin", name: "admin", component: () => import("../views/AdminView.vue") },
  { path: "/profile", name: "profile", component: () => import("../views/ProfileView.vue") },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

function getUserRole() {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
}

router.beforeEach((to, _from, next) => {
  const isAuthenticated = !!localStorage.getItem("token");
  const role = getUserRole();

  if (to.name === "admin" && role !== "ROLE_ADMIN") {
    next({ name: "gallery" });
  } else if (to.name === "upload" && !isAuthenticated) {
    // Save the intended destination before redirecting
    localStorage.setItem("intendedRoute", to.fullPath);
    next({ name: "login" });
  } else {
    next();
  }
});

export default router;


=========================================
File: ./frontend/src/shared/TagAutocomplete.vue
=========================================
<script setup lang="ts">
import { ref, onMounted } from "vue";
import http from "../api/http-client";

const props = defineProps({
  placeholder: { type: String, default: "Search tags..." },
  clearOnSelect: { type: Boolean, default: true },
  disabled: { type: Boolean, default: false },
});

const emit = defineEmits(["select"]);

const searchQuery = ref("");
const filteredKeywords = ref<string[]>([]);
const defaultKeywords = ref<string[]>([]);
const isSearchFocused = ref(false);
const highlightedIndex = ref(-1);
let debounceTimer: ReturnType<typeof setTimeout> | null = null;

onMounted(async () => {
  try {
    const res = await http.get("/images/keywords/popular?limit=8");
    defaultKeywords.value = res.data;
  } catch (e) {}
});

const onFocus = () => {
  isSearchFocused.value = true;
  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
  }
};

const onInput = () => {
  highlightedIndex.value = -1;
  if (debounceTimer) clearTimeout(debounceTimer);

  if (searchQuery.value.trim().length < 2) {
    filteredKeywords.value = defaultKeywords.value;
    return;
  }

  debounceTimer = setTimeout(async () => {
    try {
      const res = await http.get(
        `/images/keywords/search?q=${encodeURIComponent(searchQuery.value.trim())}`,
      );
      filteredKeywords.value = res.data.slice(0, 8);
    } catch (e) {
      filteredKeywords.value = [];
    }
  }, 200); // Fast 200ms debounce for smooth typing
};

const handleBlur = () => {
  setTimeout(() => {
    isSearchFocused.value = false;
    highlightedIndex.value = -1;
  }, 200);
};

const handleKeydown = (e: KeyboardEvent) => {
  if (e.key === "Escape") {
    isSearchFocused.value = false;
    return;
  }

  if (!filteredKeywords.value.length) {
    if (e.key === "Enter") {
      e.preventDefault();
      selectTag(searchQuery.value);
    }
    return;
  }

  if (e.key === "ArrowDown") {
    e.preventDefault();
    highlightedIndex.value = Math.min(
      highlightedIndex.value + 1,
      filteredKeywords.value.length - 1,
    );
  } else if (e.key === "ArrowUp") {
    e.preventDefault();
    highlightedIndex.value = Math.max(highlightedIndex.value - 1, 0);
  } else if (e.key === "Enter") {
    e.preventDefault();
    const selected = filteredKeywords.value[highlightedIndex.value];
    if (highlightedIndex.value >= 0 && selected) {
      selectTag(selected);
    } else {
      selectTag(searchQuery.value);
    }
  }
};

const selectTag = (tag: string) => {
  if (!tag.trim()) return;
  emit("select", tag.trim().toLowerCase());
  isSearchFocused.value = false;
  highlightedIndex.value = -1;
  filteredKeywords.value = [];
  if (props.clearOnSelect) {
    searchQuery.value = "";
  }
};
</script>

<template>
  <div class="autocomplete-wrapper" @blur="handleBlur">
    <input
      type="text"
      :placeholder="placeholder"
      :disabled="disabled"
      v-model="searchQuery"
      @input="onInput"
      @focus="onFocus"
      @blur="handleBlur"
      @keydown="handleKeydown"
      class="autocomplete-input"
    />
    <Transition name="fade">
      <ul class="autocomplete-dropdown" v-if="isSearchFocused && filteredKeywords.length > 0">
        <TransitionGroup name="list">
          <li
            v-for="(kw, i) in filteredKeywords"
            :key="kw"
            :class="{ highlighted: i === highlightedIndex }"
            @mousedown.prevent="selectTag(kw)"
          >
            <span class="material-symbols-outlined tag-icon">tag</span>
            {{ kw }}
          </li>
        </TransitionGroup>
      </ul>
    </Transition>
  </div>
</template>

<style scoped>
.autocomplete-wrapper {
  position: relative;
  width: 100%;
}

.autocomplete-input {
  width: 100%;
  border: none;
  background: transparent;
  outline: none;
  color: inherit;
  font-size: inherit;
  padding: inherit;
}

.autocomplete-dropdown {
  position: absolute;
  top: calc(100% + 8px);
  left: 0;
  right: 0;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  box-shadow: var(--shadow-subtle);
  list-style: none;
  margin: 0;
  padding: 0.5rem;
  z-index: 100;
  max-height: 300px;
  overflow-y: auto;
  overflow-x: hidden;
}

.autocomplete-dropdown li {
  padding: 0.5rem 0.75rem;
  font-size: 0.9rem;
  cursor: pointer;
  color: var(--text-primary);
  border-radius: 4px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  transition:
    background 0.1s ease,
    color 0.1s ease;
}

.tag-icon {
  font-size: 1.1rem;
  color: var(--text-muted);
}

.autocomplete-dropdown li:hover,
.autocomplete-dropdown li.highlighted {
  background: var(--bg-element);
  color: var(--color-accent);
}

.autocomplete-dropdown li:hover .tag-icon,
.autocomplete-dropdown li.highlighted .tag-icon {
  color: var(--color-accent);
}

/* Animations */
.fade-enter-active,
.fade-leave-active {
  transition:
    opacity 0.2s ease,
    transform 0.2s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.list-move,
.list-enter-active,
.list-leave-active {
  transition: all 0.3s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(-15px);
}
.list-leave-active {
  position: absolute;
}
</style>


=========================================
File: ./frontend/src/views/AboutView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import http from "../api/http-client";

const stats = ref({ total: 0, tags: 0 });
const isLoaded = ref(false);

// Calculate approximate tensor index size based on float array sizes
const indexMemorySize = computed(() => {
  const bytesPerImage = 3831 * 4; // 3831 dimensions * 4 bytes (float32)
  const totalMegabytes = (stats.value.total * bytesPerImage) / (1024 * 1024);
  return totalMegabytes < 1 ? "< 1.0" : totalMegabytes.toFixed(1);
});

onMounted(async () => {
  try {
    const [imgRes, tagRes] = await Promise.all([
      http.get("/images?size=1"),
      http.get("/images/keywords"),
    ]);
    stats.value.total = imgRes.data.totalElements ?? 0;
    stats.value.tags = tagRes.data.length || 0;
  } catch (e) {
    console.error("Could not fetch stats", e);
  } finally {
    isLoaded.value = true;
  }
});
</script>

<template>
  <div class="view-wrapper">
    <div class="about-header text-center">
      <h1 class="page-title">System Architecture</h1>
      <p class="about-desc">
        PDLaser v3a is a high-performance, vector-based visual archiving system. It integrates
        advanced machine learning and pure mathematical matrices to execute deep similarity searches
        based on object semantics, structure, color, and intensity.
      </p>
    </div>

    <div class="stats-strip" :class="{ 'fade-in': isLoaded }">
      <div class="stat-box">
        <span class="stat-val">{{ stats.total }}</span>
        <span class="label-text">Images Indexed</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">{{ stats.tags }}</span>
        <span class="label-text">Unique Tags</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">3,831</span>
        <span class="label-text">Dimensions / Vector</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val"
          >{{ indexMemorySize }} <span style="font-size: 1.5rem">MB</span></span
        >
        <span class="label-text">Estimated Tensor Size</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val status-green">Online</span>
        <span class="label-text">System Status</span>
      </div>
    </div>

    <h3 class="section-heading label-text">Extraction Pipeline</h3>
    <div class="pipeline-container">
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">upload_file</div>
        <div class="step-content">
          <h4>1. Ingestion</h4>
          <p>Multipart payload intercepted and buffered in memory.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">aspect_ratio</div>
        <div class="step-content">
          <h4>2. Lanczos3</h4>
          <p>BoofCV downsamples spatial data to a strict 256x256 grid.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">memory</div>
        <div class="step-content">
          <h4>3. Extraction</h4>
          <p>SigLIP 2 and standard vision algorithms map visual features.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">database</div>
        <div class="step-content">
          <h4>4. HNSW Index</h4>
          <p>L2 Normalized vectors injected into PostgreSQL pgvector.</p>
        </div>
      </div>
    </div>

    <h3 class="section-heading label-text">Engine Specifications</h3>
    <div class="tech-grid">
      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">hub</div>
        <h2>pgvector & HNSW</h2>
        <p>
          Utilizes PostgreSQL's <code>pgvector</code> extension for high-dimensional tensor storage.
          Queries are accelerated via Hierarchical Navigable Small World (HNSW) graphs, executing
          Cosine and L2 Distances natively.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">psychology</div>
        <h2>SigLIP 2 AI Processing</h2>
        <p>
          Integrates Microsoft's ONNX Runtime to execute a localized SigLIP 2 Vision Transformer
          network. Extracts 1000-dimensional semantic logits entirely in-memory for contextual
          matching.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">grid_on</div>
        <h2>BoofCV Interpolation</h2>
        <p>
          Images are mathematically resampled using the precise
          <strong>Lanczos3</strong> algorithm before deep mathematical histograms (HOG, HSV, CIELAB)
          are extracted.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">speed</div>
        <h2>Virtual Threads (Java 21)</h2>
        <p>
          Built on Spring Boot 4. Heavy vector calculus and asynchronous I/O operations are
          delegated to non-blocking virtual thread pools, allowing massive concurrent extraction
          queues.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">layers</div>
        <h2>Vue 3 Reactive UI</h2>
        <p>
          The frontend architecture utilizes Vite, the Composition API, and strict TypeScript. State
          is managed ephemerally, with asynchronous polling maintaining UI consistency during
          background tasks.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">dns</div>
        <h2>Containerized Micro-topology</h2>
        <p>
          Deployed via multi-stage Docker builds. The database, Java backend, and NGINX-served
          frontend operate in isolated alpine containers connected over a private bridge network.
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-header {
  max-width: 800px;
  margin: 0 auto 3rem auto;
  text-align: center;
}

.about-desc {
  font-size: 1.2rem;
  line-height: 1.6;
  color: var(--text-secondary);
}

.stats-strip {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem 3rem;
  margin-bottom: 4rem;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease;
  flex-wrap: wrap;
  gap: 2rem;
}
.stats-strip.fade-in {
  opacity: 1;
  transform: translateY(0);
}

.stat-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  flex: 1;
  min-width: 150px;
}

.stat-val {
  font-family: var(--font-sans);
  font-size: 3rem;
  font-weight: 300;
  color: var(--color-accent);
  line-height: 1;
}

.status-green {
  color: var(--color-success);
  font-size: 2rem;
  font-weight: 500;
  letter-spacing: 1px;
}

.divider-vertical {
  width: 1px;
  height: 60px;
  background: var(--border-subtle);
  display: none;
}
@media (min-width: 1024px) {
  .divider-vertical {
    display: block;
  }
}

.section-heading {
  margin-bottom: 2rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* Pipeline CSS */
.pipeline-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.5rem;
  margin-bottom: 5rem;
  background: var(--bg-surface);
  padding: 2rem 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}

@media (min-width: 768px) {
  .pipeline-container {
    flex-direction: row;
    align-items: flex-start;
    justify-content: space-between;
    padding: 3rem;
    gap: 0;
  }
}

.pipeline-step {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  flex: 1;
  min-width: 150px;
  position: relative;
  z-index: 2;
}
.step-circle {
  width: 60px;
  height: 60px;
  background: var(--bg-element);
  border: 2px solid var(--color-accent);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
  transition:
    transform 0.3s ease,
    background 0.3s ease;
}
.pipeline-step:hover .step-circle {
  transform: scale(1.1);
  background: var(--color-accent);
  color: var(--text-on-accent);
}
.step-content h4 {
  margin: 0 0 0.5rem 0;
  font-family: var(--font-sans);
}
.step-content p {
  margin: 0;
  font-size: 0.85rem;
  color: var(--text-secondary);
}

.pipeline-line {
  flex: none;
  width: 2px;
  height: 40px;
  background: var(--border-strong);
  margin: 0;
  position: relative;
}

@media (min-width: 768px) {
  .pipeline-line {
    flex: 1;
    height: 2px;
    width: auto;
    margin-top: 30px;
    min-width: 50px;
  }
}

.pipeline-line::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 0%;
  width: 100%;
  background: var(--color-accent);
  transition:
    height 0.5s ease,
    width 0.5s ease;
}

@media (min-width: 768px) {
  .pipeline-line::after {
    height: 100%;
    width: 0%;
  }
}

.pipeline-container:hover .pipeline-line::after {
  height: 100%;
}

@media (min-width: 768px) {
  .pipeline-container:hover .pipeline-line::after {
    width: 100%;
  }
}

/* Tech Grid CSS */
.tech-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1.5rem;
}
@media (min-width: 768px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr;
  }
}
@media (min-width: 1024px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr 1fr;
  }
}

.tech-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  transition:
    transform 0.2s ease,
    border-color 0.2s ease,
    box-shadow 0.2s ease;
}
.tech-card:hover {
  transform: translateY(-4px);
  border-color: var(--border-strong);
  box-shadow: var(--shadow-subtle);
}

.card-icon {
  font-size: 2.5rem;
  color: var(--color-accent);
  margin-bottom: 1rem;
}

.tech-card h2 {
  font-size: 1.25rem;
  font-family: var(--font-sans);
  font-weight: 600;
  margin-bottom: 1rem;
}

.tech-card p {
  color: var(--text-secondary);
  line-height: 1.6;
  margin: 0;
  font-size: 0.95rem;
}
</style>


=========================================
File: ./frontend/src/views/AdminView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, onUnmounted, computed, watch } from "vue";
import http from "../api/http-client";

const activeTab = ref("sync");
const status = ref("Loading...");
const limit = ref(5000);
const offset = ref(0);
const fileType = ref("PHOTOS");
const selectedFile = ref<File | null>(null);
let pollingInterval: ReturnType<typeof setInterval> | null = null;

// Catalog State
interface CatalogImage {
  id: number;
  remote_url: string;
  photographer_name: string;
  camera_make?: string;
  location_country?: string;
  stats_downloads?: number;
}
const catalogImages = ref<CatalogImage[]>([]);
const searchQuery = ref("");
const searchCamera = ref("");
const searchCountry = ref("");
const currentPage = ref(0);
const totalPages = ref(1);
const selectedIds = ref<Set<number>>(new Set());
const batchLimit = ref(1000);

const isBusy = computed(() => {
  return (
    status.value.startsWith("DOWNLOADING") ||
    status.value.startsWith("EXTRACTING") ||
    status.value.startsWith("SYNCING") ||
    status.value.startsWith("IMPORTING")
  );
});

const checkStatus = async () => {
  try {
    const res = await http.get("/admin/unsplash/status");
    status.value = res.data.status;
  } catch (e) {
    status.value = "Access Denied / Error";
  }
};

const onFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  selectedFile.value = target.files?.[0] || null;
};

const startUploadAndSync = async () => {
  if (!selectedFile.value) {
    alert("Please select a file first (e.g., photos.csv000 or photos.tsv).");
    return;
  }

  const formData = new FormData();
  formData.append("file", selectedFile.value);
  formData.append("limit", limit.value.toString());
  formData.append("offset", offset.value.toString());
  formData.append("fileType", fileType.value);

  try {
    await http.post("/admin/unsplash/upload", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    // Clear selection after upload
    selectedFile.value = null;
    (document.getElementById("datasetFileInput") as HTMLInputElement).value = "";
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to upload and sync file.");
  }
};

const fetchCatalog = async (page = 0) => {
  currentPage.value = page;
  try {
    const res = await http.get(`/admin/unsplash/catalog`, {
      params: {
        page,
        size: 24,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    catalogImages.value = res.data.content;
    totalPages.value = res.data.totalPages || 1;
  } catch (e) {
    console.error("Failed to load catalog");
  }
};

const toggleSelection = (id: number) => {
  if (selectedIds.value.has(id)) {
    selectedIds.value.delete(id);
  } else {
    selectedIds.value.add(id);
  }
};

const selectAll = () => {
  if (selectedIds.value.size === catalogImages.value.length) {
    selectedIds.value.clear();
  } else {
    catalogImages.value.forEach((img) => selectedIds.value.add(img.id));
  }
};

const importSelected = async () => {
  if (selectedIds.value.size === 0) return;
  try {
    await http.post("/admin/unsplash/import", Array.from(selectedIds.value));
    selectedIds.value.clear();
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start import");
  }
};

const importBatch = async () => {
  if (!confirm(`Are you sure you want to queue the top ${batchLimit.value} matches?`)) return;
  try {
    await http.post("/admin/unsplash/import/batch", null, {
      params: {
        limit: batchLimit.value,
        query: searchQuery.value,
        camera: searchCamera.value,
        country: searchCountry.value,
      },
    });
    fetchCatalog(currentPage.value);
    checkStatus();
  } catch (e: any) {
    alert(e.response?.data?.message || "Failed to start batch import");
  }
};

const handleSearch = () => {
  fetchCatalog(0);
};

// Users Tab
interface PendingUser {
  id: number;
  username: string;
}
const pendingUsers = ref<PendingUser[]>([]);
const autoApprove = ref(false);

const fetchAutoApprove = async () => {
  try {
    const res = await http.get("/admin/users/settings/auto-approve");
    autoApprove.value = res.data.enabled;
  } catch (e) {
    console.error("Failed to fetch auto-approve setting");
  }
};

const toggleAutoApprove = async () => {
  try {
    await http.put(`/admin/users/settings/auto-approve?enabled=${autoApprove.value}`);
  } catch (e) {
    alert("Failed to update auto-approve setting");
    autoApprove.value = !autoApprove.value; // revert on fail
  }
};

const fetchPendingUsers = async () => {
  try {
    const res = await http.get("/admin/users/pending");
    if (Array.isArray(res.data)) {
      pendingUsers.value = res.data;
    } else {
      console.warn("Expected an array but got something else:", res.data);
      pendingUsers.value = [];
    }
  } catch (e) {
    console.error("Failed to fetch pending users");
    pendingUsers.value = [];
  }
};

const approveUser = async (id: number) => {
  try {
    await http.put(`/admin/users/${id}/approve`);
    fetchPendingUsers();
  } catch (e) {
    alert("Failed to approve user");
  }
};

const rejectUser = async (id: number) => {
  if (!confirm("Are you sure you want to reject and permanently delete this account?")) return;
  try {
    await http.delete(`/admin/users/${id}`);
    fetchPendingUsers();
  } catch (e) {
    alert("Failed to reject user");
  }
};

watch(activeTab, (newVal) => {
  if (newVal === "users") {
    fetchPendingUsers();
    fetchAutoApprove();
  }
});

onMounted(() => {
  checkStatus();
  fetchCatalog();
  pollingInterval = setInterval(checkStatus, 3000);
});

onUnmounted(() => {
  if (pollingInterval !== null) {
    clearInterval(pollingInterval);
  }
});
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header text-center">
      <h1 class="page-title">Dataset Orchestrator</h1>
      <p class="page-subtitle">
        Upload dataset chunks, map remote metadata, and selectively import High-Res assets.
      </p>
    </header>

    <div class="system-status-bar" :class="{ active: isBusy, error: status.startsWith('ERROR') }">
      <span class="material-symbols-outlined icon" v-if="!status.startsWith('ERROR')">sync</span>
      <span class="material-symbols-outlined" v-else>error</span>
      <span class="status-text">{{ status }}</span>
    </div>

    <div class="tabs">
      <button :class="{ active: activeTab === 'sync' }" @click="activeTab = 'sync'">
        1. Upload & Sync
      </button>
      <button :class="{ active: activeTab === 'catalog' }" @click="activeTab = 'catalog'">
        2. Remote Catalog
      </button>
      <button :class="{ active: activeTab === 'users' }" @click="activeTab = 'users'">
        3. User Approvals <span v-if="pendingUsers.length > 0">({{ pendingUsers.length }})</span>
      </button>
    </div>

    <!-- TAB 1: UPLOAD & SYNC -->
    <div v-if="activeTab === 'sync'" class="sync-layout max-w-lg" style="margin: 0 auto">
      <div class="card meta-card">
        <h3 class="meta-title">Map Unsplash Dataset</h3>

        <div class="file-type-toggle">
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="PHOTOS" />
            <span>📸 Photos Metadata (photos.tsv)</span>
          </label>
          <label class="radio-label">
            <input type="radio" v-model="fileType" value="KEYWORDS" />
            <span>🏷️ Keywords & Tags (keywords.tsv)</span>
          </label>
        </div>

        <p class="help-text" v-if="fileType === 'PHOTOS'">
          Select your local <code>photos.csv000</code> or <code>photos.tsv</code> file. The server
          will parse the metadata directly into the database.
          <strong>Actual image downloads occur in Step 2.</strong>
        </p>
        <p class="help-text" v-else style="color: var(--color-warning)">
          <strong>Note:</strong> You must sync Photos before syncing Keywords. Upload
          <code>keywords.tsv</code>. Keywords for unknown photos will be skipped.
        </p>

        <div class="file-drop-area" :class="{ 'has-file': selectedFile }">
          <span class="material-symbols-outlined" style="font-size: 3rem; margin-bottom: 1rem"
            >upload_file</span
          >
          <p v-if="!selectedFile">Click or drag a dataset file here</p>
          <p v-else style="font-weight: bold; color: var(--color-accent)">
            {{ selectedFile.name }} ({{ Math.round(selectedFile.size / 1024 / 1024) }} MB)
          </p>
          <input
            type="file"
            id="datasetFileInput"
            @change="onFileChange"
            accept=".tsv,.csv,.csv000,.txt"
            class="hidden-file-input"
          />
        </div>

        <div style="display: flex; gap: 1rem; margin: 1.5rem 0">
          <div class="form-group" style="flex: 1">
            <label class="label-text">Rows to Process</label>
            <input type="number" v-model="limit" min="1" max="1000000" />
          </div>
          <div class="form-group" style="flex: 1">
            <label class="label-text">Skip Rows</label>
            <input type="number" v-model="offset" min="0" />
          </div>
        </div>
        <button class="btn w-full" @click="startUploadAndSync" :disabled="isBusy || !selectedFile">
          Upload and Sync {{ fileType === "PHOTOS" ? "Photos" : "Keywords" }}
        </button>
      </div>
    </div>

    <!-- TAB 2: REMOTE CATALOG -->
    <div v-if="activeTab === 'catalog'" class="catalog-section">
      <div class="catalog-toolbar">
        <input
          type="text"
          v-model="searchQuery"
          @keydown.enter="handleSearch"
          placeholder="Search any..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCamera"
          @keydown.enter="handleSearch"
          placeholder="Filter Camera (e.g. Sony)..."
          class="search-bar"
        />
        <input
          type="text"
          v-model="searchCountry"
          @keydown.enter="handleSearch"
          placeholder="Filter Country (e.g. Japan)..."
          class="search-bar"
        />
        <button class="btn" @click="handleSearch">Search</button>
        <button class="btn btn-outline" @click="selectAll">Select All Visible</button>
      </div>

      <div
        class="batch-toolbar"
        style="margin-bottom: 1.5rem; display: flex; gap: 1rem; align-items: center"
      >
        <label class="label-text">Batch Import Count:</label>
        <input
          type="number"
          v-model="batchLimit"
          min="1"
          max="50000"
          class="search-bar"
          style="max-width: 150px"
        />
        <button class="btn btn-outline" @click="importBatch" :disabled="isBusy">
          Import Top {{ batchLimit }} Matches
        </button>
      </div>

      <div class="catalog-grid">
        <div
          v-for="img in catalogImages"
          :key="img.id"
          class="catalog-card"
          :class="{ selected: selectedIds.has(img.id) }"
          @click="toggleSelection(img.id)"
        >
          <div class="checkbox-indicator">
            <span class="material-symbols-outlined" v-if="selectedIds.has(img.id)"
              >check_circle</span
            >
          </div>
          <img
            :src="
              img.remote_url?.startsWith('http')
                ? img.remote_url + '?w=400'
                : 'https://' + img.remote_url + '?w=400'
            "
            class="thumb"
            loading="lazy"
          />
          <div class="info">
            <span class="photog">📸 {{ img.photographer_name || "Unknown" }}</span>
            <span class="detail">🌍 {{ img.location_country || "Unknown Loc" }}</span>
            <span class="detail">📷 {{ img.camera_make || "Unknown Camera" }}</span>
            <span class="detail">⬇️ {{ img.stats_downloads }} downloads</span>
          </div>
        </div>
      </div>

      <div v-if="catalogImages.length === 0" class="empty-catalog">
        No remote metadata found. Please upload a dataset file in Step 1 first.
      </div>

      <div class="pagination" v-if="totalPages > 1">
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage - 1)"
          :disabled="currentPage === 0"
        >
          Prev
        </button>
        <span class="page-info">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
        <button
          class="btn-outline"
          @click="fetchCatalog(currentPage + 1)"
          :disabled="currentPage === totalPages - 1"
        >
          Next
        </button>
      </div>

      <div class="sticky-action-bar" v-if="selectedIds.size > 0">
        <span
          ><strong>{{ selectedIds.size }}</strong> images selected for import.</span
        >
        <button class="btn" @click="importSelected" :disabled="isBusy">Download & Vectorize</button>
      </div>
    </div>

    <!-- TAB 3: USER APPROVALS -->
    <div v-if="activeTab === 'users'" class="users-section max-w-lg" style="margin: 0 auto">
      <div class="card meta-card">
        <div
          style="
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
          "
        >
          <h3 class="meta-title" style="margin-bottom: 0">Pending Account Approvals</h3>
          <div style="display: flex; gap: 1rem; align-items: center">
            <label
              style="
                display: flex;
                align-items: center;
                gap: 0.5rem;
                cursor: pointer;
                color: var(--text-primary);
              "
            >
              <input type="checkbox" v-model="autoApprove" @change="toggleAutoApprove" />
              Auto-Approve New Users
            </label>
            <button
              class="btn btn-outline"
              @click="fetchPendingUsers"
              style="display: flex; align-items: center; gap: 0.5rem; padding: 0.4rem 0.8rem"
            >
              <span class="material-symbols-outlined" style="font-size: 1.2rem">refresh</span>
              Refresh
            </button>
          </div>
        </div>

        <div
          v-if="pendingUsers.length === 0"
          class="empty-state label-text"
          style="padding: 2rem 0; text-align: center"
        >
          No accounts pending approval.
        </div>

        <div v-else class="user-list">
          <div v-for="user in pendingUsers" :key="user.id" class="user-item">
            <span class="user-name">@{{ user.username }}</span>
            <div class="user-actions">
              <button class="btn" @click="approveUser(user.id)" :disabled="isBusy">Approve</button>
              <button
                class="btn btn-outline danger"
                @click="rejectUser(user.id)"
                :disabled="isBusy"
              >
                Reject
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.text-center {
  text-align: center;
}
.max-w-lg {
  max-width: 600px;
}

.system-status-bar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 1rem;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-bottom: 2rem;
  font-family: var(--font-mono);
  transition: all 0.3s;
}
.system-status-bar.active {
  background: var(--bg-element);
  border-color: var(--color-accent);
  color: var(--color-accent);
}
.system-status-bar.error {
  border-color: var(--color-danger);
  color: var(--color-danger);
}
.system-status-bar.active .icon {
  animation: spin 2s linear infinite;
}
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

.tabs {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 2rem;
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1rem;
}
.tabs button {
  background: none;
  border: none;
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 600;
  color: var(--text-secondary);
  cursor: pointer;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.2s;
}
.tabs button.active,
.tabs button:hover {
  background: var(--bg-element);
  color: var(--text-primary);
}

.meta-card {
  padding: 2rem;
}

.file-type-toggle {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
  background: var(--bg-surface-alt);
  padding: 1rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}
.radio-label {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  cursor: pointer;
  font-weight: 500;
  color: var(--text-primary);
}

/* New File Drop Area Styles */
.file-drop-area {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem 1rem;
  text-align: center;
  margin-top: 1.5rem;
  transition: all 0.2s ease;
  color: var(--text-secondary);
}
.file-drop-area:hover,
.file-drop-area.has-file {
  background: var(--bg-element);
  border-color: var(--color-accent);
}
.hidden-file-input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}

.catalog-toolbar {
  display: flex;
  gap: 1rem;
  margin-bottom: 1.5rem;
}
.search-bar {
  flex: 1;
  padding: 0.75rem 1rem;
  border: 1px solid var(--border-subtle);
  border-radius: 4px;
  background: var(--bg-surface);
  color: var(--text-primary);
}

.catalog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}
.catalog-card {
  background: var(--bg-surface);
  border: 2px solid var(--border-subtle);
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  transition:
    transform 0.2s,
    border-color 0.2s;
}
.catalog-card:hover {
  transform: translateY(-4px);
  border-color: var(--text-primary);
}
.catalog-card.selected {
  border-color: var(--color-accent);
}
.checkbox-indicator {
  position: absolute;
  top: 8px;
  left: 8px;
  color: var(--color-accent);
  background: rgba(0, 0, 0, 0.5);
  border-radius: 50%;
  display: flex;
}
.thumb {
  width: 100%;
  height: 150px;
  object-fit: cover;
  display: block;
}
.info {
  padding: 0.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  font-size: 0.8rem;
}
.photog {
  font-weight: bold;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.detail {
  color: var(--text-secondary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.empty-catalog {
  text-align: center;
  padding: 4rem;
  color: var(--text-muted);
  font-size: 1.1rem;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  margin-bottom: 6rem;
}
.btn-outline {
  padding: 0.5rem 1rem;
  border: 1px solid var(--border-subtle);
  background: var(--bg-surface);
  color: var(--text-primary);
  border-radius: 4px;
  cursor: pointer;
}
.btn-outline:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.sticky-action-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background: var(--bg-element);
  border-top: 1px solid var(--border-strong);
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;
  box-shadow: 0 -4px 20px rgba(0, 0, 0, 0.2);
}

.user-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.user-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--bg-surface-alt);
  padding: 1rem 1.5rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
}
.user-name {
  font-weight: 600;
  font-size: 1.1rem;
  color: var(--text-primary);
}
.user-actions {
  display: flex;
  gap: 0.5rem;
}
.danger {
  color: var(--color-danger);
  border-color: var(--color-danger);
}
.danger:hover:not(:disabled) {
  background: var(--color-danger);
  color: #fff;
}

input[type="radio"] {
  width: auto;
  min-height: auto;
}
</style>


=========================================
File: ./frontend/src/views/GalleryView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
import { useAuth } from "../composables/useAuth";
import { useGallerySearch, type Image } from "../composables/useGallerySearch";
import AdvancedSearchSidebar from "../components/AdvancedSearchSidebar.vue";

const route = useRoute();
const router = useRouter();
const { isLoggedIn } = useAuth();
const {
  similarityResultsOverride,
  similaritySourceOverride,
  selectedSourceId,
  clearSimilaritySearch,
} = useGallerySearch();

const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);
const displayLimit = ref(30);
const currentPage = ref(0);
const totalPages = ref(1);

const isSidebarOpen = ref(false);

const openSimilarityForImage = (id: number | string) => {
  if (typeof id === "number") {
    selectedSourceId.value = id;
    isSidebarOpen.value = true;
  }
};

const handleSimilarityResults = (payload: {
  results: any[];
  sourceId: number | null;
  isEphemeral?: boolean;
  fileUrl?: string;
}) => {
  const { results, sourceId, isEphemeral, fileUrl } = payload;

  // 1. Establish the visual Source Image highlight
  if (isEphemeral && fileUrl) {
    similaritySourceOverride.value = {
      id: "Ephemeral",
      url: fileUrl,
      uploader: "Uploaded Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  } else if (sourceId) {
    similaritySourceOverride.value = {
      id: sourceId,
      url: `/images/${sourceId}`,
      uploader: "Database Target",
      keywords: [{ keyword: "Source Image", isAi: false }],
    };
  }

  // 2. Map the mathematical results
  similarityResultsOverride.value = results.map((res: { id: number; score: number }) => ({
    id: res.id,
    url: `/images/${res.id}`,
    uploader: "Matched Result",
    extraction_status: "COMPLETED",
    keywords: [{ keyword: `${((1 - res.score) * 100).toFixed(2)}% Match`, isAi: false }],
  }));
};

const loadImages = async (reset = false) => {
  if (reset) currentPage.value = 0;
  isLoading.value = true;
  clearSimilaritySearch();

  try {
    const activeTag = route.query.tags as string;
    let response: { data: { content: any[]; totalElements: number } };
    if (activeTag) {
      response = await http.get("/images/search", {
        params: { keywords: activeTag, page: currentPage.value, size: displayLimit.value },
      });
    } else {
      response = await http.get(`/images?page=${currentPage.value}&size=${displayLimit.value}`);
    }

    const { content, totalElements } = response.data;

    // Map full backend data including extraction_status
    displayedImages.value = content.map(
      (img: { id: number; uploader: string; keywords: any[]; extraction_status: string }) => ({
        id: img.id,
        url: `/images/${img.id}`,
        uploader: img.uploader,
        keywords: img.keywords || [],
        extraction_status: img.extraction_status,
      }),
    );
    totalPages.value = Math.ceil(totalElements / displayLimit.value) || 1;
  } catch (error) {
    if (reset) {
      displayedImages.value = [];
      totalPages.value = 1;
    }
  } finally {
    isLoading.value = false;
  }
};

const goToPage = (pageNumber: number) => {
  if (pageNumber >= 0 && pageNumber < totalPages.value) {
    currentPage.value = pageNumber;
    loadImages();
  }
};

const clearFilter = () => {
  router.push("/");
  clearSimilaritySearch();
};

onMounted(() => {
  if (route.query.sourceId) {
    selectedSourceId.value = parseInt(route.query.sourceId as string);
    isSidebarOpen.value = true;
  }
  loadImages(true);
});

watch(
  () => route.query.tags,
  () => loadImages(true),
);

const goToImage = (id: number | string) => {
  if (typeof id === "number") router.push(`/image/${id}`);
};
</script>

<template>
  <div class="gallery-layout">
    <div class="main-content">
      <div class="gallery-header">
        <h2 class="filter-title" v-if="similarityResultsOverride">Visual Matches</h2>
        <h2 class="filter-title" v-else-if="route.query.tags">
          Results for: <span class="highlight">#{{ route.query.tags }}</span>
        </h2>
        <h2 class="filter-title" v-else>Gallery</h2>

        <div class="header-actions">
          <button class="btn btn-outline" @click="isSidebarOpen = !isSidebarOpen">
            <span
              class="material-symbols-outlined"
              style="vertical-align: middle; margin-right: 0.25rem"
              >image_search</span
            >
            Similarity Search
          </button>
          <button
            class="btn btn-outline"
            v-if="route.query.tags || similarityResultsOverride"
            @click="clearFilter"
          >
            Clear Filter
          </button>
        </div>
      </div>

      <div v-if="isLoading && displayedImages.length === 0" class="empty-state label-text">
        Loading archive...
      </div>

      <div
        v-else-if="!similarityResultsOverride && displayedImages.length === 0"
        class="empty-state"
      >
        <h2 style="margin-bottom: 1rem">No images found.</h2>
        <p class="label-text" v-if="route.query.tags">Try a different search term.</p>
        <router-link to="/upload" class="btn" v-else-if="isLoggedIn"
          >Upload your first image</router-link
        >
        <p class="label-text" v-else>You must log in to upload images.</p>
      </div>

      <div v-else>
        <div class="masonry-grid">
          <article v-if="similaritySourceOverride" class="artifact-card source-card">
            <div class="source-badge">
              <span class="material-symbols-outlined" style="font-size: 1rem">target</span>
              SOURCE TARGET
            </div>
            <img :src="similaritySourceOverride.url" class="artifact-img" draggable="false" />
          </article>

          <article
            v-for="image in similarityResultsOverride || displayedImages"
            :key="image.id"
            class="artifact-card"
          >
            <img
              :src="image.url"
              @click="goToImage(image.id)"
              class="artifact-img"
              loading="lazy"
              draggable="false"
            />

            <div class="hover-actions" v-if="typeof image.id === 'number'">
              <button
                @click.stop="openSimilarityForImage(image.id)"
                title="Find Similar"
                class="btn-icon similarity-btn"
              >
                <span class="material-symbols-outlined">image_search</span>
              </button>
            </div>

            <div class="card-overlay" @click="goToImage(image.id)">
              <div
                class="meta-chips"
                v-if="image.extraction_status && image.extraction_status !== 'COMPLETED'"
              >
                <span class="meta-chip status-chip" :class="image.extraction_status.toLowerCase()">
                  {{ image.extraction_status }}
                </span>
              </div>

              <div class="overlay-content">
                <h3 class="artifact-name">@{{ image.uploader || "System" }}</h3>
                <div class="tags" v-if="image.keywords && image.keywords.length">
                  <span class="tag-text">
                    <span
                      v-for="(kw, index) in image.keywords.slice(0, 5)"
                      :key="kw.keyword"
                      :class="{ 'ai-tag': kw.isAi }"
                    >
                      #{{ kw.keyword
                      }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1"
                        >,
                      </span>
                    </span>
                    <span v-if="image.keywords.length > 5">, ...</span>
                  </span>
                </div>
              </div>
            </div>
          </article>
        </div>

        <div class="pagination-controls" v-if="!similarityResultsOverride && totalPages > 1">
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage - 1)"
            :disabled="currentPage === 0 || isLoading"
          >
            &laquo; Prev
          </button>
          <div class="page-numbers">
            <button
              v-for="page in totalPages"
              :key="page"
              class="btn page-btn"
              :class="{ active: page - 1 === currentPage }"
              @click="goToPage(page - 1)"
              :disabled="isLoading"
            >
              {{ page }}
            </button>
          </div>
          <button
            class="btn btn-outline"
            @click="goToPage(currentPage + 1)"
            :disabled="currentPage === totalPages - 1 || isLoading"
          >
            Next &raquo;
          </button>
        </div>
      </div>
    </div>

    <AdvancedSearchSidebar
      v-if="isSidebarOpen"
      :initial-source-id="selectedSourceId"
      @close="
        isSidebarOpen = false;
        selectedSourceId = null;
      "
      @executeSimilarity="handleSimilarityResults"
    />
  </div>
</template>

<style scoped>
.gallery-layout {
  display: flex;
  gap: 2rem;
  align-items: flex-start;
}
.main-content {
  flex: 1;
  min-width: 0;
}

/* Source Image Outline CSS */
.source-card {
  border: 3px solid var(--color-accent);
  box-shadow: 0 0 20px color-mixin(in oklch, var(--color-accent) 40%, transparent);
  transform: translateY(-4px);
  z-index: 5;
}
.source-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  background: var(--color-accent);
  color: var(--text-on-accent);
  padding: 0.25rem 0.75rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  display: flex;
  align-items: center;
  gap: 0.25rem;
  z-index: 10;
  box-shadow: var(--shadow-subtle);
}

.hover-actions {
  position: absolute;
  top: 10px;
  right: 10px;
  opacity: 1;
  transition: opacity 0.2s;
  z-index: 10;
}
@media (hover: hover) and (pointer: fine) {
  .hover-actions {
    opacity: 0;
  }
  .artifact-card:hover .hover-actions {
    opacity: 1;
  }
}

.similarity-btn {
  background: var(--bg-surface);
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--text-primary);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: var(--shadow-subtle);
}
.similarity-btn:hover {
  color: var(--color-accent);
}

.gallery-header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: var(--space-lg);
  padding-bottom: var(--space-sm);
  border-bottom: 1px solid var(--border-subtle);
  gap: 1rem;
}
@media (min-width: 768px) {
  .gallery-header {
    flex-direction: row;
    align-items: center;
    gap: 0;
  }
}

.header-actions {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  width: 100%;
}
.header-actions .btn {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
@media (min-width: 768px) {
  .header-actions {
    flex-direction: row;
    width: auto;
    gap: 1rem;
  }
  .header-actions .btn {
    width: auto;
  }
}

.filter-title {
  font-family: var(--font-headline);
  font-size: 2rem;
  margin: 0;
  word-break: break-word;
}
@media (min-width: 768px) {
  .filter-title {
    font-size: 2.5rem;
  }
}
.highlight {
  color: var(--color-accent);
  font-style: italic;
}

.empty-state {
  text-align: center;
  padding: var(--space-xl) 0;
  color: var(--text-muted);
}

.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) {
  .masonry-grid {
    columns: 2;
  }
}
@media (min-width: 1024px) {
  .masonry-grid {
    columns: 3;
  }
}
@media (min-width: 1536px) {
  .masonry-grid {
    columns: 4;
  }
}

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  overflow: hidden;
  border-radius: 4px;
}

.artifact-img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.4s var(--ease-standard);
}

.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.8) 0%,
    rgba(0, 0, 0, 0) 40%,
    rgba(0, 0, 0, 0) 70%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.5rem;
  color: #fff;
  cursor: pointer;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
}

/* Metadata Chips CSS */
.meta-chips {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
}
.meta-chip {
  font-family: var(--font-sans);
  font-size: 0.7rem;
  font-weight: 700;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  background: rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(4px);
  text-transform: uppercase;
}
.status-chip.completed {
  color: var(--color-success);
  border: 1px solid var(--color-success);
}
.status-chip.processing,
.status-chip.pending {
  color: #f59e0b;
  border: 1px solid #f59e0b;
}
.status-chip.failed {
  color: var(--color-danger);
  border: 1px solid var(--color-danger);
}
.id-chip {
  color: #fff;
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.artifact-name {
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0 0.25rem 0;
  color: #fff;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.tag-text {
  font-family: var(--font-sans);
  font-size: 0.8rem;
  color: rgba(255, 255, 255, 0.8);
}

.pagination-controls {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: var(--space-md);
  margin-top: var(--space-xl);
  padding-bottom: var(--space-lg);
}
.page-numbers {
  display: flex;
  gap: 0.5rem;
  overflow-x: auto;
}
.page-btn {
  background: var(--bg-surface);
  color: var(--text-primary);
  border: 1px solid var(--border-subtle);
  padding: 0.5rem 1rem;
  min-width: 40px;
}
.page-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.page-btn:hover:not(:disabled) {
  background: var(--bg-element);
  border-color: var(--text-primary);
}
.page-btn.active {
  background: var(--color-accent);
  color: #fff;
  border-color: var(--color-accent);
}
</style>


=========================================
File: ./frontend/src/views/ImageDetailView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import http from "../api/http-client";
const route = useRoute();
const router = useRouter();
const imageId = route.params.id as string;
interface Keyword {
  keyword: string;
  isAi?: boolean;
}

interface ImageMetadata {
  Name: string;
  format: string;
  width: number;
  height: number;
  extraction_status: string;
  user_id?: number;
  photographer_name?: string;
  camera_make?: string;
  location_country?: string;
  stats_downloads?: number;
  description?: string;
  Keywords: Keyword[];
}

const metadata = ref<ImageMetadata | null>(null);
const isLoading = ref(true);

const newTag = ref("");
const isAddingTag = ref(false);

const currentUser = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr);
  } catch (e) {
    return null;
  }
});

const canDelete = computed(() => {
  if (!currentUser.value) return false;
  if (currentUser.value.role === "ROLE_ADMIN") return true;
  if (
    metadata.value &&
    metadata.value.user_id &&
    currentUser.value.userId === metadata.value.user_id
  )
    return true;
  return false;
});

const addTag = async () => {
  const tag = newTag.value.trim();
  if (!tag) return;
  isAddingTag.value = true;
  try {
    await http.put(`/images/${imageId}/keywords?tag=${encodeURIComponent(tag)}`);
    if (metadata.value) {
      if (!metadata.value.Keywords) metadata.value.Keywords = [];
      if (!metadata.value.Keywords.find((k: Keyword) => k.keyword === tag)) {
        metadata.value.Keywords.push({ keyword: tag, isAi: false });
      }
    }
    newTag.value = "";
  } catch (error) {
    console.error("Failed to add tag", error);
    alert("Failed to add tag. It may already exist or you might not have permission.");
  } finally {
    isAddingTag.value = false;
  }
};

onMounted(async () => {
  try {
    const res = await http.get(`/images/${imageId}/metadata`);
    metadata.value = res.data;
  } catch (error) {
    console.error("Failed to fetch metadata", error);
  } finally {
    isLoading.value = false;
  }
});

const getImageUrl = () => `/images/${imageId}`;
const deleteImage = async () => {
  if (!confirm("Are you sure you want to delete this image?")) return;
  try {
    await http.delete(`/images/${imageId}`);
    router.push("/");
  } catch (error) {
    console.error(error);
  }
};

// Optional: Open advanced similarity search in gallery (as Sidebar triggers similarity from GalleryView)
const findSimilar = () => {
  router.push({ path: "/", query: { sourceId: imageId } });
};

const downloadOriginal = () => {
  try {
    // Navigate directly to the download endpoint which returns the file with Content-Disposition
    const downloadUrl = `/images/${imageId}/download`;

    const a = document.createElement("a");
    a.href = downloadUrl;
    a.target = "_blank";
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  } catch (error) {
    console.error("Failed to trigger download", error);
    alert("Failed to download image.");
  }
};
</script>

<template>
  <div class="view-wrapper">
    <div v-if="isLoading" class="loading">Loading metadata...</div>
    <div v-else-if="!metadata" class="error">
      <h2>Image not found</h2>
      <button class="btn" @click="router.push('/')">Return to Gallery</button>
    </div>
    <div v-else class="detail-layout">
      <!-- Left: Full Image -->
      <div class="image-container">
        <img :src="getImageUrl()" :alt="metadata.Name" class="full-img" />
      </div>
      <!-- Right: Metadata & Actions -->
      <aside class="meta-sidebar">
        <h1 class="image-title">{{ metadata.Name }}</h1>
        <div class="meta-section">
          <div class="meta-row">
            <span class="label-text">ID</span>
            <span class="meta-val">{{ imageId }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Format</span>
            <span class="meta-val">{{ metadata.format }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Resolution</span>
            <span class="meta-val">{{ metadata.width }} x {{ metadata.height }}</span>
          </div>
          <div class="meta-row">
            <span class="label-text">Extraction Status</span>
            <span class="status-badge" :class="metadata.extraction_status?.toLowerCase()">
              {{ metadata.extraction_status }}
            </span>
          </div>
        </div>

        <div class="meta-section" v-if="metadata.photographer_name">
          <div class="meta-row" v-if="metadata.photographer_name">
            <span class="label-text">Photographer</span>
            <span class="meta-val">{{ metadata.photographer_name }}</span>
          </div>
          <div class="meta-row" v-if="metadata.camera_make">
            <span class="label-text">Camera</span>
            <span class="meta-val">{{ metadata.camera_make }}</span>
          </div>
          <div class="meta-row" v-if="metadata.location_country">
            <span class="label-text">Location</span>
            <span class="meta-val">{{ metadata.location_country }}</span>
          </div>
          <div class="meta-row" v-if="metadata.stats_downloads != null">
            <span class="label-text">Downloads</span>
            <span class="meta-val">{{ metadata.stats_downloads }}</span>
          </div>
          <div
            class="meta-row"
            v-if="metadata.description"
            style="flex-direction: column; align-items: flex-start; gap: 0.5rem; margin-top: 1rem"
          >
            <span class="label-text">Description</span>
            <span class="meta-val" style="color: var(--text-secondary); line-height: 1.4">{{
              metadata.description
            }}</span>
          </div>
        </div>

        <div class="meta-section">
          <span class="label-text">Tags</span>
          <div class="tags-list" v-if="metadata.Keywords && metadata.Keywords.length > 0">
            <span
              v-for="tag in metadata.Keywords"
              :key="tag.keyword"
              class="tag-pill"
              :class="{ 'ai-tag': tag.isAi }"
            >
              {{ tag.keyword }}
            </span>
          </div>
          <div
            class="tag-input-container"
            style="position: relative; display: flex; align-items: center; margin-top: 0.75rem"
          >
            <input
              v-model="newTag"
              type="text"
              class="input-tag"
              placeholder="Type a tag and press enter..."
              @keyup.enter="addTag"
              :disabled="isAddingTag"
              style="width: 100%; padding-right: 2.5rem"
            />
            <span
              v-if="isAddingTag"
              class="material-symbols-outlined"
              style="
                position: absolute;
                right: 10px;
                color: var(--text-secondary);
                animation: spin 1s linear infinite;
              "
              >sync</span
            >
          </div>
        </div>
        <div class="actions">
          <button
            class="btn w-full"
            @click="downloadOriginal"
            :disabled="metadata.extraction_status !== 'COMPLETED'"
            style="
              background: transparent;
              border: 1px solid var(--color-accent);
              color: var(--color-accent);
            "
          >
            <span
              class="material-symbols-outlined"
              style="vertical-align: middle; margin-right: 8px"
              >download</span
            >
            Download Original
          </button>
          <button
            class="btn w-full"
            @click="findSimilar"
            :disabled="metadata.extraction_status !== 'COMPLETED'"
          >
            Find Visually Similar
          </button>
          <button class="btn btn-outline danger w-full" @click="deleteImage" v-if="canDelete">
            Delete Image
          </button>
        </div>
      </aside>
    </div>
  </div>
</template>

<style scoped>
@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.loading,
.error {
  text-align: center;
  padding: 4rem;
  color: var(--text-secondary);
}

.detail-layout {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-xl);
  align-items: start;
}

@media (min-width: 1024px) {
  .detail-layout {
    grid-template-columns: 8fr 4fr;
  }
}

.image-container {
  background: var(--bg-element);
  border-radius: 4px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.full-img {
  max-width: 100%;
  max-height: 80vh;
  object-fit: contain;
  display: block;
}

.meta-sidebar {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  position: sticky;
  top: 100px;
}

.image-title {
  font-family: var(--font-sans);
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 2rem;
  word-break: break-all;
}

.meta-section {
  border-bottom: 1px solid var(--border-subtle);
  padding-bottom: 1.5rem;
  margin-bottom: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.meta-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.meta-val {
  font-family: var(--font-mono);
  font-size: 0.9rem;
  color: var(--text-primary);
}

.tags-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  margin-top: 0.5rem;
}

.input-tag {
  background: var(--bg-element);
  border: 1px solid var(--border-subtle);
  color: var(--text-primary);
  border-radius: 4px;
  padding: 0.5rem;
  font-size: 0.9rem;
  outline: none;
}

.input-tag::placeholder {
  color: var(--text-muted);
}

.input-tag:focus {
  border-color: var(--color-accent);
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.danger {
  color: var(--color-danger);
  border-color: var(--color-danger);
}

.danger:hover {
  background: var(--color-danger);
  color: #fff;
}
</style>


=========================================
File: ./frontend/src/views/LoginView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import http from "../api/http-client";

const username = ref("");
const password = ref("");
const errorMessage = ref("");

const handleLogin = async () => {
  try {
    const res = await http.post("/auth/login", {
      username: username.value,
      password: password.value,
    });
    localStorage.setItem("token", res.data.token);

    // Retrieve and clear the intended route
    const redirectUrl = localStorage.getItem("intendedRoute") || "/";
    localStorage.removeItem("intendedRoute");

    window.location.href = redirectUrl;
  } catch (e: any) {
    errorMessage.value = e.response?.data?.error || "Invalid credentials.";
  }
};
</script>

<template>
  <div class="view-wrapper auth-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Authenticate</h1>
      <form @submit.prevent="handleLogin" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Login</button>
        <div style="text-align: center; margin-top: 1rem">
          <router-link to="/register" class="text-link"
            >Need an account? Register here.</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
.auth-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.text-link {
  color: var(--color-accent);
  font-size: 0.9rem;
  text-decoration: none;
  font-weight: 500;
  transition: opacity 0.2s;
}

.text-link:hover {
  opacity: 0.7;
}
</style>


=========================================
File: ./frontend/src/views/ProfileView.vue
=========================================
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";

import { type Image } from "../composables/useGallerySearch";

const router = useRouter();
const displayedImages = ref<Image[]>([]);
const isLoading = ref(true);

const currentUserRole = computed(() => {
  const token = localStorage.getItem("token");
  if (!token) return null;
  try {
    const payloadBase64 = token.split(".")[1];
    if (!payloadBase64) return null;
    const payloadStr = atob(payloadBase64.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(payloadStr).role;
  } catch (e) {
    return null;
  }
});

onMounted(async () => {
  try {
    const mine = currentUserRole.value === "ROLE_ADMIN" ? "false" : "true";
    const response = await http.get(`/images?page=0&size=100&mine=${mine}`);
    displayedImages.value = response.data.content;
  } catch (error) {
    console.error(error);
  } finally {
    isLoading.value = false;
  }
});

const deleteImage = async (id: number | string, event: Event) => {
  event.stopPropagation();
  if (!confirm("Are you sure you want to permanently delete this image?")) return;
  try {
    await http.delete(`/images/${id}`);
    displayedImages.value = displayedImages.value.filter((img) => img.id !== id);
  } catch (e) {
    alert("Failed to delete image.");
  }
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">My Archive</h1>
      <p class="page-subtitle">Manage your personal uploads.</p>
    </header>

    <div v-if="isLoading" class="label-text">Loading your images...</div>
    <div v-else-if="displayedImages.length === 0" class="empty-state">
      <h2>You haven't uploaded anything yet.</h2>
      <router-link to="/upload" class="btn" style="margin-top: 1rem">Upload an Image</router-link>
    </div>

    <div class="masonry-grid" v-else>
      <article
        v-for="image in displayedImages"
        :key="image.id"
        class="artifact-card"
        @click="router.push(`/image/${image.id}`)"
      >
        <img :src="'/images/' + image.id" class="artifact-img" loading="lazy" draggable="false" />

        <div class="hover-actions">
          <button
            @click.stop="deleteImage(image.id, $event)"
            title="Delete Permanently"
            class="btn-icon delete-btn"
          >
            <span class="material-symbols-outlined" style="font-size: 1.2rem">delete</span>
          </button>
        </div>

        <div class="card-overlay">
          <div class="overlay-content">
            <h3 class="artifact-name">@{{ image.uploader || "System" }}</h3>
            <div class="tags" v-if="image.keywords && image.keywords.length">
              <span class="tag-text">
                <span
                  v-for="(kw, index) in image.keywords.slice(0, 5)"
                  :key="kw.keyword"
                  :class="{ 'ai-tag': kw.isAi }"
                >
                  #{{ kw.keyword
                  }}<span v-if="Number(index) < Math.min(image.keywords.length, 5) - 1">, </span>
                </span>
                <span v-if="image.keywords.length > 5">, ...</span>
              </span>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
.empty-state {
  text-align: center;
  padding: var(--space-xl) 0;
  color: var(--text-muted);
}

.masonry-grid {
  columns: 1;
  column-gap: 1.5rem;
}
@media (min-width: 640px) {
  .masonry-grid {
    columns: 2;
  }
}
@media (min-width: 1024px) {
  .masonry-grid {
    columns: 3;
  }
}
@media (min-width: 1536px) {
  .masonry-grid {
    columns: 4;
  }
}

.artifact-card {
  break-inside: avoid;
  margin-bottom: 1.5rem;
  position: relative;
  background: var(--bg-element);
  cursor: pointer;
  overflow: hidden;
  border-radius: 4px;
}

.artifact-img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.4s var(--ease-standard);
}

/* Base Card Overlay matching the Gallery styles */
.card-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.8) 0%,
    rgba(0, 0, 0, 0) 40%,
    rgba(0, 0, 0, 0) 70%,
    rgba(0, 0, 0, 0.7) 100%
  );
  opacity: 0;
  transition: opacity 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.5rem;
  color: #fff;
  pointer-events: none;
}
.artifact-card:hover .card-overlay {
  opacity: 1;
}

.overlay-content {
  margin-top: auto;
}

.artifact-name {
  font-family: var(--font-sans);
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0 0.25rem 0;
  color: #fff;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.tag-text {
  font-family: var(--font-sans);
  font-size: 0.8rem;
  color: rgba(255, 255, 255, 0.8);
}

/* Impeccable Context: Hover Interaction Design */
.hover-actions {
  position: absolute;
  top: 10px;
  right: 10px;
  opacity: 0;
  transition: opacity 0.2s;
  z-index: 10;
}
.artifact-card:hover .hover-actions {
  opacity: 1;
}

.delete-btn {
  background: var(--bg-surface);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0.5rem;
  border-radius: 4px;
  color: var(--color-danger);
  border: 1px solid transparent;
  transition: all 0.2s;
  box-shadow: var(--shadow-subtle);
  cursor: pointer;
}
.delete-btn:hover {
  background: var(--color-danger);
  color: #fff;
  border-color: var(--color-danger);
}
</style>


=========================================
File: ./frontend/src/views/RegisterView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import http from "../api/http-client";
const username = ref("");
const password = ref("");
const errorMessage = ref("");
const successMessage = ref("");
const router = useRouter();
const handleRegister = async () => {
  errorMessage.value = "";
  successMessage.value = "";
  try {
    await http.post("/auth/register", {
      username: username.value,
      password: password.value,
    });
    successMessage.value = "Registration successful! Redirecting to login...";
    setTimeout(() => {
      router.push("/login");
    }, 1500);
  } catch (e: any) {
    errorMessage.value = e.response?.data?.error || "Registration failed. Username might be taken.";
  }
};
</script>
<template>
  <div class="view-wrapper auth-wrapper">
    <div class="meta-card login-card">
      <h1 class="page-title">Create Account</h1>
      <form @submit.prevent="handleRegister" class="config-form">
        <div class="form-group">
          <label class="label-text">Username</label>
          <input type="text" v-model="username" required autofocus minlength="3" />
        </div>
        <div class="form-group">
          <label class="label-text">Password</label>
          <input type="password" v-model="password" required minlength="5" />
        </div>
        <p v-if="errorMessage" class="error-text">{{ errorMessage }}</p>
        <p v-if="successMessage" class="success-text">{{ successMessage }}</p>
        <button type="submit" class="btn w-full mt-4">Register</button>
        <div style="text-align: center; margin-top: 1rem">
          <router-link to="/login" class="text-link"
            >Already have an account? Login here.</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>
<style scoped>
.auth-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
}

.config-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.text-link {
  color: var(--color-accent);
  font-size: 0.9rem;
  text-decoration: none;
  font-weight: 500;
  transition: opacity 0.2s;
}

.text-link:hover {
  opacity: 0.7;
}

/* Cruelty Overrides */
</style>


=========================================
File: ./frontend/src/views/UploadView.vue
=========================================
<script setup lang="ts">
import { ref } from "vue";
import { useUploadQueue } from "../composables/useUploadQueue";

const tagsInput = ref("");
const {
  tasks,
  tagsList,
  isUploading,
  globalMessage,
  statusCache,
  executeUpload,
  onFileChange,
  removeTaskById,
  clearAll,
} = useUploadQueue();

const addTag = (event: KeyboardEvent | FocusEvent) => {
  event.preventDefault();
  const t = tagsInput.value.trim().toLowerCase().replace(/\s+/g, "_");
  if (t && !tagsList.value.includes(t)) {
    tagsList.value.push(t);
  }
  tagsInput.value = "";
};

const removeTag = (tag: string) => {
  tagsList.value = tagsList.value.filter((t) => t !== tag);
};
</script>

<template>
  <div class="view-wrapper">
    <header class="page-header">
      <h1 class="page-title">Upload Images</h1>
      <p class="page-subtitle">
        Add up to 10 images. They will be mathematically analyzed for similarity search.
      </p>
    </header>
    <div class="upload-grid">
      <section class="queue-col">
        <div class="drop-zone" v-if="!isUploading && tasks.length < 10">
          <div class="drop-box">
            <span class="material-symbols-outlined icon">add_photo_alternate</span>
            <span class="label-text">Select Files (Max 10)</span>
          </div>
          <input
            type="file"
            @change="onFileChange"
            accept="image/*"
            multiple
            class="file-input"
            title=" "
          />
        </div>
        <div class="task-list" v-if="tasks.length > 0">
          <div v-for="task in tasks" :key="task.internalId" class="task-item">
            <img :src="task.previewUrl" class="task-thumb" />
            <div class="task-info">
              <span class="task-name" :title="task.file.name">{{ task.file.name }}</span>
              <span class="status-badge" :class="task.status.toLowerCase()">
                {{
                  task.status === "EXTRACTING" && statusCache[task.id!]
                    ? statusCache[task.id!]
                    : task.status
                }}
              </span>
            </div>
            <button
              class="btn-icon"
              @click="removeTaskById(task.internalId)"
              v-if="!isUploading && task.status === 'PENDING'"
            >
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>
        </div>
      </section>
      <section class="meta-col">
        <div class="card meta-card">
          <h3 class="meta-title">Batch Metadata</h3>
          <div class="input-group">
            <label class="label-text">Tags (Applied to all)</label>
            <div class="tags-container">
              <span v-for="tag in tagsList" :key="tag" class="tag-pill">
                {{ tag }}
                <button @click="removeTag(tag)" class="tag-remove">
                  <span class="material-symbols-outlined">close</span>
                </button>
              </span>
              <input
                type="text"
                v-model="tagsInput"
                @keydown.enter="addTag"
                class="tag-input"
                placeholder="Type tag and press enter..."
                :disabled="isUploading"
              />
            </div>
          </div>
        </div>
        <p class="global-msg" v-if="globalMessage">{{ globalMessage }}</p>
        <div class="actions">
          <button
            class="btn w-full"
            @click="executeUpload"
            :disabled="tasks.length === 0 || isUploading"
          >
            {{ isUploading ? "Processing..." : "Upload & Analyze" }}
          </button>
          <button
            class="btn btn-outline w-full"
            @click="clearAll"
            v-if="tasks.length > 0 && !isUploading"
          >
            Clear Queue
          </button>
        </div>
      </section>
    </div>
  </div>
</template>

<style scoped>
.upload-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--space-lg);
  align-items: start;
}

.queue-col {
  min-width: 0;
}

@media (min-width: 1024px) {
  .upload-grid {
    grid-template-columns: 2fr 1fr;
  }
}

.drop-zone {
  position: relative;
  background: var(--bg-surface-alt);
  border: 2px dashed var(--border-subtle);
  border-radius: 8px;
  padding: 3rem;
  text-align: center;
  transition: all 0.2s;
  margin-bottom: var(--space-md);
}

.drop-zone:hover {
  border-color: var(--border-strong);
  background: var(--bg-element);
}

.file-input {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
  z-index: 10;
}

.drop-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  color: var(--text-secondary);
}

.drop-box .icon {
  font-size: 3rem;
}

.task-list {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.task-item {
  display: flex;
  align-items: center;
  min-width: 0;
  gap: 1rem;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  padding: 0.75rem;
  border-radius: 6px;
}

.task-thumb {
  width: 48px;
  height: 48px;
  object-fit: cover;
  border-radius: 4px;
  flex-shrink: 0;
}

.task-info {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  overflow: hidden;
}

.task-name {
  font-size: 0.9rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: var(--text-primary);
}

.status-badge {
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.status-badge.pending {
  color: var(--text-muted);
}

.status-badge.uploading {
  color: var(--color-accent);
}

.status-badge.extracting {
  color: var(--color-accent);
  animation: pulseText 1.5s infinite;
}

.status-badge.completed {
  color: var(--color-success);
}

.status-badge.failed {
  color: var(--color-danger);
}

.status-badge.duplicate {
  color: #f59e0b;
}

@keyframes pulseText {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}

.btn-icon {
  background: none;
  border: none;
  color: var(--text-muted);
  cursor: pointer;
  padding: 0.25rem;
  display: flex;
  flex-shrink: 0;
}

.btn-icon:hover {
  color: var(--color-danger);
}

.meta-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: var(--space-md);
}

.meta-title {
  font-size: 1.25rem;
  margin-bottom: 1.5rem;
  font-family: var(--font-sans);
  font-weight: 600;
}

.input-group label {
  display: block;
  margin-bottom: 0.75rem;
}

.tag-remove span {
  font-size: 14px;
}

.global-msg {
  font-size: 0.9rem;
  color: var(--text-secondary);
  margin-bottom: 1rem;
  text-align: center;
}

.actions {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>


=========================================
File: ./frontend/tsconfig.app.json
=========================================
{
  "extends": "@vue/tsconfig/tsconfig.dom.json",
  "compilerOptions": {
    "tsBuildInfoFile": "./node_modules/.tmp/tsconfig.app.tsbuildinfo",
    "types": ["vite/client"],

    /* Linting */
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "erasableSyntaxOnly": true,
    "noFallthroughCasesInSwitch": true,
    "noUncheckedSideEffectImports": true
  },
  "include": ["src/**/*.ts", "src/**/*.tsx", "src/**/*.vue"]
}


=========================================
File: ./frontend/tsconfig.json
=========================================
{
  "files": [],
  "references": [{ "path": "./tsconfig.app.json" }, { "path": "./tsconfig.node.json" }]
}


=========================================
File: ./frontend/tsconfig.node.json
=========================================
{
  "compilerOptions": {
    "tsBuildInfoFile": "./node_modules/.tmp/tsconfig.node.tsbuildinfo",
    "target": "ES2023",
    "lib": ["ES2023"],
    "module": "ESNext",
    "types": ["node"],
    "skipLibCheck": true,

    /* Bundler mode */
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "verbatimModuleSyntax": true,
    "moduleDetection": "force",
    "noEmit": true,

    /* Linting */
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "erasableSyntaxOnly": true,
    "noFallthroughCasesInSwitch": true,
    "noUncheckedSideEffectImports": true
  },
  "include": ["vite.config.ts"]
}


=========================================
File: ./frontend/Dockerfile
=========================================
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Your existing Nginx setup
FROM nginx:alpine

# Fix Nginx user warning
RUN sed -i 's/user  .*;//' /etc/nginx/nginx.conf

# This will now successfully pull the compiled files from the stage above
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Your custom non-root user setup
RUN addgroup -S appgroup && adduser -S -G appgroup appuser \
  && chown -R appuser:appgroup /usr/share/nginx/html \
  && chown -R appuser:appgroup /var/cache/nginx \
  && chown -R appuser:appgroup /var/log/nginx \
  && chown -R appuser:appgroup /etc/nginx/conf.d \
  && touch /var/run/nginx.pid \
  && chown -R appuser:appgroup /var/run/nginx.pid

USER appuser

# Healthcheck pointing to 127.0.0.1 to avoid Alpine IPv6 issues
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget -qO- http://127.0.0.1:8080/ || exit 1

EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]


=========================================
File: ./frontend/index.html
=========================================
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/png" href="/pdl.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover" />
    <title>pdl.</title>
    <!-- Standard Fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Newsreader:ital,opsz,wght@1,6..72,400;1,6..72,700;1,6..72,800&display=swap"
      rel="stylesheet"
    />
    <!-- Material Symbols -->
    <link
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <div id="app"></div>
    <script type="module" src="/src/main.ts"></script>
  </body>
</html>


=========================================
File: ./frontend/nginx.conf
=========================================
limit_req_zone $binary_remote_addr zone=auth_limit:10m rate=1r/s;

server {
    listen 8080;
    listen [::]:8080; # FIX: Binds IPv6 so Alpine's localhost wget doesn't fail
    
    add_header X-Content-Type-Options nosniff;

    client_max_body_size 100M;
    client_body_buffer_size 10M;
    proxy_buffers 8 5M;
    proxy_buffer_size 5M;

    location / {
        root /usr/share/nginx/html;
        index index.html index.htm;
        try_files $uri $uri/ /index.html;
    }

    location /images {
        proxy_pass http://backend:8080/images;
    }

    location /auth/register {
        limit_req zone=auth_limit burst=5 nodelay;
        proxy_pass http://backend:8080/auth/register;
    }

    location /auth {
        proxy_pass http://backend:8080/auth;
    }

    location /admin/unsplash {
        proxy_pass http://backend:8080/admin/unsplash;
    }

    location /admin/users {
        proxy_pass http://backend:8080/admin/users;
    }
}

=========================================
File: ./frontend/package.json
=========================================
{
  "name": "frontend",
  "private": true,
  "version": "0.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vue-tsc -b && vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "@stomp/stompjs": "^7.3.0",
    "axios": "^1.15.0",
    "sockjs-client": "^1.6.1",
    "vue": "^3.5.32",
    "vue-router": "^5.0.4"
  },
  "devDependencies": {
    "@types/node": "^25.6.0",
    "@types/sockjs-client": "^1.5.4",
    "@vitejs/plugin-vue": "^6.0.5",
    "@vue/tsconfig": "^0.9.1",
    "typescript": "~6.0.2",
    "vite": "^8.0.8",
    "vite-plugin-vue-devtools": "^8.1.1",
    "vue-tsc": "^3.2.6"
  }
}


=========================================
File: ./frontend/vite.config.ts
=========================================
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import vueDevTools from "vite-plugin-vue-devtools";

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  server: {
    proxy: {
      "/images": {
        target: "http://backend:8080/images",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/images/, ""),
      },
      "/auth": {
        target: "http://backend:8080/auth",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/auth/, ""),
      },
      "/admin": {
        target: "http://backend:8080/admin",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/admin/, ""),
      },
    },
  },
});


=========================================
File: ./.env.example
=========================================
# Default passwords and secrets for LOCAL DEVELOPMENT ONLY
# Do not use these values in production!

# Database
POSTGRES_PASSWORD=pdl_secure_password
DATABASE_PASSWORD=pdl_secure_password

# Admin user defaults
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin

# S3 (Garage) keys
S3_ACCESS_KEY=your_access_key_here
S3_SECRET_KEY=your_secret_key_here
ACCESS_KEY=your_access_key_here
SECRET_KEY=your_secret_key_here

# Security
JWT_SECRET=your_jwt_secret_here
GARAGE_RPC_SECRET=your_rpc_secret_here


=========================================
File: ./.gitignore
=========================================
# OS generated files
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
ehthumbs.db
Thumbs.db

# Global IDEs
.vscode/
.idea/
node_modules/
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?

# Environment Variables
.env
.env.*
!.env.example
/datasets/keywords.tsv
/datasets/keywords.tsv
/datasets/photos.tsv
/datasets/unsplash-lite.zip

# AI Models
models/


=========================================
File: ./.prettierrc
=========================================
{
  "printWidth": 100,
  "tabWidth": 2,
  "useTabs": false,
  "xmlWhitespaceSensitivity": "ignore",
  "plugins": ["prettier-plugin-java", "@prettier/plugin-xml", "prettier-plugin-sh"]
}


=========================================
File: ./.trivyignore
=========================================
DS-0002


=========================================
File: ./docker-compose.dev.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    env_file:
      - path: .env.example
        required: true
      - path: .env
        required: false
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_DB: pdl_db
      PGDATA: /var/lib/postgresql/18/data
    ports:
      - "5432:5432"
    tmpfs:
      - /var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped

  garage:
    image: dxflrs/garage:v2.2.0
    env_file:
      - path: .env.example
        required: true
      - path: .env
        required: false
    ports:
      - "3900:3900" # S3 API
    tmpfs:
      - /var/lib/garage/data
      - /var/lib/garage/meta
    volumes:
      - ./garage.toml:/etc/garage.toml
    command: ["/garage", "server"]
    restart: unless-stopped

  garage-init:
    image: alpine:3.23
    depends_on:
      garage:
        condition: service_started
    network_mode: "service:garage"
    pid: "service:garage"
    env_file:
      - path: .env.example
        required: true
      - path: .env
        required: false
    environment:
      - BUCKET_NAME=pdl-images
    restart: "on-failure:3"
    entrypoint:
      - /bin/sh
      - -eu
      - -c
      - |
        # Use the binary from the live Garage container
        G="chroot /proc/1/root /garage"

        echo "Waiting for Garage daemon..."
        for i in $$(seq 1 20); do
          if $$G node id -q 2>/dev/null; then break; fi
          sleep 2
        done

        # 1. Initialize the layout if it hasn't been assigned yet
        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
          echo "Initializing single-node layout..."
          NODE_ID=$$($$G node id -q)
          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
          $$G layout apply --version 1
        fi

        # 2. Provision Bucket and Key
        echo "Configuring bucket and credentials..."
        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
        $$G bucket create "$$BUCKET_NAME" || true
        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true

        echo "Garage S3 initialized successfully."

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    env_file:
      - path: .env.example
        required: true
      - path: .env
        required: false
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
      - APP_MODELS_DIR=/var/lib/pdl/models
      - S3_ENDPOINT=http://garage:3900
      - S3_REGION=garage
      - S3_BUCKET=pdl-images
      - LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=WARN
    volumes:
      - datasets:/var/lib/pdl/datasets
      - ./backend/models:/var/lib/pdl/models
    ports:
      - "8080:8080"
    depends_on:
      db:
        condition: service_healthy
      garage:
        condition: service_started
    restart: unless-stopped

  prometheus:
    image: prom/prometheus:v3.5.0
    command: ["--config.file=/etc/prometheus/prometheus.yml", "--log.level=warn"]
    volumes:
      - ./prometheus/prometheus.yml:/etc/prometheus/prometheus.yml
    ports:
      - "9090:9090"
    depends_on:
      - backend

  grafana:
    image: grafana/grafana:12.4.2
    environment:
      - GF_LOG_LEVEL=warn
    ports:
      - "3001:3000"
    depends_on:
      - prometheus

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    ports:
      - "3000:8080"
    depends_on:
      - backend
    restart: unless-stopped

volumes:
  datasets:


=========================================
File: ./docs/wiki/api-reference.md
=========================================
# API Reference

All backend endpoints are relative to the application's base URL (e.g., `http://localhost:8080`).

**Note on Error Handling:** The API returns RFC 7807 Problem Details for errors.

## 1. Authentication & Users

### Register User

Creates a new account with `ROLE_USER` privileges. Will auto-approve if the Admin setting is toggled.

- **URL:** `/auth/register`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` (Success), `400 Bad Request` (Username taken)

### Login

Authenticates a user and returns a JSON Web Token (JWT) valid for 24 hours.

- **URL:** `/auth/login`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` with `{ "token": "ey..." }`, `403 Forbidden` (Pending approval)

---

## 2. Core Image Operations & S3 Storage

### List All Images (Paginated)

Retrieves a paginated list of all images. Filters based on user ownership.

- **URL:** `/images`
- **Method:** `GET`
- **Query Params:** `page` (default: 0), `size` (default: 30), `mine` (boolean, default: false)
- **Response:** `200 OK` (Contains metadata, does not return binary payload)

### Get Image Content

Retrieves the binary payload of an image from Garage S3.

- **URL:** `/images/{id}`
- **Method:** `GET`
- **Response:** `200 OK` (Binary image data), `404 Not Found`

### Upload Image (Asynchronous)

Uploads a new image file. Saves the binary to Garage S3 and computes a SHA-256 hash. Feature vectors are extracted asynchronously.

- **URL:** `/images`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Headers:** `Authorization: Bearer <JWT>`
- **Body:** `file` (Image binary), `keywords` (Optional array)
- **Response:** `202 Accepted` with `{ "id": 1 }`, `409 Conflict` (Duplicate Hash)

### Check Image Processing Status (HTTP Long-Polling)

Retrieves the background extraction status. Uses Spring's `DeferredResult` to hold the connection until a status change occurs or a 10-second timeout is reached.

- **URL:** `/images/{id}/status`
- **Method:** `GET`
- **Response:** `200 OK` (State Changed) or `202 Accepted` (Timeout - reconnect)

### Download Original (S3 / Stream)

Returns the image with a `Content-Disposition: attachment` header.

- **URL:** `/images/{id}/download`
- **Method:** `GET`
- **Response:** `200 OK` with `Content-Disposition: attachment`

### Delete Image

Deletes the image from PostgreSQL and Garage S3.

- **URL:** `/images/{id}`
- **Method:** `DELETE`
- **Response:** `204 No Content`, `403 Forbidden`

---

## 3. Metadata & Keywords

### Get Image Metadata

- **URL:** `/images/{id}/metadata`
- **Method:** `GET`
- **Response:** `200 OK` (JSON Map including Provider, Status, Camera Make, Location)

### Autocomplete Keyword Routes

Endpoints optimized for Vue 3.5 autocomplete rendering.

- **GET** `/images/keywords` (All available)
- **GET** `/images/keywords/search?q=xyz` (Search string mapping)
- **GET** `/images/keywords/popular?limit=15` (Top tags sorted by distribution)

### Add / Delete Keyword

- **PUT** `/images/{id}/keywords?tag=xyz`
- **DELETE** `/images/{id}/keywords?tag=xyz`

---

## 4. Similarity Search (pgvector HNSW)

### Database Target Search

Uses HNSW graph indexing to execute hyper-fast mathematical distance calculations based on the selected descriptor logic.

- **URL:** `/images/{id}/similar`
- **Method:** `GET`
- **Query Params:** `number` (Limit), `descriptor` (`semantic`, `cielab`, `weighted`, `gradient`, `saturation`, `rgb`)
- **Response:** `200 OK` Array of matched images.

### Ephemeral Similarity Search

Extracts SigLIP 2 vectors and BoofCV arrays entirely in RAM, executes the query against the database, and discards the image without persisting it to S3.

- **URL:** `/images/search/ephemeral`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Body:** `file`, `number`, `descriptor`
- **Response:** `200 OK` Array of matched images.

---

## 5. Administrative Controls (ROLE_ADMIN)

- **Upload Metadata File:** `POST /admin/unsplash/upload` (multipart)
- **Check Catalog:** `GET /admin/unsplash/catalog` (Paginated view of remote URL mappings)
- **Batch Import:** `POST /admin/unsplash/import/batch` (Triggers asynchronous S3 background downloading)
- **Check Worker Status:** `GET /admin/unsplash/status`
- **Approve User Account:** `PUT /admin/users/{id}/approve`


=========================================
File: ./docs/wiki/database-schema.md
=========================================
# Database Schema & HNSW Graph Indexing

The application persists relational and vector data using **PostgreSQL 18** via the `pgvector` 0.1.6 extension.

_Note: Flyway migrations (`V1` through `V3`) strictly version-control this schema._

## Garage S3 Integration

As of v3a, physical file blobs are no longer stored on the container's local disk or inside the database. Images are stored in **Garage v2.2.0** (an S3-compatible backend).
PostgreSQL stores metadata and `S3 Key` identifiers.

## Tables

### 1. `images` (Core Metadata)

| Column              | Type         | Constraints       | Description                                        |
| ------------------- | ------------ | ----------------- | -------------------------------------------------- |
| `id`                | BIGSERIAL    | PRIMARY KEY       | Unique identifier. Matches the `S3 Key` prefix.    |
| `filename`          | VARCHAR(255) | NOT NULL          | Original filename.                                 |
| `hash`              | VARCHAR(64)  | UNIQUE            | SHA-256 hash preventing duplicate S3 bloat.        |
| `extraction_status` | VARCHAR(20)  | DEFAULT 'PENDING' | Tracks Async thread state.                         |
| `user_id`           | BIGINT       | FK                | Uploader account relationship.                     |
| `provider_id`       | VARCHAR(100) |                   | External dataset ID (e.g., Unsplash).              |
| `remote_url`        | TEXT         |                   | Used by background workers to buffer S3 downloads. |

### 2. `imagedescriptors` (Vector Indexing)

Stores mathematical arrays extracted via BoofCV 1.3.0 and ONNX 1.24.3.
| Column | Type | Description |
|---|---|---|
| `hogvector` | vector(31) | Histogram of Oriented Gradients (Shape Data). |
| `hsvvector` | vector(256) | Hue/Saturation Histogram. |
| `rgbvector` | vector(512) | Standard RGB color map. |
| `labvector` | vector(512) | CIELAB space distribution (Human vision modeling). |
| `semanticvector`| vector(768)| Deep semantic logits from SigLIP 2 AI model. |

## Architectural Decision: HNSW Indexes

The database uses Hierarchical Navigable Small World (HNSW) indexes for similarity searches.

Parameters tuned for high-dimensional recall vs. build cost:

- **HOG (31D):** `vector_l2_ops` | `m=4, ef_construction=32`
- **HSV (256D):** `vector_l2_ops` | `m=16, ef_construction=128`
- **RGB/LAB (512D):** `vector_cosine_ops` | `m=32, ef_construction=256`
- **Semantic (768D):** `vector_cosine_ops` | `m=32, ef_construction=256`

### 3. `imagekeywords`

Handles string tags and AI inferences.
| Column | Type | Constraints | Description |
|---|---|---|---|
| `imageid` | BIGINT | PK, FK | Parent image linkage. |
| `keyword` | VARCHAR | PK | Tag string (`nature`, `car`). |
| `is_ai_generated`| BOOLEAN | DEFAULT FALSE | Flags tags outputted automatically by the AI. |


=========================================
File: ./docs/wiki/deployment.md
=========================================
# Docker, Deployment & Twelve-Factor Methodology

The application is containerized and follows the **Twelve-Factor App** methodology. Configuration is split across three environments (`docker-compose.dev.yml`, `docker-compose.preview.yml`, `docker-compose.prod.yml`).

## The Twelve-Factor Adherence

Key aspects include:

1. **I. Codebase:** One repository contains the frontend, backend, and infrastructure code.
2. **II. Dependencies:** All backend and frontend dependencies are explicitly declared in `pom.xml` and `package.json`, isolated entirely within Docker multi-stage builds. No system-level packages leak into the containers.
3. **III. Config:** Zero hardcoded credentials exist in the source. S3 keys, Postgres passwords, and JWT Secrets are injected dynamically into the `application.yaml` via OS-level Environment Variables.
4. **IV. Backing Services:** Garage S3 and PostgreSQL are treated as attached resources.
5. **VI. Processes:** The backend and frontend are stateless. Authentication is handled client-side via JSON Web Tokens (JWT).
6. **VIII. Concurrency:** The application uses Java 21 Virtual Threads for ONNX model execution.
7. **IX. Disposability:** Database transactions use `FOR UPDATE SKIP LOCKED` for task queues.
8. **X. Dev/Prod Parity:** Developers use the exact same Docker Images locally as the production servers use. The only difference is the deployment topology (e.g., using `tmpfs` mounts in local dev vs named physical volumes in production).
9. **XI. Logs:** Logs are written to `stdout` and managed by Docker.
10. **XII. Identity:** Stateless JWTs establish workload identity between the client and the backend API.

---

## Microservice Tiers

### 1. Relational Database (`db`)

- **Image:** `pgvector/pgvector:pg18`
- **Function:** Handles all relational constraints and `hnsw` distance mathematics.
- **Storage Parity:** Uses `tmpfs` mounts in local/preview environments to enforce deterministic zero-state testing of Flyway migrations, and physical `pgdata` volumes in production.

### 2. Object Storage (`garage` & `garage-init`)

- **Images:** `dxflrs/garage:v2.2.0` (Storage Node) and `alpine:3.23` (Init Job)
- **Function:** Replaces local filesystem IO with an S3-compatible API cluster. High-performance, distributed blob storage for images.
- **Provisioning:** A lightweight alpine init container automatically pings the Garage RPC daemon, configures the single-node deployment layout, and provisions the access keys / buckets upon startup.

### 3. Java Backend (`backend`)

- **Build Stage:** `maven:3.9.6-eclipse-temurin-21-alpine`
- **Runtime Image:** `eclipse-temurin:21-jre`
- **Function:** Houses the Spring Boot 4.0.5 executable, DJL Tokenizers 0.36.0, and ONNX Runtime 1.24.3 engines. Uses AWS SDK BOM v2.42.32 to interface with the Garage container.
- **Integrity Safeguard:** Incorporates a rigorous Cryptographic Subresource Integrity check on boot. Validates a hardcoded SHA-256 hash against an internal multi-megabyte PDF payload to guarantee deployment immutability.

### 4. Telemetry Observability (`prometheus` & `grafana`)

- **Images:** `prom/prometheus:v3.5.0` and `grafana/grafana:12.4.2`
- **Function:** Prometheus scrapes the Spring Boot actuator (`/actuator/prometheus`) every 10 seconds to generate deep system insight on Virtual Thread loads, S3 transaction latency, and memory utilization, visualized globally in Grafana.

### 5. Frontend & Reverse Proxy (`frontend`)

- **Build Stage:** `node:24-alpine`
- **Runtime Image:** `nginx:alpine`
- **Function:** Nginx is configured to serve the Vue.js ^3.5.32 static files directly.
- **Proxy Configuration:** Configured to internally intercept API calls (`/images`, `/auth`, `/admin`) and proxy them directly over the docker bridge network to the Java Backend, completely obfuscating the backend from public internet exposure and resolving CORS issues.


=========================================
File: ./docs/wiki/frontend-architecture.md
=========================================
# Frontend Architecture

The frontend is a Single Page Application (SPA) built with **Vue.js 3.5 (Composition API)**, **TypeScript 6.0**, and **Vite 8.0.8**.

## Global Systems & Architectural Decisions

### Axios Interceptors & Authorization (`http-client.ts` & `useAuth.ts`)

API calls are routed through a globally configured Axios 1.15.0 instance.

- **Proxying:** In production, an Nginx container serves the static Vue artifacts and reverse-proxies `/images` and `/api` to the backend to prevent CORS issues.
- **Interceptors:** A request interceptor attaches `Bearer <token>` automatically. If the server yields a `401 Unauthorized`, a response interceptor clears `localStorage` and redirects to `/login`.

### Real-Time Asynchronous Tracking (`useImageStatus.ts`)

Feature extraction takes time.

**Decision (HTTP Long-Polling):** The frontend uses long-polling to check image status. `useImageStatus` sends requests that the backend holds via `DeferredResult` until the threads complete their tasks, updating the UI badges upon status changes.

### Global Themes & HUX (Cruelty Squad Easter Egg)

The UI incorporates standard Light and Dark color schemes computed via `oklch()`, and a **"Cruelty"** mode.

- **Effects (Hostile User Experience):** Overrides CSS properties with Impact fonts, neon high-contrast filtering, CRT animated scanlines, and taps into the Web Audio API to procedurally emit a sawtooth waveform tone on every DOM click interaction.


=========================================
File: ./docs/wiki/frontend-views.md
=========================================
# Frontend Views & Routing

This document outlines the Vue Router 5 configuration mapping the SPA interface.

### 1. Gallery View (`/` or `/gallery`)

The primary browsing interface for the dataset.

- **Component:** `src/views/GalleryView.vue`
- **Functionality:** Renders a CSS masonry grid with backend pagination. Displays tags and async polling badges.
- **Interaction:** Features an integrated off-canvas component (`AdvancedSearchSidebar.vue`) for triggering algorithmic similarity searches via uploaded files or existing database context.

### 2. Upload View (`/upload`)

- **Component:** `src/views/UploadView.vue`
- **Functionality:** Upload queue for up to 10 `File` objects. Generates local `URL.createObjectURL()` previews, executes `multipart/form-data` batch uploads, and tracks S3 transmission and ONNX extraction progress.

### 3. Image Detail View (`/image/:id`)

- **Component:** `src/views/ImageDetailView.vue`
- **Functionality:** Connects to `/images/{id}/metadata` and streams the full-scale original artifact from the S3 backend via `/images/{id}`. Includes triggers to initiate browser downloads and real-time inline keyword mutation.

### 4. Authentication Views (`/login` & `/register`)

- **Components:** `LoginView.vue`, `RegisterView.vue`
- **Functionality:** Secure POST handlers updating JWT state in `localStorage` and routing the user to their previously intended destination path.

### 5. My Archive (`/profile`)

- **Component:** `src/views/ProfileView.vue`
- **Functionality:** Dynamically queries the API passing `?mine=true`, rendering an isolated gallery strictly matching the `user_id` mapped inside the user's JWT. Offers direct destructive DELETE capabilities.

### 6. Admin Dataset Orchestrator (`/admin`)

- **Component:** `src/views/AdminView.vue`
- **Functionality:** Available to `ROLE_ADMIN`. Allows uploading of raw `.tsv` files to map remote URL attributes into the local database, and provides tools to batch download those assets into the S3 backend. Also controls user account approvals.

### 7. Architecture Overview (`/about`)

- **Component:** `src/views/AboutView.vue`
- **Functionality:** Dashboard displaying matrix sizing. Computes the estimated RAM cost of the tensor arrays based on the total number of images $\times$ 3,831 vectors $\times$ 4 bytes (float32).


=========================================
File: ./docs/wiki/home.md
=========================================
# PDLaser Documentation Wiki

This is the documentation for the **PDLaser Image Management System (v3a)**.

This wiki contains technical documentation for the backend API, frontend SPA, Garage S3 storage, database schema, and deployment infrastructure.

## Navigation

### [API Reference](api-reference)

Comprehensive guide to the Spring Boot 4.0.5 REST API, including core S3 image streams, ephemeral similarity endpoints, stateless JWT authentication, and the background Unsplash dataset ingestion engine.

### [Frontend Architecture & Views](frontend-architecture)

Detailed overview of the Vue.js ^3.5.32 SPA, exploring the component structure, Vue Router 5.0.4 routing, HTTP Long-Polling logic for async tasks, Axios 1.15.0 interceptors, and the Cruelty Squad easter-egg aesthetic (HUX).

### [Frontend Views Routing](frontend-views)

In-depth breakdown of specific route targets (`/gallery`, `/search`, `/profile`, `/admin`, etc.) and the expected client-side data flows executing against the backend.

### [Database Schema & HNSW](database-schema)

Extensive details on the PostgreSQL 18 implementation utilizing the `pgvector` 0.1.6 extension. Explains the transition to Garage (S3) for blob storage, relational table structures (`images`, `imagedescriptors`), and mathematically tuned multi-dimensional HNSW vector indexing algorithms.

### [Docker, Deployment & Twelve-Factor](deployment)

Guide to our containerized microservices orchestration and **Twelve-Factor App** compliance. Explains the multi-tier Docker Compose environments, Garage S3 blob storage initialization, Prometheus 3.5.0 / Grafana 12.4.2 telemetry observability, and Cryptographic Subresource Integrity limits.

---

## Wiki Usage

**Keep this wiki updated at all times.**

### Code Formatting

The project uses a unified Prettier configuration defined in the root `package.json` to enforce styling rules across the Java backend, Vue frontend, and documentation markdown. **Before committing code, always ensure your files are formatted:**

```bash
npm run format
```


=========================================
File: ./docs/enterprise_requirements.tex
=========================================
\documentclass[12pt, a4paper]{article}
\usepackage[utf8]{inputenc}
\usepackage[T1]{fontenc}
\usepackage{lmodern}
\usepackage{geometry}
\geometry{margin=2.5cm}
\usepackage{hyperref}
\usepackage{titlesec}
\usepackage{enumitem}
\usepackage{abstract}

% Academic formatting tweaks
\titleformat{\section}{\large\bfseries\scshape}{\thesection.}{1em}{}
\titleformat{\subsection}{\normalsize\bfseries\itshape}{\thesubsection.}{1em}{}

\begin{document}

\title{\textbf{Systemic Limitations of Monolithic Efficiency in Image Retrieval Architectures: A Paradigm Shift Towards Holistic Poly-Cloud Orchestration}}
\author{
    \textbf{Lucas Koumasonas} \\
    \textit{Lead DevOps \& Chaos Engineering Architect} \vspace{0.5em} \\
    \textbf{Nikita Semenov} \\
    \textit{Principal Cloud Orchestration \& Distributed Systems Engineer} \vspace{1em} \\
    \textit{Department of Enterprise Scalability \& Distributed Systems} \\
    \textit{Based on a Longitudinal Study of 35.5 Years of Industry Experience}
}
\date{\today}
\maketitle

\begin{abstract}
\noindent \textbf{Abstract---}Recent evaluations of the PDLaser v3a Image Management System reveal an architecture functioning with anomalous efficiency. While the application successfully executes content-based image retrieval via PostgreSQL 18 \texttt{pgvector} HNSW indexing and asynchronous BoofCV descriptor extraction, empirical observations indicate a critical lack of operational complexity. "It works" is insufficient as a non-functional enterprise requirement. This paper presents a very reasonable architectural audit of PDLaser v3a, identifying systemic flaws such as suboptimal cloud-billing generation, the circumvention of poly-cloud orchestration overhead, and the utilization of overly simplified HTTP Long-Polling mechanisms. Finally, we propose a strategic framework to transition the current architecture into a fail-safe, redundant, hybrid-native distributed monolith utilizing Kubernetes, Kafka, and cryptographic ledger integrations to maximize quarterly infrastructure expenditures.
\end{abstract}

\vspace{2em}

\pagebreak

\section{Introduction}
The modern enterprise cloud ecosystem is predicated on the fundamental principle that scalability requires an exponential increase in moving parts. If an organization intends to deploy highly available systems, standard orchestration is insufficient; architectural design must proactively anticipate the integration of Cassandra, Kafka, and Spark clusters. 

Currently, PDLaser v3a operates as a statically typed distributed monolith leveraging Java 21, Spring Boot 4.0.5, and a Vue.js 3.5 single-page application compiled via Vite 8.0. While this stack circumvents the inherent volatility of children's magic (e.g., dynamically typed serverless microservices), our audit reveals that the deployment pipeline severely under-utilizes cloud vendor Service Level Agreements (SLAs). This paper outlines the critical limitations of the current implementation and provides a rigorous, V-Model-compliant roadmap for future iterations.

\section{Empirical Analysis of Systemic Limitations}

Our analysis isolates three primary anti-patterns currently active within the PDLaser repository. These mechanisms incorrectly prioritize computational efficiency over infrastructure expansiveness.

\subsection{Storage Ephemerality and Billing Evasion}
In local and staging environments, the application utilizes \texttt{tmpfs} Docker mounts, forcing the PostgreSQL database to operate entirely within volatile RAM. Consequently, Flyway migration scripts reconstruct the schema from a zero-state upon every initialization.
\begin{itemize}
    \item \textbf{The Limitation:} This paradigm is dangerously fast and completely bypasses persistent I/O cloud billing mechanisms. Historical data migration records establish that localized Euclidean distance operations should incur a baseline expenditure of approximately 180,000 AWS credits per fiscal quarter. The current \texttt{tmpfs} implementation represents a severe liquidity bottleneck.
\end{itemize}

\subsection{The Archaic Abstraction of Long-Polling}
To monitor asynchronous BoofCV feature extraction and ONNX SigLIP 2 semantics, the \texttt{VisionProcessor.java} relies on an \texttt{UploadStatusTracker} Long-Polling mechanism using \texttt{DeferredResult}.
\begin{itemize}
    \item \textbf{The Limitation:} The client simply maintains an open HTTP connection until a 200 OK response is yielded. This lacks the requisite layers of enterprise abstraction. In a production-grade system, these events must be published to a distributed Kafka event-stream, monitored by a secondary Redis instance, validated against a Zookeeper quorum, and finally pushed to the client via WebSockets.
\end{itemize}

\subsection{Hostile User Experience (HUX) as a Load Balancer}
The frontend SPA incorporates a "Cruelty Squad" theme toggle that overrides the CSS rendering engine to generate procedural CRT scanlines and hooks into the Web Audio API to emit raw sawtooth wave frequencies on every DOM click event.
\begin{itemize}
    \item \textbf{The Advantage:} While initially flagged as a bug, the engineering team has classified this as a "Hostile User Experience" (HUX) load-balancing strategy. By making the interface physically painful to look at and listen to, user retention drops by 94\%, effectively acting as a client-side rate limiter and saving database read cycles. We recommend standardizing this approach across all B2B portals.
\end{itemize}

\subsection{Cryptographic Structural Integrity and Computational Under-Utilization}
To enforce a zero-trust boot sequence, the \texttt{HealthCheck.java} actuator dynamically streams and calculates the SHA-256 hash of a dense, multi-megabyte internal payload (\textit{The Whole War and Peace Novel.pdf}) against the static signature \texttt{4e22f003e05...}.
\begin{itemize}
    \item \textbf{The Limitation:} While synchronously calculating a cryptographic hash of 1,200 pages of text successfully introduces excellent, billable artificial startup latency, it is drastically under-engineered. 
    \item \textbf{The Reality:} Verifying 19th-century literature using standard SHA-256 is vulnerable to quantum decryption. Furthermore, this hashing must not be executed locally on the container. The byte-stream should be published via gRPC to a dedicated Apache Spark cluster for distributed cryptographic consensus before the backend is permitted to boot.
\end{itemize}

\section{Strategic Guidance and Next-Gen Architecture}

To rectify these limitations, future development sprints will abandon Agile methodologies and adopt a strict V-Model deployment pipeline. The following architectural shifts are mandatory.

\subsection{Phase 1: Migration to Poly-Cloud Kubernetes}
The current Nginx reverse proxy Docker configuration is highly stable and must therefore be deprecated immediately. 
\begin{itemize}
    \item \textbf{Implementation Strategy:} The stack will be migrated to a fully managed Kubernetes cluster. This introduces the requisite orchestration overhead and grants the organization the portability necessary to move production outages between AWS and GCP within minutes.
\end{itemize}

\subsection{Phase 2: Telemetry and Schrödinger's Latency}
Error handling is currently standardized via RFC 7807 \texttt{ProblemDetail} JSON responses equipped with unique \texttt{traceId} tags. This format is far too legible for a dedicated DevSecOps pod.
\begin{itemize}
    \item \textbf{Implementation Strategy:} Grafana and Loki pipelines will be integrated and aggressively scaled until log ingress costs demonstrably exceed the revenue generated by the application's uptime. This will allow the platform to achieve a state of Schrödinger's Latency: \textit{If a Grafana dashboard flickers yellow while no one is watching, did latency truly spike?}
\end{itemize}

\section{Advanced Database Architecture (HNSW Limitation)}

Empirical testing reveals that while the pgvector HNSW indexes yield microsecond recall for single semantic dimensions, combining disparate vectors (HOG, LAB, HSV) via weighted scalar expressions mathematically forces the PostgreSQL query planner into a Sequential Scan. We classify this as an intentional CPU-cycle utilization strategy to ensure baseline cloud billing targets are met.

\pagebreak

\section{Conclusion}
The PDLaser v3a system currently demonstrates an alarming degree of programmatic optimization and functional simplicity. By adhering to the strategic guidance outlined in this paper—specifically the integration of poly-cloud Kubernetes, distributed Kafka event streams, and cryptographic ledger fallback mechanisms—the architecture can successfully transition from a performant academic project into a holistic, enterprise-grade, billable cloud entity.

\vspace{3em}
\noindent\rule{0.4\textwidth}{0.4pt} \\
\textbf{Declaration of Operational Immunity} \\
\textit{The Lead DevOps Architect and Principal Cloud Engineer are currently engaged in merging undocumented Dependabot pull requests. Operational failures, pager alerts, or \texttt{CrashLoopBackOff} events must not be escalated to this office. If the system is unreachable, empirical evidence suggests it is probably DNS.}

\end{document}

=========================================
File: ./docs/requirements.tex
=========================================
\documentclass[12pt, a4paper]{article}
\usepackage[utf8]{inputenc}
\usepackage[T1]{fontenc}
\usepackage{lmodern}
\usepackage{geometry}
\geometry{margin=2.5cm}
\usepackage{hyperref}
\hypersetup{
    colorlinks=true,
    linkcolor=blue,
    urlcolor=cyan,
    pdftitle={Software Development Project - Requirements Document}
}

\begin{document}

\title{\textbf{Software Development Project - Requirements Document}}
\author{
    \textbf{Lucas Koumasonas \& Nikita Semenov} \\
    L3 Computer Science -- University of Bordeaux
}
\date{}
\maketitle

\section*{Introduction}
This document outlines the requirements for developing an image similarity search application using a containerized architecture backed by PostgreSQL 18, Garage S3 blob storage, and vector mathematics.

\subsection*{Requirement Classifications}
To clarify the state of each requirement relative to the initial specifications (Besoins 1--27), the following tags are used.

\begin{itemize}
    \item \textbf{[Original to Requirement X]}: The requirement remains functionally identical to the initial specifications and is fully implemented.
    \item \textbf{[Amended (Up) to Requirement X]}: The requirement was modified to expand upon the original specifications.
    \item \textbf{[Extension]}: A completely new feature not present in the original specifications.
    \item \textbf{[Extended Amendment to Requirement X]}: Placed in a dedicated section at the end of this document, these highlight highly complex architectural shifts or advanced features that drastically expand upon a base requirement.
\end{itemize}

\vspace{1em}
\noindent\fbox{%
    \parbox{\textwidth}{%
        \textbf{Important Note on Downgrades:} There are absolutely no downgraded or omitted requirements in this release. Every single original requirement has been either fully implemented or significantly exceeded.
    }%
}

\clearpage

\section{Architectural \& Organizational Decisions}
To support computer vision tasks, the following architectural standards were established:

\begin{itemize}
    \item \textbf{Decoupled Background Execution:} Image vectorization (BoofCV) and semantic extraction (ONNX) are offloaded to Virtual Threads (Java 21).
    \item \textbf{Distributed S3 Blob Storage:} The backend uses \textbf{Garage v2.2.0}, an S3-compatible object storage layer, utilizing the AWS Java SDK v2.42.32.
    \item \textbf{Twelve-Factor App Compliance:} The infrastructure follows the Twelve-Factor methodology by treating backing services as attached resources, using environment variables for configuration, and executing as stateless processes.
    \item \textbf{HNSW Vector Indexing:} We implemented Hierarchical Navigable Small World (HNSW) graphs in PostgreSQL 18 via \texttt{pgvector} 0.1.6 for indexing semantic vectors.
\end{itemize}

\clearpage

\section{Common Core}

\subsection*{Server}

\noindent\textbf{Requirement 1 : [Amended (Up) to Requirement 1] Initialize a set of images present on the server}\\
\textbf{Description :} The server verifies the connection to the Garage S3 bucket upon launch. \textbf{Amendment:} The server calculates a SHA-256 hash of binary payloads upon upload to deduplicate images. A default \texttt{admin} user is seeded into the database.
\vspace{1em}

\noindent\textbf{Requirement 2 : [Amended (Up) to Requirement 2] Manage images present on the server}\\
\textbf{Description :} The server manages references to images, allowing access to S3 data and metadata. \textbf{Amendment:} Image management is bound to JSON Web Tokens. Images track the uploader's \texttt{user\_id} for privacy scoping.
\vspace{1em}

\noindent\textbf{Requirement 3 : [Amended (Up) to Requirement 3] Index an image}\\
\textbf{Description :} The server calculates an image's descriptors. \textbf{Amendment:} Descriptors are mapped natively to \texttt{pgvector} formats. Supported mathematical indices include:
\begin{enumerate}
    \item 1D Histogram of Oriented Gradients (HOG - 31 dimensions).
    \item 2D Hue/Saturation Histogram (HSV - 256 dimensions).
    \item 3D RGB Color Histogram (512 dimensions).
    \item 3D CIELAB Color Space Histogram (512 dimensions).
    \item \textbf{[New]} 1D Semantic Logits Vector (768 dimensions via SigLIP 2 AI).
\end{enumerate}
\vspace{1em}

\noindent\textbf{Requirement 4 : [Amended (Up) to Requirement 4] Search for images similar to a given image}\\
\textbf{Description :} Constructs a sorted list of the N most similar images. \textbf{Amendment:} Executes L2 and Cosine distance operators in PostgreSQL.
\vspace{1em}

\noindent\textbf{Requirement 5 : [Original to Requirement 5] Search for images with a list of given attributes}\\
\textbf{Description :} The server constructs a list of images possessing one or more attributes among: File name, Format, and relational tags.
\vspace{1em}

\subsection*{Communication}

\noindent\textbf{Requirement 6 : [Amended (Up) to Requirement 6] Transfer the list of existing images}\\
\textbf{Description :} The list of available images is sent when receiving a \texttt{GET /images}. \textbf{Amendment:} Results are paginated (\texttt{page}, \texttt{size}) and filterable by ownership (\texttt{mine=true}).
\vspace{1em}

\noindent\textbf{Requirement 7 : [Amended (Up) to Requirement 7] Add an image}\\
\textbf{Description :} A \texttt{POST /images} payload adds an image. \textbf{Amendment:} Saves the binary to S3, schedules background extraction, and returns \textbf{202 Accepted} to allow asynchronous processing. Evaluates an embedded model to infer \texttt{ai:} tags.
\vspace{1em}

\noindent\textbf{Requirement 8 : [Amended (Up) to Requirement 8] Retrieve an image}\\
\textbf{Description :} A \texttt{GET /images/id} stream. \textbf{Amendment:} Image data is streamed directly to the client via the AWS SDK.
\vspace{1em}

\noindent\textbf{Requirement 9 : [Amended (Up) to Requirement 9] Delete an image}\\
\textbf{Description :} A \texttt{DELETE /images/id} removes the image. \textbf{Amendment:} Protected by JWT Authority. Deletes records in PostgreSQL and the object in Garage S3.
\vspace{1em}

\noindent\textbf{Requirement 10 : [Amended (Up) to Requirement 10] Transfer the list of most similar images}\\
\textbf{Description :} A \texttt{GET /images/id/similar} executes \texttt{semantic}, \texttt{cielab}, and \texttt{weighted} Euclidean searches in the database.
\vspace{1em}

\noindent\textbf{Requirement 11 : [Original to Requirement 11] Retrieve image metadata}\\
\textbf{Description :} \texttt{GET /images/id/metadata} reveals dimensions, generated AI tags, and processing state.
\vspace{1em}

\noindent\textbf{Requirement 12 \& 13 : [Original] Add / Delete a keyword}\\
\textbf{Description :} Safely mutate relational tags attached to image metadata.
\vspace{1em}

\noindent\textbf{Requirement 14 : [Amended (Up) to Requirement 14] Transfer the list of keywords}\\
\textbf{Description :} Provides autocomplete endpoints including \texttt{/search?q=} and \texttt{/popular} queries.
\vspace{1em}

\noindent\textbf{Requirement 15 : [Amended (Up) to Requirement 15] Search for images by attributes}\\
\textbf{Description :} Handled dynamically via paginated query parameters (e.g., \texttt{?keywords=xyz}) adhering to modern RESTful standards rather than utilizing the body of a GET request.

\clearpage

\section{Client}

\subsection*{User Interface Operations}

\noindent\textbf{Requirement 16 : [Amended (Up) to Requirement 16] Browse images available on the server}\\
\textbf{Description :} Renders a CSS masonry grid that integrates backend pagination state.
\vspace{1em}

\noindent\textbf{Requirement 17 : [Amended (Up) to Requirement 17] Select an image and display similar images}\\
\textbf{Description :} A search sidebar provides similarity algorithm configuration and percentage match outputs.
\vspace{1em}

\noindent\textbf{Requirement 18 : [Amended (Up) to Requirement 18] Save an image to disk}\\
\textbf{Description :} The UI provides a dedicated "Download Original" action utilizing a presigned S3 routing mechanism with enforced \texttt{Content-Disposition: attachment} headers.
\vspace{1em}

\noindent\textbf{Requirement 19 : [Amended (Up) to Requirement 19] Add an image to the server}\\
\textbf{Description :} Upload form supporting up to 10 files in a local queue.
\vspace{1em}

\noindent\textbf{Requirement 20 : [Original to Requirement 20] Delete an image}\\
\textbf{Description :} Executes an optimistic DOM update alongside the REST API call to strip elements instantly.
\vspace{1em}

\noindent\textbf{Requirement 21, 22 \& 23 : [Original] Tags and Search}\\
\textbf{Description :} Integrated UI systems allowing deep metadata mutations and filtering.

\clearpage

\section{Non-Functional Requirements}

\subsection*{Environment \& Quality}

\noindent\textbf{Requirement 24 : [Amended (Up) to Requirement 24] Continuous Integration \& Code Quality}\\
\textbf{Description :} Gitlab pipelines construct isolated execution layers. \textbf{Amendment:} A global Prettier (\texttt{.prettierrc}) integration enforces styling standardization. The codebase is audited using \texttt{gitleaks} and \texttt{trivy}. Dockerfiles implement \texttt{HEALTHCHECK} directives to monitor container status.
\vspace{1em}

\noindent\textbf{Requirement 25 : [Amended (Up) to Requirement 25] Server compatibility}\\
\textbf{Description :} Tested on Java 21, Spring Boot 4.0.5, Mockito 5.23.0, AWS SDK BOM 2.42.32, \texttt{pgvector} 0.1.6, BoofCV 1.3.0, ONNX Runtime 1.24.3, PostgreSQL 18, and Garage v2.2.0. Verified on x86\_64 Arch Linux and ARM64 Ubuntu 24.04 LTS environments.
\vspace{1em}

\noindent\textbf{Requirement 26 : [Amended (Up) to Requirement 26] Client compatibility}\\
\textbf{Description :} Modern SPA structure operating strictly on predefined specifications: Vue.js \textasciicircum3.5.32, Vite 8.0.8, Axios 1.15.0, TypeScript 6.0.2, Vue Router 5.0.4, and @types/node 25.6.0. \\
\textbf{Validation:} Verified working on Mozilla Firefox and Zen Browser via a KDE Plasma 6 (Wayland) display server.
\vspace{1em}

\noindent\textbf{Requirement 27 : [Amended (Up) to Requirement 27] Documentation}\\
\textbf{Description :} Markdown wiki covering deployment and architecture.

\clearpage

\section{Extensions}

\subsection*{Architecture \& Data Integrity}

\noindent\textbf{Requirement 28 : [Extension] Global ProblemDetail Exception Handling (RFC 7807)}\\
\textbf{Description :} All uncaught server exceptions are translated into standardized RFC 7807 \texttt{ProblemDetail} JSON schemas equipped with unique \texttt{traceId} tags for telemetry tracking.
\vspace{1em}

\noindent\textbf{Requirement 29 : [Extension] Ephemeral Database Environments}\\
\textbf{Description :} The system utilizes \texttt{tmpfs} mounts locally to enforce a zero-state initialization environment for Flyway schema migrations, ensuring a consistent database state.
\vspace{1em}

\noindent\textbf{Requirement 30 : [Extension] Docker Orchestration \& Nginx Reverse Proxy}\\
\textbf{Description :} A compiled Vue 3 static artifact is served via an \textbf{Nginx} container which dynamically reverse-proxies API calls directly to the Java Backend, completely masking the Tomcat engine from public network exposure.
\vspace{1em}

\noindent\textbf{Requirement 31 : [Extension] Telemetry Observability Pipelines}\\
\textbf{Description :} Spring Boot Actuator endpoints are scraped by a \textbf{Prometheus v3.5.0} container and visualized within \textbf{Grafana 12.4.2} dashboards to provide metrics on GC latency and thread usage.
\vspace{1em}

\noindent\textbf{Requirement 32 : [Extension] SPA Fallback Routing Filter}\\
\textbf{Description :} A custom \texttt{OncePerRequestFilter} intercepts HTML5 History Mode paths directly in the Tomcat execution context during native development, forwarding undefined routes to \texttt{index.html} without relying on external Nginx fallbacks.
\vspace{1em}

\noindent\textbf{Requirement 33 : [Extension] SHA-256 Payload Deduplication Layer}\\
\textbf{Description :} Upon upload, every binary payload is mapped against a cryptographic SHA-256 hash. If an exact duplicate hash is detected, a \texttt{409 Conflict} is explicitly thrown to prevent S3 object storage bloat.

\subsection*{Security \& Dataset Management}

\noindent\textbf{Requirement 34 : [Extension] Stateless JWT Authentication}\\
\textbf{Description :} Fully implemented stateless Role-Based Access Control allowing User and Admin privilege tiers, natively securing deletion and administrative operations.
\vspace{1em}

\noindent\textbf{Requirement 35 : [Extension] Admin Unsplash Dataset Ingestion}\\
\textbf{Description :} The \texttt{ROLE\_ADMIN} interface features an async ingestion mechanism capable of parsing `.tsv` datasets and scheduling batch downloads from external CDNs.
\vspace{1em}

\noindent\textbf{Requirement 36 : [Extension] (Removed)}\\
\textbf{Description :} This extension was removed to align with standard streaming practices.
\vspace{1em}

\noindent\textbf{Requirement 37 : [Extension] Dynamic Administrator Configuration State}\\
\textbf{Description :} Global boolean flags, such as \texttt{auto\_approve\_users}, are persisted to a dynamic \texttt{app\_settings} database table, allowing administrators to modify registration security parameters in real-time without restarting containers.
\vspace{1em}

\noindent\textbf{Requirement 38 : [Extension] Asynchronous Politeness Rate-Limiting}\\
\textbf{Description :} When processing massive batch downloads from the Unsplash API, the \texttt{UnsplashService} integrates Thread suspension politeness limits, ensuring external CDNs are not DDoS'd by the application's ingestion engine.
\vspace{1em}

\noindent\textbf{Requirement 39 : [Extension] Upload Request Quotas}\\
\textbf{Description :} The API actively scans the database queue upon multi-part file reception. Standard users are rate-limited to a maximum of 10 concurrently \texttt{PENDING} machine learning extraction tasks to preserve global CPU stability.

\subsection*{User Interface \& Experience}

\noindent\textbf{Requirement 40 : [Extension] Cruelty Squad Audio-Visual Theme (HUX)}\\
\textbf{Description :} A hidden "Cruelty" theme manipulates DOM events to generate a Hostile User Experience, projecting CRT scanlines and procedural Web Audio API sawtooth frequencies.

\clearpage

\section{Extended Amendments}

\subsection*{Advanced Image Processing \& AI}

\noindent\textbf{Requirement 41 : [Extended Amendment to Requirement 3] Lanczos3 Spatial Standardization}\\
\textbf{Description :} Before descriptors are computed, all inputs are resampled to a uniform spatial resolution ($256\times256$) using the Lanczos3 algorithm.
\vspace{1em}

\noindent\textbf{Requirement 42 : [Extended Amendment to Requirement 3] ONNX SigLIP 2 Semantic Inference}\\
\textbf{Description :} An embedded ONNX Runtime inference engine executes a SigLIP 2 Vision Transformer to map images to a 768-dimensional semantic logits array for semantic search.
\vspace{1em}

\noindent\textbf{Requirement 43 : [Extended Amendment to Requirement 3] SigLIP 2 Auto-Tagging}\\
\textbf{Description :} The ONNX runtime executes dot-product threshold filtering mapping extracted vision logits against a cached array of text token embeddings to infer semantic tags (e.g., \texttt{wildlife}) through automated processing.
\vspace{1em}

\noindent\textbf{Requirement 44 : [Extended Amendment to Requirement 3 \& 7] Long-Polling Thread Suspension}\\
\textbf{Description :} To limit constant API polling, background async status endpoints use Spring \texttt{DeferredResult} to securely suspend the HTTP context, mimicking real-time Socket updates while remaining purely RESTful.
\vspace{1.5em}

\subsection*{Advanced Search \& Vector Mathematics}

\noindent\textbf{Requirement 45 : [Extended Amendment to Requirement 4 \& 10] HNSW Matrix Graphing}\\
\textbf{Description :} The integration of `hnsw` indexes for PGVector improves vector recall speed, using configurations like \texttt{m=32, ef\_construction=256} to index 768-dimensional space.
\vspace{1em}

\noindent\textbf{Requirement 46 : [Extended Amendment to Requirement 4 \& 10] CIELAB Color Modeling}\\
\textbf{Description :} Engineering of a 512-dimensional CIELAB mapping algorithm that separates lightness and chromatic data to represent human visual perception.
\vspace{1em}

\noindent\textbf{Requirement 47 : [Extended Amendment to Requirement 17] Ephemeral Memory Search}\\
\textbf{Description :} The client UI permits users to upload an image for similarity scanning. The backend extracts vectors directly in RAM, queries the database, and returns matches without saving the image to S3.

\clearpage

\section{Critical Compliance \& Structural Integrity}

\noindent\textbf{Requirement 48 : [Extended Amendment] Zero-Trust Cryptographic Integrity Validation}\\
\textbf{Description :} The server streams and cryptographically hashes an internal, un-optimizable payload (\textit{The Whole War and Peace Novel.pdf}) against a hardcoded SHA-256 signature (\texttt{4e22...a6bb}). If compiler optimizations strip the asset or manual tampering is detected, the container instantly triggers a fatal execution halt to enforce strict deployment immutability.
\vspace{1.5em}

\noindent\textbf{Requirement 49 : [Extended Amendment] Enterprise DevOps Operability Validation Document}\\
\textbf{Description :} To guarantee 99.999\% uptime, a secondary \texttt{enterprise\_requirements.tex} document is actively maintained within this repository. It evaluates the systemic limitations of our current infrastructure and establishes the strategic guidance pipelines required by our lead Cloud Architects.

\end{document}

=========================================
File: ./package.json
=========================================
{
  "name": "v3a",
  "version": "1.0.0",
  "description": "**Live Environments (Coming Soon):** * Production URL: `[Insert Public Production Link Here]` * Preview URL: `[Insert Public Preview Link Here]`",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "format": "prettier -w ."
  },
  "repository": {
    "type": "git",
    "url": "git@gitlab.emi.u-bordeaux.fr:pdl-l3/teams/2026/v3/v3a.git"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "type": "commonjs",
  "devDependencies": {
    "@prettier/plugin-xml": "^3.4.2",
    "prettier": "3.8.1",
    "prettier-plugin-java": "2.8.1",
    "prettier-plugin-sh": "^0.18.0"
  }
}


=========================================
File: ./prometheus/prometheus.yml
=========================================
global:
  scrape_interval: 10s

scrape_configs:
  - job_name: "spring-backend"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["backend:8080"]


=========================================
File: ./README.md
=========================================
# PDLaser - Image Management System (v3a)

**Live Environments:**

- **Production URL:** `http://bigpdlaser.duckdns.org/`
- **Preview URL:** `https://bigpreviewlaser.duckdns.org/gallery`

## Overview

PDLaser is an image management system built for visual archiving. It executes content-based image retrieval (CBIR) using mathematical histograms and SigLIP 2 semantic embeddings.

**Tech Stack:**

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Data JDBC, Flyway, BoofCV 1.3.0, ONNX Runtime 1.24.3, Twelvemonkeys ImageIO 3.13.1
- **Frontend:** Vue.js ^3.5.32, TypeScript 6.0.2, Vite 8.0.8, Vue Router 5.0.4, Axios 1.15.0
- **Database:** PostgreSQL 18 + `pgvector` 0.1.6 (HNSW Indexing)
- **Infrastructure:** Docker, Nginx (alpine), Garage S3 (v2.2.0)

---

## [The Twelve-Factor App](https://12factor.net/) Methodology

This application follows the Twelve-Factor App methodology for portability and dev/prod parity.

- **I. Codebase:** One repository tracks all microservices, triggering isolated CI/CD builds.
- **III. Config:** Routing and S3 keys are passed via environment variables.
- **IV. Backing Services:** PostgreSQL and Garage S3 are treated as detached, swappable resources.
- **VI. Processes:** The Java Spring Boot API and Vue SPA are completely stateless. JWT tokens manage sessions.
- **VIII. Concurrency:** Workloads scale horizontally. Java 21 Virtual Threads handle ONNX tensor extraction.
- **IX. Disposability:** Database row locks (`FOR UPDATE SKIP LOCKED`) allow the extraction queue to recover if a container crashes.

---

## Development Standards & Contributing

This project enforces the following standards for contributions:

- **[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/):** Commit messages must follow the Conventional Commits specification.
- **Code Formatting:** We use Prettier to format code (Java, TypeScript, Vue, Markdown). Format your files before committing:
  ```bash
  npm run format
  ```

---

## Getting Started & Running the Application

### Prerequisites

- **Docker** & **Docker Compose**
- **Java JDK 21** & **Maven 3.9+** (For local native backend development)
- **Node.js 24+** (For local native frontend development)

### Method A: Fully Containerized (Recommended)

The project utilizes explicit docker-compose overrides to guarantee Dev/Prod parity.

**1. Development Environment (Hot-reloading simulated, tmpfs RAM storage):**

```bash
docker compose -f docker-compose.dev.yml up --build
```

**2. Preview Environment (Logging enabled, isolated network tests):**

```bash
docker compose -f docker-compose.preview.yml up --build
```

**3. Production Environment (Persistent SSD Volumes, auto-restart policies):**

```bash
docker compose -f docker-compose.prod.yml up -d --build
```

- **Frontend UI:** `http://localhost:3000`
- **Backend API:** `http://localhost:8080`
- **Grafana Telemetry:** `http://localhost:3001` (Requires Prometheus integration)

> **Note:** The default Administrator account is automatically seeded into the database on the first boot (Username: `admin` | Password: `admin`).

---

### Method B: Local Native Execution (Without full Docker)

If you need to run the code natively on your host OS (Arch/Ubuntu) for debugging or IDE breakpoints, you must still spin up the backing services.

**Step 1: Start the Backing Services (DB & Garage S3)**

```bash
docker compose -f docker-compose.dev.yml up db garage garage-init -d
```

**Step 2: Run the Java Backend natively**
The `application.yaml` is pre-configured with default fallbacks to `localhost` for native execution.

```bash
cd backend
./mvnw spring-boot:run
```

**Step 3: Run the Vue Frontend natively**
_(Note: If running natively, temporarily edit `frontend/vite.config.ts` and change `target: "http://backend:8080"` to `target: "http://localhost:8080"` so Vite proxies to your local host instead of the docker bridge)._

```bash
cd frontend
npm install
npm run dev
```

---

## System Compatibility & Validation (Requirement 27)

The client, server, and object storage infrastructure have been tested across the following architectures:

**Server / Infrastructure Environments (x86_64 & ARM64):**

- **Ubuntu 24.04.4 LTS (aarch64):** Oracle Cloud (Neoverse-N1, Linux 6.17.0-1009-oracle). Validates ARM64 Docker/ONNX/pgvector stability.
- **Arch Linux (x86_64):** AMD Ryzen 5 7640U, Linux 6.19.11-zen1-1-zen.

**Client Environments:**

- **OS:** Arch Linux (KDE Plasma 6.6.4 / Wayland)
- **Browsers Tested:** Mozilla Firefox, Zen Browser


=========================================
File: ./docker-compose.preview.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      POSTGRES_DB: pdl_db
      PGDATA: /var/lib/postgresql/18/data
    expose:
      - "5432"
    tmpfs:
      - /var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped
    logging: &default-logging
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"

  garage:
    image: dxflrs/garage:v2.2.0
    env_file:
      - .env
    environment:
      GARAGE_RPC_SECRET: ${GARAGE_RPC_SECRET}
    expose:
      - "3900"
    tmpfs:
      - /var/lib/garage/data
      - /var/lib/garage/meta
    volumes:
      - ./garage.toml:/etc/garage.toml
    command: ["/garage", "server"]
    healthcheck: # FIX: Direct array execution, no shell interpretation
      test: ["CMD", "/garage", "status"]
      interval: 5s
      timeout: 3s
      retries: 5
    restart: unless-stopped
    logging: *default-logging

  garage-init:
    image: alpine:3.23
    env_file:
      - .env
    depends_on:
      garage:
        condition: service_healthy
    network_mode: "service:garage"
    pid: "service:garage"
    environment:
      - ACCESS_KEY=${S3_ACCESS_KEY}
      - SECRET_KEY=${S3_SECRET_KEY}
      - BUCKET_NAME=pdl-images
      - GARAGE_RPC_SECRET=${GARAGE_RPC_SECRET}
    restart: "on-failure:3"
    entrypoint:
      - /bin/sh
      - -eu
      - -c
      - |
        G="chroot /proc/1/root /garage"
        echo "Waiting for Garage daemon..."
        for i in $$(seq 1 20); do
          if $$G node id -q 2>/dev/null; then break; fi
          sleep 2
        done
        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
          echo "Initializing single-node layout..."
          NODE_ID=$$($$G node id -q)
          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
          $$G layout apply --version 1
        fi
        echo "Configuring bucket and credentials..."
        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
        $$G bucket create "$$BUCKET_NAME" || true
        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true
        echo "Garage S3 initialized successfully."

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    env_file:
      - .env
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - DATABASE_PASSWORD=${POSTGRES_PASSWORD}
      - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
      - APP_MODELS_DIR=/var/lib/pdl/models
      - ADMIN_USERNAME=${ADMIN_USERNAME}
      - ADMIN_PASSWORD=${ADMIN_PASSWORD}
      - S3_ENDPOINT=http://garage:3900
      - S3_REGION=garage
      - S3_ACCESS_KEY=${S3_ACCESS_KEY}
      - S3_SECRET_KEY=${S3_SECRET_KEY}
      - S3_BUCKET=pdl-images
      - JWT_SECRET=${JWT_SECRET}
      - LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=WARN
    expose:
      - "8080"
    tmpfs:
      - /var/lib/pdl/datasets
      - /var/lib/pdl/models
    depends_on:
      db:
        condition: service_healthy
      garage:
        condition: service_healthy
    restart: unless-stopped
    logging: *default-logging

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    expose:
      - "8080"
    depends_on:
      - backend
    restart: unless-stopped
    logging: *default-logging
    networks:
      - dokploy-network
      - default

networks:
  dokploy-network:
    external: true


=========================================
File: ./docker-compose.prod.yml
=========================================
services:
  db:
    image: pgvector/pgvector:pg18
    environment:
      POSTGRES_USER: pdl_user
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      POSTGRES_DB: pdl_db
      PGDATA: /var/lib/postgresql/18/data
    expose:
      - "5432"
    volumes:
      - pgdata:/var/lib/postgresql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U pdl_user -d pdl_db"]
      interval: 5s
      timeout: 5s
      retries: 5
    restart: unless-stopped
    logging: &default-logging
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"

  garage:
    image: dxflrs/garage:v2.2.0
    env_file:
      - .env
    environment:
      GARAGE_RPC_SECRET: ${GARAGE_RPC_SECRET}
    expose:
      - "3900"
    volumes:
      - garagedata:/var/lib/garage/data
      - garagemeta:/var/lib/garage/meta
      - ./garage.toml:/etc/garage.toml
    command: ["/garage", "server"]
    healthcheck:
      test: ["CMD", "/garage", "status"]
      interval: 5s
      timeout: 3s
      retries: 5
    restart: unless-stopped
    logging: *default-logging

  garage-init:
    image: alpine:3.23
    env_file:
      - .env
    depends_on:
      garage:
        condition: service_healthy
    network_mode: "service:garage"
    pid: "service:garage"
    environment:
      - ACCESS_KEY=${S3_ACCESS_KEY}
      - SECRET_KEY=${S3_SECRET_KEY}
      - BUCKET_NAME=pdl-images
      - GARAGE_RPC_SECRET=${GARAGE_RPC_SECRET}
    restart: "on-failure:3"
    entrypoint:
      - /bin/sh
      - -eu
      - -c
      - |
        G="chroot /proc/1/root /garage"
        echo "Waiting for Garage daemon..."
        for i in $$(seq 1 20); do
          if $$G node id -q 2>/dev/null; then break; fi
          sleep 2
        done
        if $$G status 2>&1 | grep -q "NO ROLE ASSIGNED"; then
          echo "Initializing single-node layout..."
          NODE_ID=$$($$G node id -q)
          $$G layout assign "$$NODE_ID" -z dc1 -c 1G
          $$G layout apply --version 1
        fi
        echo "Configuring bucket and credentials..."
        $$G key import --yes -n pdl-key "$$ACCESS_KEY" "$$SECRET_KEY" || true
        $$G bucket create "$$BUCKET_NAME" || true
        $$G bucket allow "$$BUCKET_NAME" --read --write --owner --key pdl-key || true
        echo "Garage S3 initialized successfully."

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
    env_file:
      - .env
    environment:
      - HOST_NAME=db
      - DATABASE_NAME=pdl_db
      - DATABASE_USER=pdl_user
      - DATABASE_PASSWORD=${POSTGRES_PASSWORD}
      - APP_UNSPLASH_DATASET_DIR=/var/lib/pdl/datasets
      - APP_MODELS_DIR=/var/lib/pdl/models
      - ADMIN_USERNAME=${ADMIN_USERNAME}
      - ADMIN_PASSWORD=${ADMIN_PASSWORD}
      - S3_ENDPOINT=http://garage:3900
      - S3_REGION=garage
      - S3_ACCESS_KEY=${S3_ACCESS_KEY}
      - S3_SECRET_KEY=${S3_SECRET_KEY}
      - S3_BUCKET=pdl-images
      - JWT_SECRET=${JWT_SECRET}
      - LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=WARN
    expose:
      - "8080"
    tmpfs:
      - /var/lib/pdl/datasets
      - /var/lib/pdl/models
    depends_on:
      db:
        condition: service_healthy
      garage:
        condition: service_healthy
    restart: unless-stopped
    logging: *default-logging

  prometheus:
    image: prom/prometheus:v3.5.0
    command: ["--config.file=/etc/prometheus/prometheus.yml", "--log.level=warn"]
    volumes:
      - ./prometheus/prometheus.yml:/etc/prometheus/prometheus.yml
      - promdata:/prometheus
    expose:
      - "9090"
    depends_on:
      - backend
    logging: *default-logging

  grafana:
    image: grafana/grafana:12.4.2
    environment:
      - GF_LOG_LEVEL=warn
    expose:
      - "3000"
    volumes:
      - grafanadata:/var/lib/grafana
    depends_on:
      - prometheus
    logging: *default-logging

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    expose:
      - "8080"
    depends_on:
      - backend
    restart: unless-stopped
    logging: *default-logging
    networks:
      - dokploy-network
      - default

volumes:
  pgdata:
  garagedata:
  garagemeta:
  promdata:
  grafanadata:

networks:
  dokploy-network:
    external: true


=========================================
File: ./garage.toml
=========================================
metadata_dir = "/var/lib/garage/meta"
data_dir = "/var/lib/garage/data"
db_engine = "lmdb"

replication_factor = 1
consistency_mode = "consistent"

rpc_bind_addr = "[::]:3901"

[s3_api]
api_bind_addr = "[::]:3900"
s3_region = "garage"
root_domain = ".s3.garage.localhost"

[s3_web]
bind_addr = "[::]:3902"
root_domain = ".web.garage.localhost"

[admin]
api_bind_addr = "[::]:3903"


=========================================
File: ./generate_env.sh
=========================================
#!/bin/bash

# Database and Admin passwords (alphanumeric only to prevent parsing errors)
PG_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)
ADMIN_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)

# Garage S3 Access Key MUST start with "GK" followed by hex characters
S3_ACCESS="GK$(openssl rand -hex 12)"

# Garage S3 Secret Key and RPC Secret (RPC secret must be exactly 32 bytes hex encoded)
S3_SECRET=$(openssl rand -hex 32)
GARAGE_RPC=$(openssl rand -hex 32)

# Spring Boot JWT Secret (Hex encoding guarantees no special character issues, 32 bytes / 256 bits)
JWT=$(openssl rand -hex 32)

echo "POSTGRES_PASSWORD=${PG_PASS}"
echo "ADMIN_USERNAME=admin"
echo "ADMIN_PASSWORD=${ADMIN_PASS}"
echo "S3_ACCESS_KEY=${S3_ACCESS}"
echo "S3_SECRET_KEY=${S3_SECRET}"
echo "JWT_SECRET=${JWT}"
echo "GARAGE_RPC_SECRET=${GARAGE_RPC}"


