/**
 * SISTEMA SABER PRO - JavaScript Principal
 * Funcionalidades generales del sistema
 */

// Configuración global
const SaberProSystem = {
    version: '1.0.0',
    author: 'Sistema SABER PRO',
    
    // Inicializar el sistema
    init: function() {
        this.setupEventListeners();
        this.initializeTooltips();
        this.initializeAlerts();
        this.setupFormValidation();
        this.initializeDataTables();
        console.log('Sistema SABER PRO inicializado correctamente');
    },
    
    // Configurar event listeners
    setupEventListeners: function() {
        // Confirmación de eliminaciones
        document.querySelectorAll('[data-confirm]').forEach(element => {
            element.addEventListener('click', function(e) {
                const message = this.getAttribute('data-confirm') || '¿Está seguro de realizar esta acción?';
                if (!confirm(message)) {
                    e.preventDefault();
                }
            });
        });
        
        // Loading states para botones
        document.querySelectorAll('.btn[type="submit"]').forEach(button => {
            button.addEventListener('click', function() {
                this.disabled = true;
                const originalText = this.innerHTML;
                this.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Procesando...';
                
                // Restaurar después de 3 segundos (fallback)
                setTimeout(() => {
                    this.disabled = false;
                    this.innerHTML = originalText;
                }, 3000);
            });
        });
    },
    
    // Inicializar tooltips de Bootstrap
    initializeTooltips: function() {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
    },
    
    // Auto-ocultar alertas
    initializeAlerts: function() {
        const alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
        alerts.forEach(alert => {
            setTimeout(() => {
                if (alert.parentNode) {
                    alert.classList.add('fade');
                    setTimeout(() => alert.remove(), 500);
                }
            }, 5000);
        });
    },
    
    // Validación de formularios
    setupFormValidation: function() {
        const forms = document.querySelectorAll('.needs-validation');
        forms.forEach(form => {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            });
        });
        
        // Validación en tiempo real para campos específicos
        this.setupDocumentValidation();
        this.setupEmailValidation();
        this.setupPuntajeValidation();
    },
    
    // Validación de documento
    setupDocumentValidation: function() {
        const documentInputs = document.querySelectorAll('input[name="documento"]');
        documentInputs.forEach(input => {
            input.addEventListener('input', function() {
                const value = this.value.replace(/\D/g, ''); // Solo números
                this.value = value;
                
                if (value.length < 8 || value.length > 15) {
                    this.setCustomValidity('El documento debe tener entre 8 y 15 dígitos');
                } else {
                    this.setCustomValidity('');
                }
            });
        });
    },
    
    // Validación de email
    setupEmailValidation: function() {
        const emailInputs = document.querySelectorAll('input[type="email"]');
        emailInputs.forEach(input => {
            input.addEventListener('blur', function() {
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (this.value && !emailRegex.test(this.value)) {
                    this.setCustomValidity('Por favor ingrese un email válido');
                } else {
                    this.setCustomValidity('');
                }
            });
        });
    },
    
    // Validación de puntajes SABER PRO
    setupPuntajeValidation: function() {
        const puntajeInputs = document.querySelectorAll('input[name*="puntaje"], input[name*="modulo"]');
        puntajeInputs.forEach(input => {
            input.addEventListener('input', function() {
                const value = parseInt(this.value);
                const isGlobal = this.name.includes('Global');
                const max = isGlobal ? 300 : 100;
                
                if (value < 0 || value > max) {
                    this.setCustomValidity(`El puntaje debe estar entre 0 y ${max}`);
                } else {
                    this.setCustomValidity('');
                }
                
                // Actualizar color según el puntaje
                this.updatePuntajeColor(value, isGlobal);
            });
        });
    },
    
    // Inicializar DataTables si existe
    initializeDataTables: function() {
        if (typeof $ !== 'undefined' && $.fn.DataTable) {
            $('.datatable').DataTable({
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/es-ES.json'
                },
                responsive: true,
                pageLength: 25,
                order: [[0, 'asc']]
            });
        }
    },
    
    // Mostrar notificación
    showNotification: function(message, type = 'info', duration = 3000) {
        const alertTypes = {
            'success': 'alert-success',
            'error': 'alert-danger', 
            'warning': 'alert-warning',
            'info': 'alert-info'
        };
        
        const alertClass = alertTypes[type] || 'alert-info';
        const icon = {
            'success': 'fas fa-check-circle',
            'error': 'fas fa-exclamation-triangle',
            'warning': 'fas fa-exclamation-circle',
            'info': 'fas fa-info-circle'
        }[type] || 'fas fa-info-circle';
        
        const alertHTML = `
            <div class="alert ${alertClass} alert-dismissible fade show position-fixed" 
                 style="top: 20px; right: 20px; z-index: 9999; min-width: 300px;">
                <i class="${icon} me-2"></i>
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        `;
        
        document.body.insertAdjacentHTML('beforeend', alertHTML);
        
        // Auto-remover después del tiempo especificado
        setTimeout(() => {
            const alert = document.querySelector('.alert.position-fixed');
            if (alert) alert.remove();
        }, duration);
    },
    
    // Formatear números
    formatNumber: function(number, decimals = 0) {
        return new Intl.NumberFormat('es-CO', {
            minimumFractionDigits: decimals,
            maximumFractionDigits: decimals
        }).format(number);
    },
    
    // Calcular beneficio basado en puntaje
    calculateBenefit: function(puntaje) {
        if (puntaje < 80) {
            return {
                beneficio: 'NO PUEDE GRADUARSE',
                color: 'danger',
                descripcion: 'Debe volver a presentar las pruebas SABER PRO'
            };
        } else if (puntaje <= 150) {
            return {
                beneficio: 'Exoneración parcial de derechos',
                color: 'info',
                descripcion: 'Descuento del 25% en derechos de grado'
            };
        } else if (puntaje <= 170) {
            return {
                beneficio: 'Exoneración total + 50% descuento en grado',
                color: 'success',
                descripcion: 'Sin derechos administrativos + 50% descuento en ceremonia'
            };
        } else {
            return {
                beneficio: 'Exoneración total + 100% descuento en grado',
                color: 'primary',
                descripcion: 'Sin derechos administrativos + ceremonia gratuita'
            };
        }
    }
};

