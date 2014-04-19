package de.anschalter.util.service;

/*
 *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *   
 *  Any unauthorised copying, duplication, reproduction and
 *  compilation will constitute an infringement of copyright.
 *   
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author keeper
 */
public class ServiceProvider {

    public static final void register(String className) {
        _instance.services.put(className, new ServiceProxy(className));
    }

    public static Object getService(String className) {
        return _instance.services.get(className).getInstance();
    }

    public static interface Service {

        public Object getInstance();
    }

    public static final class ServiceProxy
            implements Service {

        @SuppressWarnings("rawtypes")
		private Class delegate;
        private String name;

        public ServiceProxy(String className) {
            name = className;
        }

        @Override
        public Object getInstance() {
            Object result = null;
            try {
                delegate = Class.forName(name);
                result = delegate.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(ServiceProvider.class.getName()).log(
                        Level.SEVERE,
                        null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServiceProvider.class.getName()).log(
                        Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiceProvider.class.getName()).log(
                        Level.SEVERE,
                        null, ex);
            }
            return result;
        }
    }
    private Map<String, Service> services = new HashMap<String, Service>();
    private static final ServiceProvider _instance = new ServiceProvider();

    private ServiceProvider() {
    }
}
