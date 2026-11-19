    class FooBar {
        private int n;
        private final Semaphore fooSem = new Semaphore(1); // run first so starts with 1 initial permit
        private final Semaphore barSem = new Semaphore(0); // run later so starts with 0 initial permit (block at first)

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                fooSem.acquire(); // get 1 permit from fooSem (blocks if 0), next loop fooSem be 0, when fooSem.release() not triggered

                printFoo.run();

                barSem.release(); // put 1 permit into barSem (let bar can keep going from blocking)
            }

            // use Semaphore can avoid while loop keep checking and use cpu signal to block and run thread.
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                barSem.acquire();

                printBar.run();

                fooSem.release();
            }
        }
    }