// Extensión para actualizar color de puntajes
HTMLInputElement.prototype.updatePuntajeColor = function(value, isGlobal) {
    this.classList.remove('border-success', 'border-warning', 'border-danger');
    
    if (isGlobal) {
        if (value >= 170) this.classList.add('border-success');
        else if (value >= 80) this.classList.add('border-warning');
        else this.classList.add('border-danger');
    } else {
        if (value >= 80) this.classList.add('border-success');
        else if (value >= 60) this.classList.add('border-warning');
        else this.classList.add('border-danger');
    }
};

// Utilidades globales
window.SaberPro = SaberProSystem;

// Inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', function() {
    SaberProSystem.init();
});

// Funciones específicas para reportes
const ReportUtils = {
    // Exportar tabla a CSV
    exportTableToCSV: function(tableId, filename = 'reporte.csv') {
        const table = document.getElementById(tableId);
        const rows = table.querySelectorAll('tr');
        let csv = '';
        
        rows.forEach(row => {
            const cells = row.querySelectorAll('th, td');
            const rowData = Array.from(cells).map(cell => {
                return '"' + cell.textContent.trim().replace(/"/g, '""') + '"';
            });
            csv += rowData.join(',') + '\n';
        });
        
        const blob = new Blob([csv], { type: 'text/csv' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = filename;
        a.click();
        window.URL.revokeObjectURL(url);
    },
    
    // Imprimir reporte
    printReport: function(elementId) {
        const element = document.getElementById(elementId);
        const printWindow = window.open('', '', 'width=800,height=600');
        
        printWindow.document.write(`
            <html>
                <head>
                    <title>Reporte SABER PRO</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                    <style>
                        @media print {
                            .no-print { display: none !important; }
                            body { font-size: 12px; }
                        }
                    </style>
                </head>
                <body>
                    ${element.outerHTML}
                </body>
            </html>
        `);
        
        printWindow.document.close();
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    }
};

// Hacer disponible globalmente
window.ReportUtils = ReportUtils;